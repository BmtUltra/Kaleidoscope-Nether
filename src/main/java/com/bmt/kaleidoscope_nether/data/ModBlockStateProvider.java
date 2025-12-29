package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.KNBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, KaleidoscopeNether.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        VariantBlockStateBuilder builder = getVariantBuilder(KNBlocks.POISONOUS_FRUIT.get());
        builder.forAllStates(blockState -> {
            int age = blockState.getValue(CropBlock.AGE);
            ResourceLocation file = modLoc("poisonous_fruit/stage%d".formatted(age));
            return ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(file)).build();
        });

    }
}
