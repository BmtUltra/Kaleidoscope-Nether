package com.bmt.kaleidoscope_nether.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class KNAdvancementTriggerRegistry {
    public static final KNAdvancementTrigger SIMPLE_ID = new KNAdvancementTrigger("simple_id");

    public static void init() {
        CriteriaTriggers.register(SIMPLE_ID);
    }
}
