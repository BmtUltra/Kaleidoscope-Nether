package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.PaintingBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;


public class KNPaintings {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KaleidoscopeNether.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<Block, Block> BMT_PAINTING = BLOCKS.register("bmt_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> DREAM_PAINTING = BLOCKS.register("dream_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> CHA_PAINTING = BLOCKS.register("cha_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> CHEN_PAINTING = BLOCKS.register("chen_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> SMILE_PAINTING = BLOCKS.register("smile_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> DIAMOND_PAINTING = BLOCKS.register("diamond_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> RABBIT_PAINTING = BLOCKS.register("rabbit_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> CAT_PAINTING = BLOCKS.register("cat_painting",
            PaintingBlock::new);

    public static final DeferredHolder<Block, Block> FROG_PAINTING = BLOCKS.register("frog_painting",
            PaintingBlock::new);


    public static final DeferredHolder<Item, Item> BMT_PAINTING_ITEM = ITEMS.register("bmt_painting",
            () -> new BlockItem(BMT_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> DREAM_PAINTING_ITEM = ITEMS.register("dream_painting",
            () -> new BlockItem(DREAM_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> CHA_PAINTING_ITEM = ITEMS.register("cha_painting",
            () -> new BlockItem(CHA_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> CHEN_PAINTING_ITEM = ITEMS.register("chen_painting",
            () -> new BlockItem(CHEN_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> SMILE_PAINTING_ITEM = ITEMS.register("smile_painting",
            () -> new BlockItem(SMILE_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> DIAMOND_PAINTING_ITEM = ITEMS.register("diamond_painting",
            () -> new BlockItem(DIAMOND_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> RABBIT_PAINTING_ITEM = ITEMS.register("rabbit_painting",
            () -> new BlockItem(RABBIT_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> CAT_PAINTING_ITEM = ITEMS.register("cat_painting",
            () -> new BlockItem(CAT_PAINTING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, Item> FROG_PAINTING_ITEM = ITEMS.register("frog_painting",
            () -> new BlockItem(FROG_PAINTING.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}