package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.registry.KNItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = com.chadate.kaleidoscope_nether.KaleidoscopeNether.MOD_ID)
public class ItemTabEventHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            //event.insertAfter(
            //        Items.TIPPED_ARROW.getDefaultInstance(),
            //        KNItems.MYSTERIOUS_POISON_ARROW.get().getDefaultInstance(),
             //       CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            //);

            event.insertAfter(
                Items.CROSSBOW.getDefaultInstance(),
                KNItems.BLOWGUN.get().getDefaultInstance(),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
/*
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(
                    Items.WARPED_FUNGUS_ON_A_STICK.getDefaultInstance(),
                    KNItems.SEED_BAG.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }*/
    }
}