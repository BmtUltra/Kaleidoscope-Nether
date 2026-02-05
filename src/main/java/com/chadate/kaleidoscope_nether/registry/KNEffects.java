package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.chadate.kaleidoscope_nether.effect.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KNEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> CRIMSON_BUFF = EFFECTS.register("crimson_buff",
            () -> new CrimsonBuffEffect(0xFF0000));

    public static final DeferredHolder<MobEffect, MobEffect> WARPED_BUFF = EFFECTS.register("warped_buff",
            () -> new WarpedBuffEffect(0x00FFA2));

    public static final DeferredHolder<MobEffect, MobEffect> STAR_BLESSING_BUFF = EFFECTS.register("star_blessing_buff",
            () -> new StarBlessingBuffEffect(0x87CEEB));

    public static final DeferredHolder<MobEffect, MobEffect> GHOST_BUFF = EFFECTS.register("ghost_buff",
            () -> new GhostBuffEffect(0x4A90E2));

    public static final DeferredHolder<MobEffect, MobEffect> LAVA_WALKER = EFFECTS.register("lava_walker",
            () -> new LavaWalkerEffect(0xCC3300));

    public static final DeferredHolder<MobEffect, MobEffect> MYSTERIOUS_POISON = EFFECTS.register("mysterious_poison",
            () -> new MysteriousPoisonEffect(0x8A2BE2));

    public static final DeferredHolder<MobEffect, MobEffect> GLOWING_BUFF = EFFECTS.register("glowing_buff",
            () -> new GlowingBuffEffect(0xFFFF00));
}