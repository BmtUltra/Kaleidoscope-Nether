package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.PaintingBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNPaintings {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeNether.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<Block> BMT_PAINTING = BLOCKS.register("bmt_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> DREAM_PAINTING = BLOCKS.register("dream_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CHA_PAINTING = BLOCKS.register("cha_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CHEN_PAINTING = BLOCKS.register("chen_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> SMILE_PAINTING = BLOCKS.register("smile_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> DIAMOND_PAINTING = BLOCKS.register("diamond_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> RABBIT_PAINTING = BLOCKS.register("rabbit_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CAT_PAINTING = BLOCKS.register("cat_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> FROG_PAINTING = BLOCKS.register("frog_painting",
            PaintingBlock::new);


    public static final RegistryObject<Item> BMT_PAINTING_ITEM = ITEMS.register("bmt_painting",
            () -> new BlockItem(BMT_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> DREAM_PAINTING_ITEM = ITEMS.register("dream_painting",
            () -> new BlockItem(DREAM_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHA_PAINTING_ITEM = ITEMS.register("cha_painting",
            () -> new BlockItem(CHA_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHEN_PAINTING_ITEM = ITEMS.register("chen_painting",
            () -> new BlockItem(CHEN_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> SMILE_PAINTING_ITEM = ITEMS.register("smile_painting",
            () -> new BlockItem(SMILE_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_PAINTING_ITEM = ITEMS.register("diamond_painting",
            () -> new BlockItem(DIAMOND_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> RABBIT_PAINTING_ITEM = ITEMS.register("rabbit_painting",
            () -> new BlockItem(RABBIT_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CAT_PAINTING_ITEM = ITEMS.register("cat_painting",
            () -> new BlockItem(CAT_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> FROG_PAINTING_ITEM = ITEMS.register("frog_painting",
            () -> new BlockItem(FROG_PAINTING.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}