package com.bmt.kaleidoscope_nether.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class EverlastingFoodItem extends Item {
    private final Supplier<Integer> eatingCooldown;
    private final Supplier<Boolean> isEnabled;

    public EverlastingFoodItem(FoodProperties food, Supplier<Integer> eatingCooldown, Supplier<Boolean> isEnabled, Rarity rarity) {
        super(new Properties().food(food).rarity(rarity).stacksTo(1)); 
        this.eatingCooldown = eatingCooldown;
        this.isEnabled = isEnabled;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.kaleidoscope_nether.everlasting_flame_steak.tooltip.line1"));
        tooltip.add(Component.translatable("item.kaleidoscope_nether.everlasting_flame_steak.tooltip.line2"));
        tooltip.add(Component.translatable("item.kaleidoscope_nether.everlasting_flame_steak.tooltip.line3"));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isEdible()) {
            entity.eat(world, stack.copy());
            addCooldown(entity, eatingCooldown.get());

            // 给予玩家效果
            if (entity instanceof Player player) {
                // 使用软依赖方式获取效果
                MobEffect warmthEffect = ForgeRegistries.MOB_EFFECTS.getValue(
                        net.minecraft.resources.ResourceLocation.tryBuild("kaleidoscope_cookery", "warmth")
                );
                MobEffect satiatedShieldEffect = ForgeRegistries.MOB_EFFECTS.getValue(
                        net.minecraft.resources.ResourceLocation.tryBuild("kaleidoscope_cookery", "satiated_shield")
                );

                // 温暖效果 - 30秒
                if (warmthEffect != null) {
                    player.addEffect(new MobEffectInstance(warmthEffect, 600, 0));
                }

                // 饱腹代偿 - 15秒
                if (satiatedShieldEffect != null) {
                    player.addEffect(new MobEffectInstance(satiatedShieldEffect, 300, 0));
                }
            }
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