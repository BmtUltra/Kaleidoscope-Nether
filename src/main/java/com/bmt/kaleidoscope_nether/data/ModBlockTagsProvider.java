package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.API.KNTags;
import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(KNTags.Blocks.SOUL_SOIL_SAND)
                .add(
                        Blocks.SOUL_SAND,
                        Blocks.SOUL_SOIL
                );
    }
}
