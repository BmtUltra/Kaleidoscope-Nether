package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabEventHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceLocation artifactsTab = ResourceLocation.fromNamespaceAndPath("artifacts", "main");
        if (event.getTabKey().location().equals(artifactsTab)) {
            event.accept(ModItems.EVERLASTING_FLAME_STEAK.get());
        }
    }
}