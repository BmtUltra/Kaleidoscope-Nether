package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class LavaWalkerEffect extends MobEffect {
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");

    public LavaWalkerEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        boolean isInNether = livingEntity.level().dimension().equals(Level.NETHER);
        if (isInNether) {
            applySpeedBoost(livingEntity, true);
        } else {
            applySpeedBoost(livingEntity, false);
        }
    }

    private void applySpeedBoost(LivingEntity livingEntity, boolean shouldBoost) {
        livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_UUID);

        if (shouldBoost) {
            AttributeModifier speedModifier = new AttributeModifier(
                    SPEED_MODIFIER_UUID,
                    "Lava Walker Speed Boost",
                    0.30,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            if (!livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(speedModifier)) {
                livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(speedModifier);
            }
        }
    }
}