package com.chadate.kaleidoscope_nether;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(KaleidoscopeNether.MOD_ID)
public class KaleidoscopeNether {
    public static final String MOD_ID = "kaleidoscope_nether";

    public KaleidoscopeNether(IEventBus modEventBus, ModContainer modContainer) {

    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
