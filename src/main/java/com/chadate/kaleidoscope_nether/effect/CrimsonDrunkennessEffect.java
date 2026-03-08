package com.chadate.kaleidoscope_nether.effect;

import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CrimsonDrunkennessEffect extends MobEffect {

    public CrimsonDrunkennessEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    public static float calculateDamageBonus(LivingEntity attacker, float baseDamage) {
        if (attacker.hasEffect(KNEffects.CRIMSON_DRUNKENNESS)) {
            int amplifier = Objects.requireNonNull(attacker.getEffect(KNEffects.CRIMSON_DRUNKENNESS)).getAmplifier();
            float damageMultiplier = 0.5f + (amplifier * 0.5f);
            return baseDamage * (1.0f + damageMultiplier);
        }
        return baseDamage;
    }

    public static boolean hasCrimsonDrunkenness(LivingEntity entity) {
        return entity.hasEffect(KNEffects.CRIMSON_DRUNKENNESS);
    }
}