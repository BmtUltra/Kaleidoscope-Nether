package com.chadate.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class WarpedDrunkennessEffect extends MobEffect {

    public WarpedDrunkennessEffect(int color) {
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

    public void onHurt(LivingEntity entity, float damageAmount, int amplifier) {
        float healPercentage = 0.8f + (amplifier * 0.1f);
        float healAmount = damageAmount * healPercentage;
        entity.heal(healAmount);
    }
}