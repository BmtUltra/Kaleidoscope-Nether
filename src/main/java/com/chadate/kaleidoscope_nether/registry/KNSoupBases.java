package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.registry.soupbase.*;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase.SoupBaseManager;

public class KNSoupBases {
    public static void registerAll() {
        SoupBaseManager.registerSoupBase(new CrimsonBucketSoupBase());
        SoupBaseManager.registerSoupBase(new WarpedBucketSoupBase());
        SoupBaseManager.registerSoupBase(new PoisonousBucketSoupBase());
        SoupBaseManager.registerSoupBase(new SoulBucketSoupBase());
    }
}