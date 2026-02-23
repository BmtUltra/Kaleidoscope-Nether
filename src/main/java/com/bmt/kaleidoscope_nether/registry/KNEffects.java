package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<MobEffect> CRIMSON = EFFECTS.register("crimson",
            () -> new CrimsonEffect(0xFF0000));

    public static final RegistryObject<MobEffect> WARPED = EFFECTS.register("warped",
            () -> new WarpedEffect(0x00FFA2));

    public static final RegistryObject<MobEffect> STAR_BLESSING = EFFECTS.register("star_blessing",
            () -> new StarBlessingEffect(0x87CEEB));

    public static final RegistryObject<MobEffect> GHOST = EFFECTS.register("ghost",
            () -> new GhostEffect(0x4A90E2));

    public static final RegistryObject<MobEffect> TROPICAL_STRIDER = EFFECTS.register("tropical_strider",
            () -> new TropicalStriderEffect(0xCC3300));

    public static final RegistryObject<MobEffect> MYSTERIOUS_POISON = EFFECTS.register("mysterious_poison",
            () -> new MysteriousPoisonEffect(0x8A2BE2));

    public static final RegistryObject<MobEffect> GLOWING = EFFECTS.register("glowing",
            () -> new GlowingEffect(0xFFFF00));

    public static final RegistryObject<MobEffect> CRIMSON_DRUNKENNESS = EFFECTS.register("crimson_drunkenness",
            () -> new CrimsonDrunkennessEffect(0xFF0000));

    public static final RegistryObject<MobEffect> WARPED_DRUNKENNESS = EFFECTS.register("warped_drunkenness",
            () -> new WarpedDrunkennessEffect(0x00FFA2));

    public static final RegistryObject<MobEffect> MYSTERIOUS_POISON_DRUNKENNESS = EFFECTS.register("mysterious_poison_drunkenness",
            () -> new MysteriousPoisonDrunkennessEffect(0x8A2BE2));

//    public static final RegistryObject<MobEffect> SOUL_DRUNKENNESS = EFFECTS.register("soul_drunkenness",
//            () -> new SoulDrunkennessEffect(0x00CED1));
}