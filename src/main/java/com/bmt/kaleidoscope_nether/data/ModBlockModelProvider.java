package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (int i = 0; i < 8; i++) {
            cross("poisonous_fruit/stage%d".formatted(i), KaleidoscopeNether.id("block/poisonous_fruit/stage%d".formatted(i))).renderType("cutout");
        }
    }


}
