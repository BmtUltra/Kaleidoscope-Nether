package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifier {
    public static final ResourceKey<BiomeModifier> POISONOUS_FRUIT = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("poisonous_fruit")
    );
    public static final ResourceKey<BiomeModifier> TWISTING_CAVE_VINES = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("twisting_cave_vines")
    );

    public static final ResourceKey<BiomeModifier> WEEPING_CAVE_VINES = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            KaleidoscopeNether.id("weeping_cave_vines")
    );

    public static void bootstrap(BootstapContext<BiomeModifier> bootstrap) {
        HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);
        bootstrap.register(POISONOUS_FRUIT,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.POISONOUS_FRUIT)),
                        GenerationStep.Decoration.UNDERGROUND_DECORATION
                )
        );

        bootstrap.register(TWISTING_CAVE_VINES,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.get(Biomes.WARPED_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.TWISTING_CAVE_VINES)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        bootstrap.register(WEEPING_CAVE_VINES,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomes.get(Biomes.CRIMSON_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.WEEPING_CAVE_VINES)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }
}
