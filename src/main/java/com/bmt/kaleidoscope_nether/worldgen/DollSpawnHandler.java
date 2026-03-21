package com.bmt.kaleidoscope_nether.worldgen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class DollSpawnHandler {
    private static final String DOLL_MOD_ID = "kaleidoscope_doll";

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (!ModList.get().isLoaded(DOLL_MOD_ID)) {
            return;
        }

        if (event.getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension() == Level.NETHER) {
                DollSpawnHandlerImpl.spawnDollsAtFixedPosition(serverLevel);
            }
        }
    }
}