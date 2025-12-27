package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class GlowingBuffEffect extends MobEffect {

    private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("1eaf83ff-7207-4a6d-8e7a-123456789abc");
    private static final UUID ATTACK_SPEED_UUID = UUID.fromString("2eaf83ff-7207-4a6d-8e7a-123456789def");

    private static final double MOVEMENT_SPEED_BONUS = 0.10;
    private static final double ATTACK_SPEED_BONUS = 0.20;

    private static final AttributeModifier MOVEMENT_SPEED_MODIFIER = new AttributeModifier(
            MOVEMENT_SPEED_UUID,
            "Glowing movement speed bonus",
            MOVEMENT_SPEED_BONUS,
            AttributeModifier.Operation.MULTIPLY_TOTAL
    );

    private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(
            ATTACK_SPEED_UUID,
            "Glowing attack speed bonus",
            ATTACK_SPEED_BONUS,
            AttributeModifier.Operation.MULTIPLY_TOTAL
    );

    public GlowingBuffEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MOVEMENT_SPEED_MODIFIER)) {
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(MOVEMENT_SPEED_MODIFIER);
        }

        if (!livingEntity.getAttribute(Attributes.ATTACK_SPEED).hasModifier(ATTACK_SPEED_MODIFIER)) {
            livingEntity.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(ATTACK_SPEED_MODIFIER);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, net.minecraft.world.entity.ai.attributes.AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(livingEntity, attributeMap, amplifier);

        if (livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MOVEMENT_SPEED_MODIFIER)) {
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MOVEMENT_SPEED_MODIFIER);
        }

        if (livingEntity.getAttribute(Attributes.ATTACK_SPEED).hasModifier(ATTACK_SPEED_MODIFIER)) {
            livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_MODIFIER);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}