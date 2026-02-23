package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class WarpedDrunkennessEffect extends MobEffect {
    
    public WarpedDrunkennessEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }
    
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public void onHurt(LivingEntity entity, float damageAmount, int amplifier) {
        float healPercentage = 0.8f + (amplifier * 0.1f);
        float healAmount = damageAmount * healPercentage;
        entity.heal(healAmount);
    }
}