package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasEffect(KNEffects.LAVA_WALKER.get())) {
            if (this.level().dimension().equals(Level.NETHER)) {
                BlockState blockState = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement());
                float friction = blockState.getBlock().getFriction();
                float baseSpeed = 1.0f;
                float frictionBonus = Math.max(1 - friction, 0) * 0.2f;
                float totalSpeed = baseSpeed + frictionBonus;
                
                cir.setReturnValue(totalSpeed);
            }
        }
    }
}