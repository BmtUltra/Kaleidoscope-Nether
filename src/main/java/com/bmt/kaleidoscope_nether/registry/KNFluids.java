package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.FLUIDS;
import static net.minecraftforge.registries.ForgeRegistries.Keys.FLUID_TYPES;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KNFluids {
    public static final ResourceLocation CRIMSON_JUICE_ID = KaleidoscopeNether.id("crimson_juice");
    public static final ResourceLocation FLOWING_CRIMSON_JUICE_ID = KaleidoscopeNether.id("flowing_crimson_juice");

    public static final ResourceLocation WARPED_JUICE_ID = KaleidoscopeNether.id("warped_juice");
    public static final ResourceLocation FLOWING_WARPED_JUICE_ID = KaleidoscopeNether.id("flowing_warped_juice");

    public static final ResourceLocation POISONOUS_JUICE_ID = KaleidoscopeNether.id("poisonous_juice");
    public static final ResourceLocation FLOWING_POISONOUS_JUICE_ID = KaleidoscopeNether.id("flowing_poisonous_juice");

    public static final ResourceLocation SOUL_JUICE_ID = KaleidoscopeNether.id("soul_juice");
    public static final ResourceLocation FLOWING_SOUL_JUICE_ID = KaleidoscopeNether.id("flowing_soul_juice");

    public static final RegistryObject<FluidType> CRIMSON_JUICE_TYPE = RegistryObject.create(CRIMSON_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeNether.MOD_ID);
    public static final RegistryObject<FluidType> WARPED_JUICE_TYPE = RegistryObject.create(WARPED_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeNether.MOD_ID);
    public static final RegistryObject<FluidType> POISONOUS_JUICE_TYPE = RegistryObject.create(POISONOUS_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeNether.MOD_ID);
    public static final RegistryObject<FluidType> SOUL_JUICE_TYPE = RegistryObject.create(SOUL_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<Fluid> CRIMSON_JUICE = RegistryObject.create(CRIMSON_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_CRIMSON_JUICE = RegistryObject.create(FLOWING_CRIMSON_JUICE_ID, FLUIDS);

    public static final RegistryObject<Fluid> WARPED_JUICE = RegistryObject.create(WARPED_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_WARPED_JUICE = RegistryObject.create(FLOWING_WARPED_JUICE_ID, FLUIDS);

    public static final RegistryObject<Fluid> POISONOUS_JUICE = RegistryObject.create(POISONOUS_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_POISONOUS_JUICE = RegistryObject.create(FLOWING_POISONOUS_JUICE_ID, FLUIDS);

    public static final RegistryObject<Fluid> SOUL_JUICE = RegistryObject.create(SOUL_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_SOUL_JUICE = RegistryObject.create(FLOWING_SOUL_JUICE_ID, FLUIDS);

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Keys.FLUID_TYPES, helper -> {
            helper.register(CRIMSON_JUICE_ID, new JuiceFluidType(CRIMSON_JUICE_ID, 0));
            helper.register(WARPED_JUICE_ID, new JuiceFluidType (WARPED_JUICE_ID, 0));
            helper.register(POISONOUS_JUICE_ID, new JuiceFluidType (POISONOUS_JUICE_ID, 0));
            helper.register(SOUL_JUICE_ID, new JuiceFluidType (SOUL_JUICE_ID, 10));
        });

        event.register(Keys.FLUIDS, helper -> {
            Properties crimsonJuice = new Properties(CRIMSON_JUICE_TYPE, CRIMSON_JUICE, FLOWING_CRIMSON_JUICE)
                    .bucket(KNItems.CRIMSON_BUCKET);
            Properties warpedJuice = new Properties(WARPED_JUICE_TYPE, WARPED_JUICE, FLOWING_WARPED_JUICE)
                    .bucket(KNItems.WARPED_BUCKET);
            Properties poisonousJuice = new Properties(POISONOUS_JUICE_TYPE, POISONOUS_JUICE, FLOWING_POISONOUS_JUICE)
                    .bucket(KNItems.POISONOUS_BUCKET);
            Properties soulJuice = new Properties(SOUL_JUICE_TYPE, SOUL_JUICE, FLOWING_SOUL_JUICE)
                    .bucket(KNItems.SOUL_BUCKET);

            helper.register(CRIMSON_JUICE_ID, new Source(crimsonJuice));
            helper.register(FLOWING_CRIMSON_JUICE_ID, new Flowing(crimsonJuice));

            helper.register(WARPED_JUICE_ID, new Source(warpedJuice));
            helper.register(FLOWING_WARPED_JUICE_ID, new Flowing(warpedJuice));

            helper.register(POISONOUS_JUICE_ID, new Source(poisonousJuice));
            helper.register(FLOWING_POISONOUS_JUICE_ID, new Flowing(poisonousJuice));

            helper.register(SOUL_JUICE_ID, new Source(soulJuice));
            helper.register(FLOWING_SOUL_JUICE_ID, new Flowing(soulJuice));
        });
    }
}