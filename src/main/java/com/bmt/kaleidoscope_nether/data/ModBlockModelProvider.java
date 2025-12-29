package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<String> cropBlocksId = new ArrayList<>();
        cropBlocksId.add("poisonous_fruit");
        cropBlocksId.add("soul_pepper");
        for (int i = 0; i < 8; i++) {
            for (String string : cropBlocksId) {
                cross("%s/stage%d".formatted(string, i), KaleidoscopeNether.id("block/%s/stage%d".formatted(string, i))).renderType("cutout");
            }
        }
    }


}
