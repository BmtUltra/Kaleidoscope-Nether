package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.effect.CrimsonEffect;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import com.bmt.kaleidoscope_nether.registry.KNEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void onGetDamageAfterArmorAbsorb(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        LivingEntity target = (LivingEntity) (Object) this;
        if (damageSource.getEntity() instanceof LivingEntity attacker && attacker.hasEffect(KNEffects.CRIMSON.get())) {
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
                int piercingLevel = EnchantmentHelper.getItemEnchantmentLevel(KNEnchantments.PIERCING.get(), weapon);
                if (piercingLevel > 0) {
                    float armorIgnorePercent = Math.min(piercingLevel * 0.25f, 1.0f);
                    float armorValue = target.getArmorValue();
                    float effectiveArmor = armorValue * (1.0f - armorIgnorePercent);
                    float armorDamageReduction = effectiveArmor * 0.04f;
                    float totalDamageReduction = armorDamageReduction * damage;
                    float finalDamage = damage - totalDamageReduction;
                    finalDamage = Math.max(finalDamage, 0.0f);
                    cir.setReturnValue(finalDamage);
                }
            }
        }
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void onHurt(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity target = (LivingEntity) (Object) this;
        if (damageSource.getDirectEntity() instanceof AbstractArrow arrow) {
            Entity owner = arrow.getOwner();
            if (owner instanceof LivingEntity shooter) {
                ItemStack weapon = shooter.getMainHandItem();
                int anesthesiaLevel = EnchantmentHelper.getItemEnchantmentLevel(KNEnchantments.ANESTHESIA.get(), weapon);
                if (anesthesiaLevel > 0) {
                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180, anesthesiaLevel - 1));
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 180, anesthesiaLevel - 1));
                }
            }
        }
    }
}