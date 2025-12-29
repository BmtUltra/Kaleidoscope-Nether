package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.block.PoisonousFruit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<PoisonousFruit> POISONOUS_FRUIT = BLOCKS.register("poisonous_fruit", () -> new PoisonousFruit(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.CROP)));
}
