package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.registry.KNItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemTabEventHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            //event.getEntries().putAfter(
            //        Items.TIPPED_ARROW.getDefaultInstance(),
            //        KNItems.MYSTERIOUS_POISON_ARROW.get().getDefaultInstance(),
             //       CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            //);

            event.getEntries().putAfter(
                    Items.CROSSBOW.getDefaultInstance(),
                    KNItems.BLOWGUN.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(
                    Items.WARPED_FUNGUS_ON_A_STICK.getDefaultInstance(),
                    KNItems.SEED_BAG.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }
}