package com.chadate.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.chadate.kaleidoscope_nether.effect.CrimsonDrunkennessEffect;
import com.chadate.kaleidoscope_nether.effect.CrimsonEffect;
import com.chadate.kaleidoscope_nether.registry.KNEffects;
import com.chadate.kaleidoscope_nether.registry.KNEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        LivingEntity target = (LivingEntity) (Object) this;

        if (damageSource.getEntity() instanceof LivingEntity attacker && CrimsonDrunkennessEffect.hasCrimsonDrunkenness(attacker)) {
            float effectiveArmor = 0.0f;
            float armorDamageReduction = effectiveArmor * 0.04f;
            float totalDamageReduction = armorDamageReduction * damage;
            float finalDamage = damage - totalDamageReduction;

            finalDamage = CrimsonDrunkennessEffect.calculateDamageBonus(attacker, finalDamage);
            cir.setReturnValue(finalDamage);
            return;
        }

        if (damageSource.getEntity() instanceof LivingEntity attacker && attacker.hasEffect(KNEffects.CRIMSON)) {
            float effectiveArmor = CrimsonEffect.calculateArmorPenetration(attacker, target);
            float armorDamageReduction = effectiveArmor * 0.04f;
            float totalDamageReduction = armorDamageReduction * damage;
            float finalDamage = damage - totalDamageReduction;
            finalDamage = CrimsonEffect.calculateDamageBonus(attacker, finalDamage);
            cir.setReturnValue(finalDamage);
            return;
        }

        if (damageSource.getDirectEntity() instanceof AbstractArrow arrow) {
            Entity owner = arrow.getOwner();
            if (owner instanceof LivingEntity shooter) {
                ItemStack weapon = shooter.getMainHandItem();
                int piercingLevel = weapon.getEnchantmentLevel(shooter.level().holderOrThrow(KNEnchantments.PIERCING));
                if (piercingLevel > 0) {
                    float finalDamage = kaleidoscope_Nether$getFinalDamage(damage, piercingLevel, target);
                    cir.setReturnValue(finalDamage);
                }
            }
        }
    }

    @Unique
    private static float kaleidoscope_Nether$getFinalDamage(float damage, int piercingLevel, LivingEntity target) {
        float armorIgnorePercent = Math.min(piercingLevel * 0.25f, 1.0f);
        float armorValue = target.getArmorValue();
        float effectiveArmor = armorValue * (1.0f - armorIgnorePercent);
        float armorDamageReduction = effectiveArmor * 0.04f;
        float totalDamageReduction = armorDamageReduction * damage;
        float finalDamage = damage - totalDamageReduction;
        finalDamage = Math.max(finalDamage, 0.0f);
        return finalDamage;
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void onHurt(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity target = (LivingEntity) (Object) this;
        if (damageSource.getDirectEntity() instanceof AbstractArrow arrow) {
            Entity owner = arrow.getOwner();
            if (owner instanceof LivingEntity shooter) {
                ItemStack weapon = shooter.getMainHandItem();
                int anesthesiaLevel = weapon.getEnchantmentLevel(shooter.level().holderOrThrow(KNEnchantments.ANESTHESIA));
                if (anesthesiaLevel > 0) {
                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180, anesthesiaLevel - 1));
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 180, anesthesiaLevel - 1));
                }
            }
        }
    }
}