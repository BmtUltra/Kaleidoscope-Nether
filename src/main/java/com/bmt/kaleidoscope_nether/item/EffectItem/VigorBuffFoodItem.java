package com.bmt.kaleidoscope_nether.item.EffectItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VigorBuffFoodItem extends Item {
    private final int buffDuration;

    public VigorBuffFoodItem(FoodProperties food, int buffDurationInSeconds) {
        super(new Item.Properties().food(food));
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            MobEffect vigorEffect = ForgeRegistries.MOB_EFFECTS.getValue(
                    net.minecraft.resources.ResourceLocation.tryBuild("kaleidoscope_cookery", "vigor")
            );
            if (vigorEffect != null) {
                int durationInTicks = buffDuration * 20;
                MobEffectInstance effectInstance = new MobEffectInstance(
                        vigorEffect,
                        durationInTicks,
                        0,
                        false,
                        false,
                        true
                );
                player.addEffect(effectInstance);
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);
        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltipComponents.add(Component.translatable(translationKey).withStyle(ChatFormatting.RED));
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}