package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class KNEffects {
    public static Holder<MobEffect> CRIMSON;
    public static Holder<MobEffect> WARPED;
    public static Holder<MobEffect> STAR_BLESSING;
    public static Holder<MobEffect> GHOST;
    public static Holder<MobEffect> TROPICAL_STRIDER;
    public static Holder<MobEffect> MYSTERIOUS_POISON;
    public static Holder<MobEffect> GLOWING;
    public static Holder<MobEffect> SOUL_DRUNKENNESS;

    public static void registerEffects() {
        CRIMSON = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "crimson"),
                new CrimsonEffect(0xFF0000));

        WARPED = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "warped"),
                new WarpedEffect(0x00FFA2));

        STAR_BLESSING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "star_blessing"),
                new StarBlessingEffect(0x87CEEB));

        GHOST = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "ghost"),
                new GhostEffect(0x4A90E2));

        TROPICAL_STRIDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "tropical_strider"),
                new TropicalStriderEffect(0xCC3300));

        MYSTERIOUS_POISON = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "mysterious_poison"),
                new MysteriousPoisonEffect(0x8A2BE2));

        GLOWING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "glowing"),
                new GlowingEffect(0xFFFF00));

        SOUL_DRUNKENNESS = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "soul_drunkenness"),
                new SoulDrunkennessEffect(0x00CED1));
    }
}