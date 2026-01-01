package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.block.PoisonousFruit;
import com.bmt.kaleidoscope_nether.block.SoulPepper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class KNBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeNether.MOD_ID);

    private static final Supplier<BlockBehaviour.Properties> CROP_DEFAULT_PROPERTIES = () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

    public static final RegistryObject<PoisonousFruit> POISONOUS_FRUIT = BLOCKS.register("poisonous_fruit", () -> new PoisonousFruit(CROP_DEFAULT_PROPERTIES.get()));

    public static final RegistryObject<SoulPepper> SOUL_PEPPER = BLOCKS.register("soul_pepper", () -> new SoulPepper(CROP_DEFAULT_PROPERTIES.get()));

}
