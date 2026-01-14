package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<SoundEvent> LAVA_ROASTED_CHICKEN_EAT = registerSoundEvent("lava_roasted_chicken_eat");
    public static final RegistryObject<SoundEvent> STAR_DUST_REPAIR = registerSoundEvent("star_dust_repair");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = KaleidoscopeNether.id(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(location));
    }
}