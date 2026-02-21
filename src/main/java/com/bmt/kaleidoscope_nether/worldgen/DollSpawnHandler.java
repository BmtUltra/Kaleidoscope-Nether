package com.bmt.kaleidoscope_nether.worldgen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class DollSpawnHandler {
    private static final BlockPos FIXED_SPAWN_POS = new BlockPos(599, 34, 106);

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension() == Level.NETHER) {
                spawnDollsAtFixedPosition(serverLevel);
            }
        }
    }

    private static void spawnDollsAtFixedPosition(ServerLevel level) {
        spawnDollBlock(level, "doll_8");
    }

    private static void spawnDollBlock(ServerLevel level, String dollId) {
        ResourceLocation dollBlockId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, dollId);
        Block dollBlock = ForgeRegistries.BLOCKS.getValue(dollBlockId);

        if (dollBlock instanceof DollBlock) {
            BlockState dollState = dollBlock.defaultBlockState();
            level.setBlock(FIXED_SPAWN_POS, dollState, 3);
        }
    }
}