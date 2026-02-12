package com.chadate.kaleidoscope_nether.integration;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModCreativeTabs;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollEntityItem;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class KaleidoscopeDollIntegration {
    private static boolean isLoaded = false;

    private static final Map<ResourceLocation, Block> DOLL_BLOCKS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> DOLL_ITEMS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> ENTITY_DOLL_ITEMS = new LinkedHashMap<>();

    private static final Map<String, String> DOLL_DEFINITIONS = Map.of(
        "doll_0", "contributor_0",
        "doll_1", "contributor_1",
        "doll_2", "contributor_2",
        "doll_3", "contributor_3",
        "doll_4", "contributor_4",
        "doll_5", "contributor_5"
    );

    private static final Map<String, String> ENTITY_DOLL_DEFINITIONS = Map.of(
        "entity_doll_0", "doll_0",
        "entity_doll_1", "doll_1",
        "entity_doll_2", "doll_2",
        "entity_doll_3", "doll_3",
        "entity_doll_4", "doll_4",
        "entity_doll_5", "doll_5"
    );

    public static void init() {
        isLoaded = ModList.get().isLoaded(KaleidoscopeDoll.MOD_ID);
    }

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        if (!isLoaded) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            DOLL_DEFINITIONS.keySet().forEach(dollId -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                DollBlock block = new DollBlock();
                DOLL_BLOCKS.put(id, block);
                event.register(Registries.BLOCK, id, () -> block);
            });
        }
    }

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        if (!isLoaded) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.ITEM)) {
            DOLL_DEFINITIONS.forEach((dollId, tooltipKey) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
                Block block = DOLL_BLOCKS.get(id);
                if (block != null) {
                    DollItem item = new DollItem(block, tooltipKey);
                    DOLL_ITEMS.put(id, item);
                    event.register(Registries.ITEM, id, () -> item);
                }
            });

            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, entityDollId);
                DollEntityItem item = new DollEntityItem();
                ENTITY_DOLL_ITEMS.put(id, item);
                event.register(Registries.ITEM, id, () -> item);
            });
        }
    }

    @SubscribeEvent
    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (!isLoaded) {
            return;
        }
        
        if (event.getTab() == ModCreativeTabs.AUTHOR_DOLL_TAB.get()) {
            DOLL_ITEMS.forEach((id, item) -> event.accept(item));
        }
        
        if (event.getTab() == ModCreativeTabs.ENTITY_DOLL_TAB.get()) {
            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
                ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, blockDollId);
                Block block = DOLL_BLOCKS.get(blockId);
                
                if (block != null) {
                    ItemStack stack = DollEntityItem.createItemWithBlockState(block.defaultBlockState());
                    event.accept(stack);
                }
            });
        }
    }

}
