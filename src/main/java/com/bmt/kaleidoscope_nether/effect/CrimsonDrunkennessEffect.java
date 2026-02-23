package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CrimsonDrunkennessEffect extends MobEffect {

    public CrimsonDrunkennessEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static float calculateDamageBonus(LivingEntity attacker, float baseDamage) {
        if (attacker.hasEffect(KNEffects.CRIMSON_DRUNKENNESS.get())) {
            int amplifier = attacker.getEffect(KNEffects.CRIMSON_DRUNKENNESS.get()).getAmplifier();
            float damageMultiplier = 0.5f + (amplifier * 0.5f);
            return baseDamage * (1.0f + damageMultiplier);
        }
        return baseDamage;
    }

    public static boolean hasCrimsonDrunkenness(LivingEntity entity) {
        return entity.hasEffect(KNEffects.CRIMSON_DRUNKENNESS.get());
    }
}