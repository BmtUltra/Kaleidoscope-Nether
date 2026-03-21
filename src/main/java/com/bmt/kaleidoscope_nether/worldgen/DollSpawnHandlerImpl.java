package com.bmt.kaleidoscope_nether.worldgen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
class DollSpawnHandlerImpl {
    private static final BlockPos FIXED_SPAWN_POS = new BlockPos(599, 34, 106);

    static void spawnDollsAtFixedPosition(ServerLevel level) {
        spawnDollBlock(level);
    }

    private static void spawnDollBlock(ServerLevel level) {
        ResourceLocation dollBlockId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "doll_8");
        Block dollBlock = BuiltInRegistries.BLOCK.get(dollBlockId);

        if (dollBlock instanceof DollBlock) {
            BlockState dollState = dollBlock.defaultBlockState();
            level.setBlock(FIXED_SPAWN_POS, dollState, 3);
        }
    }
}