package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.init.soupbase.*;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase.SoupBaseManager;

public class KNSoupBases {
    public static void registerAll() {
        SoupBaseManager.registerSoupBase(new CrimsonBucketSoupBase());
        SoupBaseManager.registerSoupBase(new WarpedBucketSoupBase());
        SoupBaseManager.registerSoupBase(new PoisonousBucketSoupBase());
        SoupBaseManager.registerSoupBase(new SoulBucketSoupBase());
    }
}