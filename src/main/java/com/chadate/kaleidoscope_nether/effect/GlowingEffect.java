package com.chadate.kaleidoscope_nether.effect;

import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GlowingEffect extends MobEffect {

    public GlowingEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        return false;
    }

    @SubscribeEvent
    public static void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        ExperienceOrb orb = event.getOrb();

        if (player.hasEffect(KNEffects.GLOWING)) {
            int amplifier = Objects.requireNonNull(player.getEffect(KNEffects.GLOWING)).getAmplifier();

            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
            int originalValue = orb.getValue();
            int bonusValue = (int) (originalValue * (bonusMultiplier - 1.0f));

            if (bonusValue > 0) {
                player.giveExperiencePoints(bonusValue);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerXpChange(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();

        if (player.hasEffect(KNEffects.GLOWING)) {
            int amplifier = Objects.requireNonNull(player.getEffect(KNEffects.GLOWING)).getAmplifier();

            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
            int originalAmount = event.getAmount();
            int bonusAmount = (int) (originalAmount * (bonusMultiplier - 1.0f));

            if (bonusAmount > 0) {
                event.setAmount(originalAmount + bonusAmount);
            }
        }
    }
}