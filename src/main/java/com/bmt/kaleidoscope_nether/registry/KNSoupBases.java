package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.registry.soupbase.MilkBucketSoupBase;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase.SoupBaseManager;
import net.minecraft.resources.ResourceLocation;

public class KNSoupBases {
    public static final ResourceLocation MILK_BUCKET = new ResourceLocation("minecraft", "milk");

    public static void registerAll() {
        SoupBaseManager.registerSoupBase(new MilkBucketSoupBase());
    }
}