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
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpecialFruitItem extends Item {
    private final RegistryObject<MobEffect> effect;
    private final int duration;
    private final int amplifier;

    public SpecialFruitItem(FoodProperties food, RegistryObject<MobEffect> effect, int duration, int amplifier, Rarity rarity) {
        super(new Item.Properties().food(food).rarity(rarity));
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player && !level.isClientSide()) {
            player.addEffect(new MobEffectInstance(
                    effect.get(),
                    duration,
                    amplifier,
                    false,
                    false,
                    true
            ));
        }

        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        String translationKey = getDescriptionId() + ".tooltip.line1";
        tooltip.add(Component.translatable(translationKey).withStyle(ChatFormatting.BLUE));
    }
}