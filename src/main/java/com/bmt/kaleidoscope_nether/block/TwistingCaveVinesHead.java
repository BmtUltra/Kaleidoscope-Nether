package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.init.KNBlocks;
import com.bmt.kaleidoscope_nether.init.KNItems;
import net.minecraft.core.Direction;

public class TwistingCaveVinesHead extends KNCaveVinesHeadBlock {

    public TwistingCaveVinesHead(Properties properties) {
        super(properties, KNBlocks.TWISTING_CAVE_VINES_PLANT, KNItems.WARPED_FRUIT);
        growthDirection = Direction.UP;
    }
}
