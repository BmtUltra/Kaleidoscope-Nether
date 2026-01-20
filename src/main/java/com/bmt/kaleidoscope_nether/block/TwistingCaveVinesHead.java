package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.registry.KNBlocks;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TwistingCaveVinesHead extends KNCaveVinesHeadBlock {

    public TwistingCaveVinesHead(Properties properties) {
        super(properties, KNBlocks.TWISTING_CAVE_VINES_PLANT, KNItems.WARPED_FRUIT);
        growthDirection = Direction.UP;
    }
}
