package com.bmt.kaleidoscope_nether.item.EffectItem;

import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CataclysmBuffFoodItem extends BowlFoodOnlyItem {
    private final int buffDuration;

    public CataclysmBuffFoodItem(FoodProperties food, int buffDurationInSeconds) {
        super(food);
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof Player player) {
            MobEffect cataclysmEffect = ForgeRegistries.MOB_EFFECTS.getValue(
                    net.minecraft.resources.ResourceLocation.tryBuild("cataclysm", "monstrous")
            );

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
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);

        tooltipComponents.add(Component.empty());

        tooltipComponents.add(Component.translatable("item.kaleidoscope_nether.giant_beast_croissant.tooltip.line1")
                .withStyle(ChatFormatting.DARK_PURPLE));
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}