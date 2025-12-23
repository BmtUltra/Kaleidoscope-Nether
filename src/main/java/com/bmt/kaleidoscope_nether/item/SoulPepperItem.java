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

public class SoulPepperItem extends Item {
    private final int buffDuration; // 持续时间（秒）

    public SoulPepperItem(FoodProperties food, int buffDurationInSeconds) {
        super(new Item.Properties().food(food));
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);

        // 添加怨魂buff的tooltip
        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltipComponents.add(Component.translatable(translationKey).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            // 给予冤魂buff效果
            int durationInTicks = buffDuration * 20;
            MobEffectInstance ghostBuff = new MobEffectInstance(
                    ModEffects.GHOST_BUFF.get(),
                    durationInTicks,
                    0, // 等级
                    false, // 环境效果
                    false, // 显示粒子
                    true // 显示图标
            );

            player.addEffect(ghostBuff);

            // 损失25%生命值
            float maxHealth = player.getMaxHealth();
            float damageAmount = maxHealth * 0.25f;

            // 确保至少造成1点伤害
            if (damageAmount < 1.0f) {
                damageAmount = 1.0f;
            }

            // 造成伤害
            player.hurt(player.damageSources().magic(), damageAmount);
        }

        return super.finishUsingItem(stack, level, entity);
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}