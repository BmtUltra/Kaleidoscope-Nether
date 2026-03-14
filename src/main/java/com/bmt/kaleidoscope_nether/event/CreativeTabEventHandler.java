//package com.bmt.kaleidoscope_nether.event;
//
//import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
//import com.bmt.kaleidoscope_nether.registry.KNItems;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.ItemStack;
//
//import java.util.Objects;
//
//public class CreativeTabEventHandler {
//
//    public static void registerEvents() {
//        ItemGroupEvents.modifyEntriesEvent(ResourceLocation.fromNamespaceAndPath("artifacts", "main"))
//                .register(entries -> {
//                    entries.accept(KNItems.EVERLASTING_FLAME_STEAK);
//                });
//
//        ItemGroupEvents.modifyEntriesEvent(ResourceLocation.fromNamespaceAndPath("kaleidoscope_cookery", "cookery_food"))
//                .register(entries -> {
//                    removeModItems(entries);
//                });
//
//        KaleidoscopeNether.LOGGER.info("Registered CreativeTabEventHandler");
//    }
//
//    private static void removeModItems(FabricItemGroupEntries entries) {
//        var itemsToRemove = entries.getDisplayStacks().stream()
//                .filter(itemStack -> {
//                    var key = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
//                    return key != null && key.getNamespace().equals(KaleidoscopeNether.MOD_ID);
//                })
//                .toList();
//
//        for (ItemStack itemStack : itemsToRemove) {
//            entries.remove(itemStack);
//        }
//    }
//}