package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.registry.KNBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public final class DrinkBlockEntityTypeEventHandler {

    private static final ResourceLocation TAVERN_DRINK_BE = ResourceLocation.tryBuild("kaleidoscope_tavern", "drink");

    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        if (!ModList.get().isLoaded("kaleidoscope_tavern")) {
            return;
        }
        BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(TAVERN_DRINK_BE).ifPresent(drinkType -> event.modify(drinkType, KNBlocks.WARPED_CONNY.get()));
    }
}
