package com.chadate.kaleidoscope_nether.worldgen;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class DollSpawnHandler {
    private static final BlockPos FIXED_SPAWN_POS = new BlockPos(599, 34, 106);
    private static final String DATA_NAME = KaleidoscopeNether.MOD_ID + "_doll_spawned";

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension() == Level.NETHER) {
                spawnDollsAtFixedPosition(serverLevel);
            }
        }
    }

    private static void spawnDollsAtFixedPosition(ServerLevel level) {
        DollSpawnData data = level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        DollSpawnData::new,
                        DollSpawnData::load
                ),
                DATA_NAME
        );

        if (!data.isDollSpawned()) {
            spawnDollBlock(level, "doll_8");
            data.setDollSpawned(true);
            data.setDirty();
        }
    }

    private static void spawnDollBlock(ServerLevel level, String dollId) {
        ResourceLocation dollBlockId = KaleidoscopeNether.id(dollId);
        Block dollBlock = BuiltInRegistries.BLOCK.get(dollBlockId);

        if (dollBlock instanceof DollBlock) {
            BlockState dollState = dollBlock.defaultBlockState();
            level.setBlock(FIXED_SPAWN_POS, dollState, 3);
        }
    }

    private static class DollSpawnData extends SavedData {
        private static final String DOLL_SPAWNED_KEY = "doll_spawned";
        private boolean dollSpawned;

        public DollSpawnData() {
            this.dollSpawned = false;
        }

        public boolean isDollSpawned() {
            return dollSpawned;
        }

        public void setDollSpawned(boolean spawned) {
            this.dollSpawned = spawned;
            this.setDirty();
        }

        public static DollSpawnData load(CompoundTag tag, HolderLookup.Provider registries) {
            DollSpawnData data = new DollSpawnData();
            if (tag.contains(DOLL_SPAWNED_KEY)) {
                data.dollSpawned = tag.getBoolean(DOLL_SPAWNED_KEY);
            }
            return data;
        }

        @Override
        public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
            tag.putBoolean(DOLL_SPAWNED_KEY, dollSpawned);
            return tag;
        }
    }
}