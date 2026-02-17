package com.chadate.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.chadate.kaleidoscope_nether.effect.CrimsonEffect;
import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        if (damageSource.getEntity() instanceof LivingEntity attacker && attacker.hasEffect(KNEffects.CRIMSON)) {
            LivingEntity target = (LivingEntity) (Object) this;
            float effectiveArmor = CrimsonEffect.calculateArmorPenetration(attacker, target);
            float armorDamageReduction = effectiveArmor * 0.04f;
            float totalDamageReduction = armorDamageReduction * damage;
            float finalDamage = damage - totalDamageReduction;
            finalDamage = CrimsonEffect.calculateDamageBonus(attacker, finalDamage);
            cir.setReturnValue(finalDamage);
        }
    }
}