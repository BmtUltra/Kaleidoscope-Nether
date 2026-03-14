package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CrimsonEffect extends MobEffect {
    public CrimsonEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static float calculateDamageBonus(LivingEntity attacker, float baseDamage) {
        if (attacker.hasEffect(KNEffects.CRIMSON.get())) {
            int amplifier = Objects.requireNonNull(attacker.getEffect(KNEffects.CRIMSON.get())).getAmplifier();
            float multiplier = 1.5f + (amplifier * 0.5f);
            return baseDamage * multiplier;
        }
        return baseDamage;
    }

    public static float calculateArmorPenetration(LivingEntity attacker, LivingEntity target) {
        if (attacker.hasEffect(KNEffects.CRIMSON.get())) {
            float armorValue = target.getArmorValue();
            float armorPenetration = armorValue * 0.3f + 10.0f;
            return Math.max(0, armorValue - armorPenetration);
        }
        return target.getArmorValue();
    }

    @Deprecated
    @SuppressWarnings("unused")
    public static float getDamageMultiplier(LivingEntity attacker, LivingEntity target) {
        if (attacker.hasEffect(KNEffects.CRIMSON.get())) {
            int amplifier = Objects.requireNonNull(attacker.getEffect(KNEffects.CRIMSON.get())).getAmplifier();
            return 1.5f + (amplifier * 0.5f);
        }
        return 1.0f;
    }
}