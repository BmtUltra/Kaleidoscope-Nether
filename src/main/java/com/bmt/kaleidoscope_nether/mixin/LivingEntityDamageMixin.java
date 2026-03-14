package com.bmt.kaleidoscope_nether.mixin;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDamageMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Holder<MobEffect> starBlessingHolder = KNEffects.STAR_BLESSING;
        if (starBlessingHolder != null && entity.hasEffect(starBlessingHolder)) {
            float reducedDamage = damage * 0.2F;
            cir.setReturnValue(reducedDamage);
        }
    }
}