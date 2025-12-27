package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysteriousPoisonFoodItem extends Item {
    private final int effectDuration;
    private final int effectAmplifier;

    public MysteriousPoisonFoodItem(FoodProperties food, int durationInSeconds, int amplifier, Rarity rarity) {
        super(new Item.Properties().food(food).rarity(rarity));
        this.effectDuration = durationInSeconds;
        this.effectAmplifier = amplifier;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof Player player) {
            int durationInTicks = effectDuration * 20;
            MobEffectInstance poisonEffect = new MobEffectInstance(
                    ModEffects.MYSTERIOUS_POISON.get(),
                    durationInTicks,
                    effectAmplifier,
                    false,
                    false,
                    true
            );

            player.addEffect(poisonEffect);
        }

        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltip.add(Component.translatable(translationKey).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }