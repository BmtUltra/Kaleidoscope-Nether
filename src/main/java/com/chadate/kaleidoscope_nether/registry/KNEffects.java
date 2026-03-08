package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.chadate.kaleidoscope_nether.effect.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> CRIMSON = EFFECTS.register("crimson",
            () -> new CrimsonEffect(0xFF0000));

    public static final DeferredHolder<MobEffect, MobEffect> WARPED = EFFECTS.register("warped",
            () -> new WarpedDrunkennessEffect(0x00FFA2));

    public static final DeferredHolder<MobEffect, MobEffect> STAR_BLESSING = EFFECTS.register("star_blessing",
            () -> new StarBlessingEffect(0x87CEEB));

    public static final DeferredHolder<MobEffect, MobEffect> GHOST = EFFECTS.register("ghost",
            () -> new GhostEffect(0x4A90E2));

    public static final DeferredHolder<MobEffect, MobEffect> TROPICAL_STRIDER = EFFECTS.register("tropical_strider",
            () -> new TropicalStriderEffect(0xCC3300));

    public static final DeferredHolder<MobEffect, MobEffect> MYSTERIOUS_POISON = EFFECTS.register("mysterious_poison",
            () -> new MysteriousPoisonEffect(0x8A2BE2));

    public static final DeferredHolder<MobEffect, MobEffect> GLOWING = EFFECTS.register("glowing",
            () -> new GlowingEffect(0xFFFF00));

    public static final DeferredHolder<MobEffect, MobEffect> CRIMSON_DRUNKENNESS = EFFECTS.register("crimson_drunkenness",
            () -> new CrimsonDrunkennessEffect(0xFF0000));

    public static final DeferredHolder<MobEffect, MobEffect> WARPED_DRUNKENNESS = EFFECTS.register("warped_drunkenness",
            () -> new WarpedDrunkennessEffect(0x00FFA2));

    public static final DeferredHolder<MobEffect, MobEffect> MYSTERIOUS_POISON_DRUNKENNESS = EFFECTS.register("mysterious_poison_drunkenness",
            () -> new MysteriousPoisonDrunkennessEffect(0x8A2BE2));

    public static final DeferredHolder<MobEffect, MobEffect> SOUL_DRUNKENNESS = EFFECTS.register("soul_drunkenness",
            () -> new SoulDrunkennessEffect(0x00CED1));
}