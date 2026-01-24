package com.bmt.kaleidoscope_nether.item.EffectItem;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class SpecialFruitBlockItem extends BlockItem {
    private final RegistryObject<MobEffect> effect;
    private final int duration;
    private final int amplifier;


    public SpecialFruitBlockItem(Block block, FoodProperties food, RegistryObject<MobEffect> effect, int duration, int amplifier, Rarity rarity) {
        super(block, new Item.Properties().food(food).rarity(rarity));

        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public @NotNull String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
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
}
