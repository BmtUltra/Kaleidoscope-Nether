package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.block.*;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class KNBlocks {
    public static final BlockBehaviour.Properties CROP_DEFAULT_PROPERTIES =
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

    public static final BlockBehaviour.Properties CAVE_VINES_PROPERTIES =
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);

    public static final PoisonousFruit POISONOUS_FRUIT = register("poisonous_fruit",
            new PoisonousFruit(CROP_DEFAULT_PROPERTIES));

    public static final SoulPepper SOUL_PEPPER = register("soul_pepper",
            new SoulPepper(CROP_DEFAULT_PROPERTIES));

    public static final TwistingCaveVinesPlant TWISTING_CAVE_VINES_PLANT = register("twisting_cave_vines_plant",
            new TwistingCaveVinesPlant(CAVE_VINES_PROPERTIES));

    public static final TwistingCaveVinesHead TWISTING_CAVE_VINES = register("twisting_cave_vines",
            new TwistingCaveVinesHead(CAVE_VINES_PROPERTIES));

    public static final WeepingCaveVinesPlant WEEPING_CAVE_VINES_PLANT = register("weeping_cave_vines_plant",
            new WeepingCaveVinesPlant(CAVE_VINES_PROPERTIES));

    public static final WeepingCaveVinesHead WEEPING_CAVE_VINES = register("weeping_cave_vines",
            new WeepingCaveVinesHead(CAVE_VINES_PROPERTIES));

    public static final Block NETHER_STOVE = register("nether_stove",
            new StoveBlock());

    private static <T extends Block> T register(String name, T block) {
        return null;
    }

    public static void registerBlocks() {
    }
}