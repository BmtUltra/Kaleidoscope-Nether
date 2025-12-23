package com.bmt.kaleidoscope_nether.API;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface KNTags {
    interface Blocks {
        TagKey<Block> SOUL_SOIL_SAND = register("soul_soil_sand");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name));
        }
    }

    interface Items {
        TagKey<Item> SOUL_PEPPER_TRANSFORMABLE = register("soul_pepper_transformable");

        private static TagKey<Item> register(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name));
        }
    }
}
