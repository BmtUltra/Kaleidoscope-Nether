package com.bmt.kaleidoscope_nether.worldgen;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
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