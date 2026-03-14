package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class KNSounds {
    public static final SoundEvent LAVA_ROASTED_CHICKEN_EAT = registerSoundEvent("lava_roasted_chicken_eat");
    public static final SoundEvent STAR_DUST_REPAIR = registerSoundEvent("star_dust_repair");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name);
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(location);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, location, soundEvent);
    }

    public static void registerSounds() {
    }
}