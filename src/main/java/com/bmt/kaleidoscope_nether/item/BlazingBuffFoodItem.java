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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlazingBuffFoodItem extends Item {
    private final int buffDuration; // 持续时间（秒）

    public BlazingBuffFoodItem(FoodProperties food, int buffDurationInSeconds) {
        super(new Item.Properties().food(food));
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            // 给予火热效果
            int durationInTicks = buffDuration * 20;
            MobEffectInstance blazingEffect = new MobEffectInstance(
                    ModEffects.BLAZING.get(),
                    durationInTicks,
                    0, // 等级
                    false, // 环境效果
                    false,  // 显示粒子
                    true   // 显示图标
            );

            player.addEffect(blazingEffect);
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);

        // 直接使用翻译键，不传递参数
        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltipComponents.add(Component.translatable(translationKey).withStyle(ChatFormatting.GOLD));
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}