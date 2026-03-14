package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
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

    public static void registerEvents() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            Holder<MobEffect> glowingHolder = KNEffects.GLOWING;
            if (player.hasEffect(glowingHolder)) {
                applyExperienceBonus(player);
            }
        });

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            Holder<MobEffect> glowingHolder = KNEffects.GLOWING;
            if (newPlayer.hasEffect(glowingHolder)) {
                applyExperienceBonus(newPlayer);
            }
        });
    }

    public static void onExperienceOrbPickup(Player player, ExperienceOrb orb) {
        Holder<MobEffect> glowingHolder = KNEffects.GLOWING;
        if (player.hasEffect(glowingHolder)) {
            int amplifier = Objects.requireNonNull(player.getEffect(glowingHolder)).getAmplifier();
            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
            int originalValue = orb.getValue();
            int bonusValue = (int) (originalValue * (bonusMultiplier - 1.0f));

            if (bonusValue > 0) {
                player.giveExperiencePoints(bonusValue);
            }
        }
    }

    public static int onExperienceChange(Player player, int originalAmount) {
        Holder<MobEffect> glowingHolder = KNEffects.GLOWING;
        if (player.hasEffect(glowingHolder)) {
            int amplifier = Objects.requireNonNull(player.getEffect(glowingHolder)).getAmplifier();
            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
            int bonusAmount = (int) (originalAmount * (bonusMultiplier - 1.0f));

            if (bonusAmount > 0) {
                return originalAmount + bonusAmount;
            }
        }
        return originalAmount;
    }

    private static void applyExperienceBonus(Player player) {
    }
}