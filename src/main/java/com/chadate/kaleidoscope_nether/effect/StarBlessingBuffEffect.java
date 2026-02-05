package com.chadate.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class StarBlessingBuffEffect extends MobEffect {
    public StarBlessingBuffEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            if (player.tickCount % 10 == 0) {
                float maxHealth = player.getMaxHealth();
                float healPercentage = 0.05f + (amplifier * 0.05f);
                float healAmount = maxHealth * healPercentage;
                player.heal(healAmount);
            }
        }
        return false;
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    public static float getDamageReduction(float originalDamage, int amplifier) {
        float baseReduction = 0.5f;
        float additionalReduction = amplifier * 0.1f;
        float totalReduction = baseReduction + additionalReduction;
        return originalDamage * (1.0f - totalReduction);
    }
}