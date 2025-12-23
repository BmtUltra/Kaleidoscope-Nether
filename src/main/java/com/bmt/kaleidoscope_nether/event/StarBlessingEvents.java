package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
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
    private static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("6f7a8b9c-0d1e-2f3a-4b5c-d6e7f8a9b0c1");
    private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.fromString("7a8b9c0d-1e2f-3a4b-5c6d-e7f8a9b0c1d2");
    private static final UUID HUNGER_MODIFIER_UUID = UUID.fromString("8b9c0d1e-2f3a-4b5c-6d7e-f8a9b0c1d2e3");

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                // 15：完全免疫火焰伤害
                if (starBlessingLevel >= 15 && isFireDamage(event.getSource())) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                // 6级：受到的伤害降低10%
                if (starBlessingLevel >= 6) {
                    float reducedDamage = event.getAmount() * 0.9f;
                    event.setAmount(reducedDamage);
                }

                // buff：受到的伤害降低50%
                if (player.hasEffect(ModEffects.STAR_BLESSING_BUFF.get())) {
                    float reducedDamage = event.getAmount() * 0.5f;
                    event.setAmount(reducedDamage);
                }
            }
        }

        // 3级：造成的伤害提升10%（任何地方都生效）
        if (event.getSource().getEntity() instanceof Player attacker) {
            AttributeInstance starBlessingAttr = attacker.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                if (starBlessingLevel >= 3) {
                    float increasedDamage = event.getAmount() * 1.1f;
                    event.setAmount(increasedDamage);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
            if (starBlessingAttr != null) {
                int starBlessingLevel = getStarBlessingLevel(starBlessingAttr);

                // 等级9：移动速度提升6%（任何地方都生效）
                if (starBlessingLevel >= 9) {
                    AttributeInstance movementAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED);
                    if (movementAttr != null && movementAttr.getModifier(MOVEMENT_SPEED_MODIFIER_UUID) == null) {
                        AttributeModifier speedModifier = new AttributeModifier(
                                MOVEMENT_SPEED_MODIFIER_UUID,
                                "Star Blessing Speed Bonus",
                                0.06,
                                AttributeModifier.Operation.MULTIPLY_TOTAL
                        );
                        movementAttr.addPermanentModifier(speedModifier);
                    }
                } else {
                    AttributeInstance movementAttr = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED);
                    if (movementAttr != null) {
                        movementAttr.removeModifier(MOVEMENT_SPEED_MODIFIER_UUID);
                    }
                }

                if (starBlessingLevel >= 12) {
                    // 每tick减少饥饿值消耗
                    if (player.tickCount % 20 == 0) { // 每秒执行一次
                        if (player.getFoodData().needsFood()) {
                            // 减少饥饿值消耗
                            float exhaustion = player.getFoodData().getExhaustionLevel();
                            if (exhaustion > 0) {
                                player.getFoodData().setExhaustion(exhaustion * 0.88f); // 减少12%
                            }
                        }
                    }
                }

                // 星之祝福buff：每10tick恢复5%生命值+0.5生命值
                if (player.hasEffect(ModEffects.STAR_BLESSING_BUFF.get()) && player.tickCount % 10 == 0) {
                    float maxHealth = player.getMaxHealth();
                    float healAmount = (maxHealth * 0.05f) + 0.5f;
                    player.heal(healAmount);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(net.minecraftforge.event.entity.living.MobEffectEvent.Added event) {
        // 星之祝福buff：立刻清除全部debuff
        if (event.getEffectInstance().getEffect() == ModEffects.STAR_BLESSING_BUFF.get() &&
                event.getEntity() instanceof Player player) {

            // 安全地清除所有负面效果 - 先收集再移除
            List<MobEffect> effectsToRemove = new ArrayList<>();
            for (Map.Entry<MobEffect, MobEffectInstance> entry : player.getActiveEffectsMap().entrySet()) {
                MobEffect effect = entry.getKey();
                if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effect);
                }
            }

            // 移除收集到的负面效果
            for (MobEffect effect : effectsToRemove) {
                player.removeEffect(effect);
            }

            // 立刻获得生命值50%的黄心（吸收生命值）
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