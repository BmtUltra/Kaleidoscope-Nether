package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNPotions {
    public static final DeferredRegister<Potion> POISONS = DeferredRegister.create(ForgeRegistries.POTIONS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<Potion> MYSTERIOUS_POISON = POISONS.register("mysterious_poison", () -> new Potion(new MobEffectInstance(
            KNEffects.MYSTERIOUS_POISON.get(),
            1500,
            0
    )));

}
