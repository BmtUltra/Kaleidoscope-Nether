package com.bmt.kaleidoscope_nether.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PlaceableFoodItem extends BlockItem {
    private final FoodProperties foodProperties;
    private final int effectDuration;

    public PlaceableFoodItem(Block block, FoodProperties foodProperties, int effectDuration, Item.Properties properties) {
        super(block, properties);
        this.foodProperties = foodProperties;
        this.effectDuration = effectDuration;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        // 优先尝试放置方块
        InteractionResult placementResult = super.useOn(context);
        if (placementResult.consumesAction()) {
            return placementResult;
        }

        // 如果不能放置，则尝试食用
        return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        if (entity instanceof Player player) {
            // 恢复饥饿值和饱和度
            player.getFoodData().eat(foodProperties.getNutrition(), foodProperties.getSaturationModifier());

            // 给予怨魂效果 - 修复引用
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    com.bmt.kaleidoscope_nether.registry.ModEffects.GHOST_BUFF.get(),
                    effectDuration,
                    0
            ));
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 32; // 食用时间（刻）
    }
}