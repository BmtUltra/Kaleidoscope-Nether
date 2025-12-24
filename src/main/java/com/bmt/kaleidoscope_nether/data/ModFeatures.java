package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.API.KNTags;
import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> POISONOUS_FRUIT = createKey("poisonous_fruit");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(
                context,
                POISONOUS_FRUIT,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        96,
                        7,
                        3,
                        PlacementUtils.filtered(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.DIAMOND_BLOCK)
                                ),
                                ModFeatureUtils.simplePatchPredicate(KNTags.Blocks.SOUL_SOIL_SAND)
                        )
                ));
    }
}
