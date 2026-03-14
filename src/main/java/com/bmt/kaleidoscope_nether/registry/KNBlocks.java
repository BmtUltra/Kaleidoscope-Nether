package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.block.*;
import com.github.ysbbbbbb.kaleidoscopecookery.block.decoration.ChairBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.decoration.CookStoolBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.decoration.TableBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.BarCabinetBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class KNBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeNether.MOD_ID);

    public static final Supplier<BlockBehaviour.Properties> CROP_DEFAULT_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

    public static final Supplier<BlockBehaviour.Properties> CAVE_VINES_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);

    public static final RegistryObject<PoisonousFruit> POISONOUS_FRUIT = BLOCKS.register("poisonous_fruit",
            () -> new PoisonousFruit(CROP_DEFAULT_PROPERTIES.get()));

    public static final RegistryObject<SoulPepper> SOUL_PEPPER = BLOCKS.register("soul_pepper",
            () -> new SoulPepper(CROP_DEFAULT_PROPERTIES.get()));

    public static final RegistryObject<KNCaveVinesPlantBlock> TWISTING_CAVE_VINES_PLANT = BLOCKS.register("twisting_cave_vines_plant",
            () -> new TwistingCaveVinesPlant(CAVE_VINES_PROPERTIES.get()));

    public static final RegistryObject<KNCaveVinesHeadBlock> TWISTING_CAVE_VINES = BLOCKS.register("twisting_cave_vines",
            () -> new TwistingCaveVinesHead(CAVE_VINES_PROPERTIES.get()));

    public static final RegistryObject<KNCaveVinesPlantBlock> WEEPING_CAVE_VINES_PLANT = BLOCKS.register("weeping_cave_vines_plant",
            () -> new WeepingCaveVinesPlant(CAVE_VINES_PROPERTIES.get()));

    public static final RegistryObject<KNCaveVinesHeadBlock> WEEPING_CAVE_VINES = BLOCKS.register("weeping_cave_vines",
            () -> new WeepingCaveVinesHead(CAVE_VINES_PROPERTIES.get()));

    // 下界炉灶
    public static final RegistryObject<StoveBlock> NETHER_STOVE = BLOCKS.register("nether_stove",
            StoveBlock::new);

//    // 下界疣木家具
//    public static final RegistryObject<ChairBlock> CHAIR_WART = BLOCKS.register("chair_wart",
//            ChairBlock::new);
//
//    public static final RegistryObject<CookStoolBlock> COOK_STOOL_WART = BLOCKS.register("cook_stool_wart",
//            CookStoolBlock::new);
//
//    public static final RegistryObject<TableBlock> TABLE_WART = BLOCKS.register("table_wart",
//            TableBlock::new);

    // 诡异康尼
    public static final RegistryObject<Block> WARPED_CONNY = BLOCKS.register("warped_conny",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 灵魂白兰地
    public static final RegistryObject<Block> SOUL_BRANDY = BLOCKS.register("soul_brandy",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());
}
