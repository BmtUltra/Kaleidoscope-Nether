package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.init.KNBlocks;
import com.bmt.kaleidoscope_nether.init.KNItems;
import net.minecraft.core.Direction;

public class TwistingCaveVinesPlant extends KNCaveVinesPlantBlock {
    public TwistingCaveVinesPlant(Properties properties) {
        super(KNBlocks.TWISTING_CAVE_VINES, properties, KNItems.WARPED_FRUIT);
        growthDirection = Direction.UP;
    }
}
