package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class CrimsonEffect extends MobEffect {
    public CrimsonEffect(int color) {
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
        Holder<MobEffect> crimsonHolder = KNEffects.CRIMSON;
        if (attacker.hasEffect(crimsonHolder)) {
            return baseDamage * 1.2f;
        }
        return baseDamage;
    }

    public static float calculateArmorPenetration(LivingEntity attacker, LivingEntity target) {
        Holder<MobEffect> crimsonHolder = KNEffects.CRIMSON;
        if (attacker.hasEffect(crimsonHolder)) {
            float armorValue = target.getArmorValue();
            float armorPenetration = armorValue * 0.3f + 10.0f;
            return Math.max(0, armorValue - armorPenetration);
        }
        return target.getArmorValue();
    }

    @Deprecated
    @SuppressWarnings("unused")
    public static float getDamageMultiplier(LivingEntity attacker, LivingEntity target) {
        Holder<MobEffect> crimsonHolder = KNEffects.CRIMSON;
        if (attacker.hasEffect(crimsonHolder)) {
            return 1.2f;
        }
        return 1.0f;
    }
}