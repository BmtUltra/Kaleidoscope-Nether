package com.bmt.kaleidoscope_nether.item.EffectItem;

import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CataclysmBuffFoodItem extends BowlFoodOnlyItem {
    private final int buffDuration;

    public CataclysmBuffFoodItem(FoodProperties food, int buffDurationInSeconds) {
        super(food);
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof Player player) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath("cataclysm", "monstrous");
            Holder<MobEffect> cataclysmEffect = BuiltInRegistries.MOB_EFFECT.getHolder(id).orElse(null);
            if (cataclysmEffect != null) {
                int durationInTicks = buffDuration * 20;
                MobEffectInstance effectInstance = new MobEffectInstance(
                        cataclysmEffect,
                        durationInTicks,
                        3,
                        false,
                        false,
                        true
                );
                player.addEffect(effectInstance);
            }
        }
        return resultStack;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        tooltip.add(Component.empty());

        tooltip.add(Component.translatable("item.kaleidoscope_nether.giant_beast_croissant.tooltip.line1")
                .withStyle(ChatFormatting.DARK_PURPLE));
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}