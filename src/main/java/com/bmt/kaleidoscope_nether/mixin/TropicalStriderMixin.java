package com.bmt.kaleidoscope_nether.mixin;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
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
public abstract class TropicalStriderMixin extends Entity {
    public TropicalStriderMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Holder<MobEffect> tropicalStriderHolder = KNEffects.TROPICAL_STRIDER;
        if (tropicalStriderHolder != null && entity.hasEffect(tropicalStriderHolder)) {
            if (this.level().dimension().equals(Level.NETHER)) {
                BlockState blockState = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement());
                float friction = blockState.getBlock().getFriction();
                float baseSpeed = 1.0f;
                float frictionBonus = Math.max(1 - friction, 0) * 0.2f;
                float totalSpeed = baseSpeed + frictionBonus;
                float movementEfficiency = (float)entity.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_EFFICIENCY);
                float finalSpeed = net.minecraft.util.Mth.lerp(movementEfficiency, totalSpeed, 1.0f);

                cir.setReturnValue(finalSpeed);
            }
        }
    }
}