package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class MysteriousPoisonEffect extends MobEffect {
    
    public MysteriousPoisonEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }
    
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            float maxHealth = livingEntity.getMaxHealth();
            float damage = maxHealth * 0.01f + amplifier * 0.5f;

            damage = Math.max(damage, 1.0f);

            livingEntity.hurt(livingEntity.damageSources().magic(), damage);
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int interval = 20 - (amplifier * 3);
        interval = Math.max(interval, 5);

        return duration % interval == 0;
    }
    
    @Override
    public void addAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(livingEntity, attributeMap, amplifier);
    }
    
    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(livingEntity, attributeMap, amplifier);
    }
}