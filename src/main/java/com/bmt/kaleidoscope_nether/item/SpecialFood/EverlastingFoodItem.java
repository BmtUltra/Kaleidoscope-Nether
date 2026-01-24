package com.bmt.kaleidoscope_nether.item.SpecialFood;

import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class EverlastingFoodItem extends FoodWithEffectsItem {
    private final Supplier<Integer> eatingCooldown;
    private final Supplier<Boolean> isEnabled;

    public EverlastingFoodItem(FoodProperties food, Supplier<Integer> eatingCooldown, Supplier<Boolean> isEnabled, Rarity rarity) {
        super(food);
        this.eatingCooldown = eatingCooldown;
        this.isEnabled = isEnabled;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.kaleidoscope_nether.everlasting_flame_steak.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isEdible()) {
            entity.eat(world, stack.copy());
            addCooldown(entity, eatingCooldown.get());
        }
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!isEnabled.get()) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean isEdible() {
        if (!isEnabled.get()) {
            return false;
        }
        return super.isEdible();
    }

    private void addCooldown(LivingEntity entity, int cooldown) {
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this, cooldown);
        }
    }
}