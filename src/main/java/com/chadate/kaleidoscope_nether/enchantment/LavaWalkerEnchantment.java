package com.chadate.kaleidoscope_nether.enchantment;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.chadate.kaleidoscope_nether.registry.KNEnchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class LavaWalkerEnchantment {

    private static void applyLavaWalkerEffect(LivingEntity entity) {
        if (entity instanceof Player player && player.isSpectator()) {
            return;
        }

        Vec3 pos = entity.position();
        Vec3 movement = entity.getDeltaMovement();
        Vec3 futurePos = pos.add(movement);
        BlockPos onPos = entity.getOnPos();
        BlockPos futureBlockPos = BlockPos.containing(futurePos.x, futurePos.y, futurePos.z);

        boolean isMoving = movement.lengthSqr() > 0.0001;

        if (entity.isInLava()) {
            entity.setDeltaMovement(movement.add(0, 0.1, 0));

            if (isMoving && entity.level() instanceof ServerLevel level && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
        } else if (entity.level().getFluidState(onPos).is(FluidTags.LAVA)) {
            if (entity.level() instanceof ServerLevel level && isMoving && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
            entity.setDeltaMovement(movement.x(), Math.max(movement.y(), 0D), movement.z());
            entity.setOnGround(true);
        } else if (entity.level().getFluidState(futureBlockPos).is(FluidTags.LAVA) && movement.y() > -0.8) {
            if (entity.level() instanceof ServerLevel level && isMoving && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
            entity.setDeltaMovement(movement.x(), Math.max(movement.y(), movement.y() * 0.5), movement.z());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        if (!boots.isEmpty()) {
            int lavaWalkerLevel = boots.getEnchantmentLevel(
                player.level().holderOrThrow(KNEnchantments.LAVA_WALKER)
            );

            if (lavaWalkerLevel > 0) {
                applyLavaWalkerEffect(player);
            }
        }
    }
}
