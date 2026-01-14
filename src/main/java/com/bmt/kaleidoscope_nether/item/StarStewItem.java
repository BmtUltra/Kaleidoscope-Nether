package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.Config;
import com.bmt.kaleidoscope_nether.registry.ModAttributes;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class StarStewItem extends Item {
    private static final UUID STAR_BLESSING_MODIFIER_UUID = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-e1f2a3b4c5d6");
    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("4d5e6f7a-8b9c-0d1e-2f3a-b4c5d6e7f8a9");
    private static final TagKey<net.minecraft.world.item.Item> STAR_BLESSING_FOODS =
            ItemTags.create(KaleidoscopeNether.id( "star_blessing_foods"));

    private static final int MAX_STAR_BLESSING_LEVEL = 12;

    public StarStewItem(FoodProperties food) {
        super(new Properties().food(food));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        String translationKey1 = getDescriptionId() + ".tooltip.line1";
        String translationKey2 = getDescriptionId() + ".tooltip.line2";

        tooltip.add(Component.translatable(translationKey1).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(translationKey2).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(ModEffects.STAR_BLESSING_BUFF.get(), 600, 0));

            if (stack.is(STAR_BLESSING_FOODS)) {
                if (level.random.nextFloat() < 0.4f) {
                    AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());
                    if (starBlessingAttr != null) {
                        int currentLevel = getCurrentStarBlessingLevel(starBlessingAttr);

                        if (currentLevel < MAX_STAR_BLESSING_LEVEL) {
                            starBlessingAttr.removeModifier(STAR_BLESSING_MODIFIER_UUID);
                            AttributeModifier modifier = new AttributeModifier(
                                    STAR_BLESSING_MODIFIER_UUID,
                                    "Star Blessing Increase",
                                    currentLevel + 1.0,
                                    AttributeModifier.Operation.ADDITION
                            );
                            starBlessingAttr.addPermanentModifier(modifier);

                            updatePlayerMaxHealth(player, currentLevel + 1);
                            spawnStarBlessingParticles(level, player);
                        }
                    }
                }
            }
        }

        return result;
    }

    private int getCurrentStarBlessingLevel(AttributeInstance attribute) {
        AttributeModifier mainModifier = attribute.getModifier(STAR_BLESSING_MODIFIER_UUID);
        if (mainModifier != null) {
            return (int) mainModifier.getAmount();
        }
        return (int) attribute.getBaseValue();
    }

    private void updatePlayerMaxHealth(Player player, int starBlessingLevel) {
        float healthIncrease = starBlessingLevel * 2.0f;

        player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .removeModifier(HEALTH_MODIFIER_UUID);

        AttributeModifier healthModifier = new AttributeModifier(
                HEALTH_MODIFIER_UUID,
                "Star Blessing Health Bonus",
                healthIncrease,
                AttributeModifier.Operation.ADDITION
        );
        player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .addPermanentModifier(healthModifier);

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    private void spawnStarBlessingParticles(Level level, Player player) {
        if (!level.isClientSide() && level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            double x = player.getX();
            double y = player.getY() + player.getEyeHeight();
            double z = player.getZ();

            int particleCount = 30;

            for (int i = 0; i < particleCount; i++) {
                double angle = level.random.nextDouble() * Math.PI * 2;
                double radius = level.random.nextDouble() * 0.5;

                double offsetX = Math.cos(angle) * radius;
                double offsetY = level.random.nextDouble() * player.getBbHeight() - player.getBbHeight() * 0.5;
                double offsetZ = Math.sin(angle) * radius;

                double speedX = Math.cos(angle) * 0.1;
                double speedY = level.random.nextDouble() * 0.05 - 0.025;
                double speedZ = Math.sin(angle) * 0.1;

                serverLevel.sendParticles(
                        ParticleTypes.GLOW,
                        x + offsetX,
                        y + offsetY,
                        z + offsetZ,
                        1,
                        speedX, speedY, speedZ,
                        0.0
                );
            }

            for (int i = 0; i < 10; i++) {
                double angle = level.random.nextDouble() * Math.PI * 2;
                double radius = level.random.nextDouble() * 0.3;

                double offsetX = Math.cos(angle) * radius;
                double offsetY = level.random.nextDouble() * player.getBbHeight() * 0.3 - player.getBbHeight() * 0.15;
                double offsetZ = Math.sin(angle) * radius;

                double speedX = Math.cos(angle) * 0.08;
                double speedY = level.random.nextDouble() * 0.03 - 0.015;
                double speedZ = Math.sin(angle) * 0.08;

                serverLevel.sendParticles(
                        ParticleTypes.END_ROD,
                        x + offsetX,
                        y + offsetY,
                        z + offsetZ,
                        1,
                        speedX, speedY, speedZ,
                        0.0
                );
            }

            for (int i = 0; i < 8; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * player.getBbWidth();
                double offsetY = level.random.nextDouble() * player.getBbHeight() * 0.5;
                double offsetZ = (level.random.nextDouble() - 0.5) * player.getBbWidth();

                double speedX = (level.random.nextDouble() - 0.5) * 0.02;
                double speedY = level.random.nextDouble() * 0.08 + 0.04;
                double speedZ = (level.random.nextDouble() - 0.5) * 0.02;

                serverLevel.sendParticles(
                        ParticleTypes.GLOW,
                        x + offsetX,
                        y + offsetY,
                        z + offsetZ,
                        1,
                        speedX, speedY, speedZ,
                        0.0
                );
            }
        }
    }
}