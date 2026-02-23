package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.UUID;

public class MysteriousPoisonDrunkennessEffect extends MobEffect {
    private static final String MAX_HEALTH_UUID = "e5f6a7b8-c9d0-4e1f-9a2b-3c4d5e6f7a8b";
    private static final UUID MAX_HEALTH_UUID_OBJ = UUID.fromString(MAX_HEALTH_UUID);

    public MysteriousPoisonDrunkennessEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide) {
            if (livingEntity.tickCount % 10 == 0) {
                float reductionPercentage = 0.05f + (amplifier * 0.03f);
                double baseMaxHealth = livingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH);
                double reductionAmount = baseMaxHealth * reductionPercentage;
                double minHealth = baseMaxHealth * 0.01f;
                AttributeModifier existingModifier = livingEntity.getAttribute(Attributes.MAX_HEALTH)
                        .getModifier(MAX_HEALTH_UUID_OBJ);
                double currentReduction = existingModifier != null ? existingModifier.getAmount() : 0;
                double newReduction = currentReduction - reductionAmount;
                double maxAllowedReduction = baseMaxHealth - minHealth;
                if (Math.abs(newReduction) > maxAllowedReduction) {
                    newReduction = -maxAllowedReduction;
                }
                livingEntity.getAttribute(Attributes.MAX_HEALTH)
                        .removeModifier(MAX_HEALTH_UUID_OBJ);
                AttributeModifier modifier = new AttributeModifier(
                        MAX_HEALTH_UUID_OBJ,
                        "mysterious_poison_drunkenness_health_reduction",
                        newReduction,
                        AttributeModifier.Operation.ADDITION
                );
                livingEntity.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(modifier);
                if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
                    livingEntity.setHealth(livingEntity.getMaxHealth());
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, net.minecraft.world.entity.ai.attributes.AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(livingEntity, attributeMap, amplifier);

        if (!livingEntity.level().isClientSide) {
            livingEntity.getAttribute(Attributes.MAX_HEALTH)
                    .removeModifier(MAX_HEALTH_UUID_OBJ);
        }
    }
}