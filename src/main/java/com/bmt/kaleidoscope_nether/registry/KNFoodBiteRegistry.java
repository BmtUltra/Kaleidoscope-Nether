package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.resources.ResourceLocation;

public class KNFoodBiteRegistry {
    public static ResourceLocation SOUL_LAMB_CHOP;

    public static void init() {
        FoodBiteRegistry registry = new FoodBiteRegistry();
        SOUL_LAMB_CHOP = registry.registerFoodData(KaleidoscopeNether.id("soul_lamb_chop"), FoodBiteRegistry.FoodData
                .create(3, KNFoods.SOUL_LAMB_CHOP_BLOCK, KNFoods.SOUL_LAMB_CHOP_ITEM)
        );

    }

}
