package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
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

public class GhostBuffFoodItem extends Item {
    private final int buffDuration;

    public GhostBuffFoodItem(FoodProperties food, int buffDurationInSeconds) {
        super(new Item.Properties().food(food));
        this.buffDuration = buffDurationInSeconds;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            int durationInTicks = buffDuration * 20;
            MobEffectInstance ghostBuff = new MobEffectInstance(
                    KNEffects.GHOST_BUFF.get(),
                    durationInTicks,
                    0,
                    false,
                    false,
                    true
            );

            player.addEffect(ghostBuff);
        }

        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);

        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltipComponents.add(Component.translatable(translationKey).withStyle(ChatFormatting.BLUE));
    }

    public int getBuffDuration() {
        return buffDuration;
    }
}