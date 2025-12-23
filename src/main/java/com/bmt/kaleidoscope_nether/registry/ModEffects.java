package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<MobEffect> CRIMSON_BUFF = EFFECTS.register("crimson_buff",
            () -> new CrimsonBuffEffect(0xFF6B6B));

    public static final RegistryObject<MobEffect> WARPED_BUFF = EFFECTS.register("warped_buff",
            () -> new WarpedBuffEffect(0x6B8E23));

    public static final RegistryObject<MobEffect> STAR_BLESSING_BUFF = EFFECTS.register("star_blessing_buff",
            () -> new StarBlessingBuffEffect(0x87CEEB));

    public static final RegistryObject<MobEffect> GHOST_BUFF = EFFECTS.register("ghost_buff",
            () -> new GhostBuffEffect(0x8A2BE2));

    public static final RegistryObject<MobEffect> LAVA_WALKER = EFFECTS.register("lava_walker",
            () -> new LavaWalkerEffect(0xCC3300));

    public static final RegistryObject<MobEffect> MYSTERIOUS_POISON = EFFECTS.register("mysterious_poison",
            () -> new MysteriousPoisonEffect(0x8B008B));

    public static final RegistryObject<MobEffect> BLAZING = EFFECTS.register("blazing",
            () -> new BlazingEffect(0xFF4500));

    public static final RegistryObject<MobEffect> GLOWING_BUFF = EFFECTS.register("glowing_buff",
            () -> new GlowingBuffEffect(0xFFFF00));
}