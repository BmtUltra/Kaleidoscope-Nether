package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.Config;
import com.bmt.kaleidoscope_nether.registry.ModAttributes;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StarBlessingEvents {

    private static final UUID STAR_BLESSING_MODIFIER_UUID = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-e1f2a3b4c5d6");
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("9c0d1e2f-3a4b-5c6d-7e8f-a9b0c1d2e3f4");
    private static final UUID ARMOR_TOUGHNESS_MODIFIER_UUID = UUID.fromString("0d1e2f3a-4b5c-6d7e-8f9a-b0c1d2e3f4a5");
    private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.fromString("1e2f3a4b-5c6d-7e8f-9a0b-c1d2e3f4a5b6");

    private static final int MAX_STAR_BLESSING_LEVEL = 12;

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                if (starBlessingLevel >= MAX_STAR_BLESSING_LEVEL &&
                        Config.STAR_BLESSING_LEVEL_12_FIRE_IMMUNITY.get() &&
                        isFireDamage(event.getSource())) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.STAR_BLESSING_BUFF.get())) {
                float reducedDamage = event.getAmount() * 0.5f;
                event.setAmount(reducedDamage);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                updateAttributeModifiers(player, starBlessingLevel);

                if (player.hasEffect(ModEffects.STAR_BLESSING_BUFF.get()) && player.tickCount % 10 == 0) {
                    float maxHealth = player.getMaxHealth();
                    float healAmount = (maxHealth * 0.05f) + 0.5f;
                    player.heal(healAmount);
                }
            }
        }
    }

    private static void updateAttributeModifiers(Player player, int starBlessingLevel) {
        AttributeInstance armorAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR);
        if (armorAttr != null) {
            if (starBlessingLevel >= 3) {
                int armorBonus = Config.STAR_BLESSING_LEVEL_3_ARMOR_BONUS.get();
                if (armorBonus > 0) {
                    AttributeModifier armorModifier = new AttributeModifier(
                            ARMOR_MODIFIER_UUID,
                            "Star Blessing Armor Bonus",
                            armorBonus,
                            AttributeModifier.Operation.ADDITION
                    );
                    if (armorAttr.getModifier(ARMOR_MODIFIER_UUID) == null) {
                        armorAttr.addPermanentModifier(armorModifier);
                    }
                }
            } else {
                armorAttr.removeModifier(ARMOR_MODIFIER_UUID);
            }
        }

        AttributeInstance toughnessAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS);
        if (toughnessAttr != null) {
            if (starBlessingLevel >= 6) {
                int toughnessBonus = Config.STAR_BLESSING_LEVEL_6_TOUGHNESS_BONUS.get();
                if (toughnessBonus > 0) {
                    AttributeModifier toughnessModifier = new AttributeModifier(
                            ARMOR_TOUGHNESS_MODIFIER_UUID,
                            "Star Blessing Armor Toughness Bonus",
                            toughnessBonus,
                            AttributeModifier.Operation.ADDITION
                    );
                    if (toughnessAttr.getModifier(ARMOR_TOUGHNESS_MODIFIER_UUID) == null) {
                        toughnessAttr.addPermanentModifier(toughnessModifier);
                    }
                }
            } else {
                toughnessAttr.removeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID);
            }
        }

        AttributeInstance knockbackResistanceAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE);
        if (knockbackResistanceAttr != null) {
            if (starBlessingLevel >= 9) {
                int knockbackResistanceBonus = Config.STAR_BLESSING_LEVEL_9_KNOCKBACK_RESISTANCE.get();
                if (knockbackResistanceBonus > 0) {
                    AttributeModifier knockbackModifier = new AttributeModifier(
                            KNOCKBACK_RESISTANCE_MODIFIER_UUID,
                            "Star Blessing Knockback Resistance Bonus",
                            knockbackResistanceBonus * 0.1,
                            AttributeModifier.Operation.ADDITION
                    );
                    if (knockbackResistanceAttr.getModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID) == null) {
                        knockbackResistanceAttr.addPermanentModifier(knockbackModifier);
                    }
                }
            } else {
                knockbackResistanceAttr.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(net.minecraftforge.event.entity.living.MobEffectEvent.Added event) {
        if (event.getEffectInstance().getEffect() == ModEffects.STAR_BLESSING_BUFF.get() &&
                event.getEntity() instanceof Player player) {

            List<MobEffect> effectsToRemove = new ArrayList<>();
            for (Map.Entry<MobEffect, MobEffectInstance> entry : player.getActiveEffectsMap().entrySet()) {
                MobEffect effect = entry.getKey();
                if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effect);
                }
            }

            for (MobEffect effect : effectsToRemove) {
                player.removeEffect(effect);
            }

            float maxHealth = player.getMaxHealth();
            float absorptionAmount = maxHealth * 0.5f;
            player.setAbsorptionAmount(player.getAbsorptionAmount() + absorptionAmount);
        }
    }

    private static int getStarBlessingLevel(AttributeInstance attribute) {
        AttributeModifier mainModifier = attribute.getModifier(STAR_BLESSING_MODIFIER_UUID);
        if (mainModifier != null) {
            return (int) mainModifier.getAmount();
        }
        return (int) attribute.getBaseValue();
    }

    private static boolean isFireDamage(DamageSource source) {
        return source.is(DamageTypeTags.IS_FIRE);
    }
}