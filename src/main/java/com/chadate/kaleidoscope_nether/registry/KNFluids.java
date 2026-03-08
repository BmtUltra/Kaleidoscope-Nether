package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class KNFluids {

    // 注册表
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, KaleidoscopeNether.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.FLUID, KaleidoscopeNether.MOD_ID);

    // ID
    public static final ResourceLocation CRIMSON_JUICE_ID = KaleidoscopeNether.id("crimson_juice");
    public static final ResourceLocation FLOWING_CRIMSON_JUICE_ID = KaleidoscopeNether.id("flowing_crimson_juice");

    public static final ResourceLocation WARPED_JUICE_ID = KaleidoscopeNether.id("warped_juice");
    public static final ResourceLocation FLOWING_WARPED_JUICE_ID = KaleidoscopeNether.id("flowing_warped_juice");

    public static final ResourceLocation POISONOUS_JUICE_ID = KaleidoscopeNether.id("poisonous_juice");
    public static final ResourceLocation FLOWING_POISONOUS_JUICE_ID = KaleidoscopeNether.id("flowing_poisonous_juice");

    public static final ResourceLocation SOUL_JUICE_ID = KaleidoscopeNether.id("soul_juice");
    public static final ResourceLocation FLOWING_SOUL_JUICE_ID = KaleidoscopeNether.id("flowing_soul_juice");

    // FluidType
    public static final DeferredHolder<FluidType, FluidType> CRIMSON_JUICE_TYPE =
            FLUID_TYPES.register("crimson_juice", () -> new JuiceFluidType(CRIMSON_JUICE_ID, 0));
    public static final DeferredHolder<FluidType, FluidType> WARPED_JUICE_TYPE =
            FLUID_TYPES.register("warped_juice", () -> new JuiceFluidType(WARPED_JUICE_ID, 0));
    public static final DeferredHolder<FluidType, FluidType> POISONOUS_JUICE_TYPE =
            FLUID_TYPES.register("poisonous_juice", () -> new JuiceFluidType(POISONOUS_JUICE_ID, 0));
    public static final DeferredHolder<FluidType, FluidType> SOUL_JUICE_TYPE =
            FLUID_TYPES.register("soul_juice", () -> new JuiceFluidType(SOUL_JUICE_ID, 10));

    // BaseFlowingFluid 属性（需要在静态块里初始化）
    public static BaseFlowingFluid.Properties CRIMSON_PROPS;
    public static BaseFlowingFluid.Properties WARPED_PROPS;
    public static BaseFlowingFluid.Properties POISONOUS_PROPS;
    public static BaseFlowingFluid.Properties SOUL_PROPS;

    // 流体本体（Source / Flowing）
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> CRIMSON_JUICE =
            FLUIDS.register("crimson_juice", () -> new BaseFlowingFluid.Source(CRIMSON_PROPS));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_CRIMSON_JUICE =
            FLUIDS.register("flowing_crimson_juice", () -> new BaseFlowingFluid.Flowing(CRIMSON_PROPS));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> WARPED_JUICE =
            FLUIDS.register("warped_juice", () -> new BaseFlowingFluid.Source(WARPED_PROPS));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_WARPED_JUICE =
            FLUIDS.register("flowing_warped_juice", () -> new BaseFlowingFluid.Flowing(WARPED_PROPS));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> POISONOUS_JUICE =
            FLUIDS.register("poisonous_juice", () -> new BaseFlowingFluid.Source(POISONOUS_PROPS));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_POISONOUS_JUICE =
            FLUIDS.register("flowing_poisonous_juice", () -> new BaseFlowingFluid.Flowing(POISONOUS_PROPS));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOUL_JUICE =
            FLUIDS.register("soul_juice", () -> new BaseFlowingFluid.Source(SOUL_PROPS));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_SOUL_JUICE =
            FLUIDS.register("flowing_soul_juice", () -> new BaseFlowingFluid.Flowing(SOUL_PROPS));

    static {
        CRIMSON_PROPS = new BaseFlowingFluid.Properties(
                CRIMSON_JUICE_TYPE,
                CRIMSON_JUICE,
                FLOWING_CRIMSON_JUICE
        ).bucket(KNItems.CRIMSON_BUCKET);

        WARPED_PROPS = new BaseFlowingFluid.Properties(
                WARPED_JUICE_TYPE,
                WARPED_JUICE,
                FLOWING_WARPED_JUICE
        ).bucket(KNItems.WARPED_BUCKET);

        POISONOUS_PROPS = new BaseFlowingFluid.Properties(
                POISONOUS_JUICE_TYPE,
                POISONOUS_JUICE,
                FLOWING_POISONOUS_JUICE
        ).bucket(KNItems.POISONOUS_BUCKET);

        SOUL_PROPS = new BaseFlowingFluid.Properties(
                SOUL_JUICE_TYPE,
                SOUL_JUICE,
                FLOWING_SOUL_JUICE
        ).bucket(KNItems.SOUL_BUCKET);
    }
}