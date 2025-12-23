package com.bmt.kaleidoscope_nether.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;

public class GlowingFruitItem extends SpecialFruitItem {
    public GlowingFruitItem(FoodProperties food, RegistryObject<MobEffect> effect, int duration, int amplifier, Rarity rarity) {
        super(food, effect, duration, amplifier, rarity);
    }
}