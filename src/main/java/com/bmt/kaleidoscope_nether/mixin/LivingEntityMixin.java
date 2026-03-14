package com.bmt.kaleidoscope_nether.mixin;

import com.bmt.kaleidoscope_nether.effect.CrimsonEffect;
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
public class LivingEntityMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        LivingEntity target = (LivingEntity) (Object) this;

        if (damageSource.getEntity() instanceof LivingEntity attacker) {
            Holder<MobEffect> crimsonHolder = KNEffects.CRIMSON;
            if (crimsonHolder != null && attacker.hasEffect(crimsonHolder)) {
                float effectiveArmor = CrimsonEffect.calculateArmorPenetration(attacker, target);
                float armorDamageReduction = effectiveArmor * 0.04f;
                float totalDamageReduction = armorDamageReduction * damage;
                float finalDamage = damage - totalDamageReduction;
                finalDamage = CrimsonEffect.calculateDamageBonus(attacker, finalDamage);
                cir.setReturnValue(finalDamage);
            }
        }
    }
}