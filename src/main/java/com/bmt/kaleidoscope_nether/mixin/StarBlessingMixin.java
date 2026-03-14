package com.bmt.kaleidoscope_nether.mixin;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class StarBlessingMixin extends Entity {
    public StarBlessingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"), cancellable = true)
    private void onAddEffect(MobEffectInstance effectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        Holder<MobEffect> starBlessingHolder = KNEffects.STAR_BLESSING;
        if (starBlessingHolder != null && livingEntity.hasEffect(starBlessingHolder)) {
            MobEffect effect = effectInstance.getEffect().value();
            if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    private void onKnockback(double strength, double x, double z, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        Holder<MobEffect> starBlessingHolder = KNEffects.STAR_BLESSING;
        if (starBlessingHolder != null && livingEntity.hasEffect(starBlessingHolder)) {
            ci.cancel();
        }
    }

    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V")
    )
    private void onHurtKnockback(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        Holder<MobEffect> starBlessingHolder = KNEffects.STAR_BLESSING;
        if (starBlessingHolder != null) {
            livingEntity.hasEffect(starBlessingHolder);
        }
    }
}