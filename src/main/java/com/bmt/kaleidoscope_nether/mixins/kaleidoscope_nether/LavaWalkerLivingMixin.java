package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.init.KNEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class LavaWalkerLivingMixin {

    @Inject(
            method = "collide(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void lavaWalker$enableLavaWalking(Vec3 movement, CallbackInfoReturnable<Vec3> cir) {
        Entity self = (Entity) (Object) this;

        if (!(self instanceof LivingEntity entity)) {
            return;
        }

        Vec3 original = cir.getReturnValue();

        if (original.y > 0) {
            return;
        }

        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
        if (boots.isEmpty()) {
            return;
        }

        int enchantmentLevel = boots.getEnchantmentLevel(KNEnchantments.LAVA_WALKER.get());
        if (enchantmentLevel <= 0) {
            return;
        }

        if (entity.isShiftKeyDown()) {
            return;
        }

        Level level = entity.level();

        int[][] offsets = {
                { 1, 0, 1 }, { 1, 0, 0 }, { 1, -1, 0 }, { 1, 0, -1 },
                { 0, 0, 1 }, { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, -1 },
                { -1, 0, 1 }, { -1, 0, 0 }, { -1, -1, 0 }, { -1, 0, -1 }
        };

        double highestLavaY = original.y;
        boolean foundLava = false;

        for (int[] offset : offsets) {
            BlockPos sourcePos = entity.blockPosition();
            BlockPos pos = new BlockPos(
                    sourcePos.getX() + offset[0],
                    sourcePos.getY() + offset[1],
                    sourcePos.getZ() + offset[2]);

            FluidState fluidState = level.getFluidState(pos);

            if (fluidState.isEmpty() || !fluidState.is(FluidTags.LAVA)) {
                continue;
            }

            VoxelShape shape = Shapes.block().move(
                    pos.getX(),
                    pos.getY() + fluidState.getOwnHeight(),
                    pos.getZ());

            if (Shapes.joinIsNotEmpty(
                    shape,
                    Shapes.create(entity.getBoundingBox().inflate(0.5)),
                    BooleanOp.AND)) {
                double height = shape.max(Direction.Axis.Y) - entity.getY() - 1;

                if (highestLavaY < height) {
                    highestLavaY = height;
                    foundLava = true;
                }
            }
        }

        if (foundLava) {
            entity.fallDistance = 0F;
            entity.setOnGround(true);

            cir.setReturnValue(new Vec3(original.x, highestLavaY, original.z));
        }
    }
}