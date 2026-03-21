package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.registry.soupbase.CrimsonBucketSoupBase;
import com.bmt.kaleidoscope_nether.registry.soupbase.PoisonousBucketSoupBase;
import com.bmt.kaleidoscope_nether.registry.soupbase.SoulBucketSoupBase;
import com.bmt.kaleidoscope_nether.registry.soupbase.WarpedBucketSoupBase;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase.SoupBaseManager;

public class KNSoupBases {
    public static void registerAll() {
        SoupBaseManager.registerSoupBase(new CrimsonBucketSoupBase());
        SoupBaseManager.registerSoupBase(new WarpedBucketSoupBase());
        SoupBaseManager.registerSoupBase(new PoisonousBucketSoupBase());
        SoupBaseManager.registerSoupBase(new SoulBucketSoupBase());
    }
}