package com.chadate.kaleidoscope_nether.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class MysteriousPoisonDrunkennessEffect extends MobEffect {
    private static final ResourceLocation MAX_HEALTH_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_nether", "mysterious_poison_drunkenness_health_reduction");

    public MysteriousPoisonDrunkennessEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide) {
            if (livingEntity.tickCount % 10 == 0) {
                float reductionPercentage = 0.05f + (amplifier * 0.03f);
                double baseMaxHealth = livingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH);
                double reductionAmount = baseMaxHealth * reductionPercentage;
                double minHealth = baseMaxHealth * 0.01f;
                AttributeInstance attributeInstance = livingEntity.getAttribute(Attributes.MAX_HEALTH);
                AttributeModifier existingModifier = attributeInstance != null
                        ? attributeInstance.getModifier(MAX_HEALTH_MODIFIER_ID)
                        : null;
                double currentReduction = existingModifier != null ? existingModifier.amount() : 0;
                double newReduction = currentReduction - reductionAmount;
                double maxAllowedReduction = baseMaxHealth - minHealth;
                if (Math.abs(newReduction) > maxAllowedReduction) {
                    newReduction = -maxAllowedReduction;
                }
                if (attributeInstance != null) {
                    attributeInstance.removeModifier(MAX_HEALTH_MODIFIER_ID);
                    AttributeModifier modifier = new AttributeModifier(
                            MAX_HEALTH_MODIFIER_ID,
                            newReduction,
                            AttributeModifier.Operation.ADD_VALUE
                    );
                    attributeInstance.addTransientModifier(modifier);
                }
                if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
                    livingEntity.setHealth(livingEntity.getMaxHealth());
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(@NotNull AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        AttributeInstance attributeInstance = attributeMap.getInstance(Attributes.MAX_HEALTH);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(MAX_HEALTH_MODIFIER_ID);
        }
    }
}