package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacements {
    public static final ResourceKey<PlacedFeature> POISONOUS_FRUIT = createKey("poisonous_fruit");

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, key));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(
                context,
                POISONOUS_FRUIT,
                holdergetter.getOrThrow(ModFeatures.POISONOUS_FRUIT),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE
        );
    }
}
