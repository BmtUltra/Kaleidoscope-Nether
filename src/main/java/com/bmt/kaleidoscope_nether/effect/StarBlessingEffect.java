package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class StarBlessingEffect extends MobEffect {
    public StarBlessingEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            if (player.tickCount % 10 == 0) {
                float maxHealth = player.getMaxHealth();
                float healAmount = maxHealth * 0.02f;
                player.heal(healAmount);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}