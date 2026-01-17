package com.bmt.kaleidoscope_nether.registry;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public interface KNFoods {

    FoodProperties SOUL_LAMB_CHOP_ITEM = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.9F)
            .effect(() -> {
                return new MobEffectInstance(KNEffects.GHOST_BUFF.get(), 180 * 20);
            }, 1.0F).meat().build();

    FoodProperties SOUL_LAMB_CHOP_BLOCK = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.9F)
            .effect(() -> {
                return new MobEffectInstance(KNEffects.GHOST_BUFF.get(), 240 * 20);
            }, 1.0F).meat().build();
}
