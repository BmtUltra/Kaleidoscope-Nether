package com.bmt.kaleidoscope_nether.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class SeedBagItem extends Item {

    private static final int SEED_BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    private static final int MAX_TOTAL_SEEDS = 2048;
    private static final int MAX_SEED_TYPES = 9;

    public SeedBagItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack seedBag, @NotNull ItemStack incoming, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess carriedSlotAccessor) {
        if(seedBag.getCount() != 1 || action != ClickAction.SECONDARY || !slot.allowModification(player))
            return false;

        if(incoming.isEmpty())
            return dropOntoEmptyCursor(player, seedBag, carriedSlotAccessor);
        else
            return absorbFromCursor(player, seedBag, carriedSlotAccessor);
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack seedBag, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if(seedBag.getCount() != 1 || action != ClickAction.SECONDARY || !slot.allowModification(player))
            return false;

        ItemStack droppedOnto = slot.getItem();
        if(droppedOnto.isEmpty())
            return dropIntoEmptySlot(player, seedBag, slot);
        else
            return absorbFromSlot(player, seedBag, slot);
    }

    private boolean absorbFromCursor(Player player, ItemStack seedBag, SlotAccess cursorAccess) {
        return mutateContents(seedBag, contents -> {
            ItemStack onCursor = cursorAccess.get();

            if(!contents.absorb(onCursor))
                return false;

            cursorAccess.set(onCursor);
            playInsertSound(player);
            return true;
        });
    }

    private boolean absorbFromSlot(Player player, ItemStack seedBag, Slot pickupFrom) {
        return mutateContents(seedBag, contents -> {
            if(!contents.absorb(pickupFrom.getItem()))
                return false;

            pickupFrom.setChanged();
            playInsertSound(player);
            return true;
        });
    }

    private boolean dropOntoEmptyCursor(Player player, ItemStack seedBag, SlotAccess cursorAccess) {
        return mutateContents(seedBag, contents -> {
            if(contents.isEmpty())
                return false;

            ItemStack toDrop = contents.splitOneStack();
            if(toDrop.isEmpty())
                return false;

            cursorAccess.set(toDrop);
            playRemoveOneSound(player);
            return true;
        });
    }

    private boolean dropIntoEmptySlot(Player player, ItemStack seedBag, Slot depositInto) {
        return mutateContents(seedBag, contents -> {
            if(contents.isEmpty())
                return false;

            ItemStack toDrop = contents.splitOneStack();
            if(toDrop.isEmpty())
                return false;

            depositInto.set(toDrop);
            playRemoveOneSound(player);
            return true;
        });
    }

    private static void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private static void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        // 移除进度条显示
        return false;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return 0;
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return SEED_BAR_COLOR;
    }

    public static BagContents getContents(ItemStack stack) {
        return BagContents.readFromStack(stack);
    }

    public static int getTotalCount(ItemStack stack) {
        return BagContents.readTotalCountFromStack(stack);
    }

    public static <T> T mutateContents(ItemStack seedBag, Function<BagContents, T> func) {
        return BagContents.mutate(seedBag, func);
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        Component base = super.getName(stack);

        BagContents contents = getContents(stack);
        if(contents.isEmpty())
            return base;

        return base;
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack seedBag = context.getItemInHand();
        Player player = context.getPlayer();
        BlockPos clickedPos = context.getClickedPos();

        if (player != null && player.isShiftKeyDown() && !context.getLevel().isClientSide()) {
            var targetedBE = context.getLevel().getBlockEntity(clickedPos);
            if (targetedBE != null) {
                Optional<IItemHandler> optionalItemHandler = targetedBE.getCapability(ForgeCapabilities.ITEM_HANDLER, context.getClickedFace()).resolve();
                if (optionalItemHandler.isPresent()) {
                    return storeAllToContainer(seedBag, player, optionalItemHandler.get());
                }
            }
        }

        BlockState blockState = context.getLevel().getBlockState(clickedPos);
        if (blockState.getBlock() instanceof FarmBlock) {
            return plantOnFarmland(context, seedBag, clickedPos);
        }

        // 检查是否是灵魂沙（用于下界疣）
        if (blockState.is(net.minecraft.world.level.block.Blocks.SOUL_SAND)) {
            return plantOnSoulSand(context, seedBag, clickedPos);
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack seedBag = player.getItemInHand(hand);
        return InteractionResultHolder.pass(seedBag);
    }

    private InteractionResult storeAllToContainer(ItemStack seedBag, Player player, IItemHandler container) {
        return mutateContents(seedBag, contents -> {
            if(contents.isEmpty()) {
                return InteractionResult.FAIL;
            }

            int totalStored = 0;
            List<SeedEntry> entries = new ArrayList<>(contents.entries);

            for (SeedEntry entry : entries) {
                ItemStack toInsert = entry.seed.copy();
                toInsert.setCount(entry.count);

                ItemStack remaining = ItemHandlerHelper.insertItem(container, toInsert, false);
                int stored = entry.count - remaining.getCount();

                if (stored > 0) {
                    contents.removeSeed(entry.seed, stored);
                    totalStored += stored;
                }
            }

            if (totalStored > 0) {
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        });
    }

    private InteractionResult plantOnFarmland(UseOnContext context, ItemStack seedBag, BlockPos farmlandPos) {
        return mutateContents(seedBag, contents -> {
            if(contents.isEmpty())
                return InteractionResult.PASS;

            Player player = context.getPlayer();
            Level level = context.getLevel();

            // 按顺序从左至右选择第一个合适的种子
            Optional<SeedEntry> suitableSeed = contents.findSuitableSeedForFarmland(level, farmlandPos.above());
            if(suitableSeed.isEmpty())
                return InteractionResult.PASS;

            SeedEntry seedEntry = suitableSeed.get();
            ItemStack seed = seedEntry.seed.copy();
            seed.setCount(1);

            if(player == null || !player.isShiftKeyDown()) {
                return plantSingleSeed(contents, context, seed, farmlandPos, seedEntry);
            } else {
                return plantInArea(contents, context, seed, farmlandPos, seedEntry);
            }
        });
    }

    private InteractionResult plantOnSoulSand(UseOnContext context, ItemStack seedBag, BlockPos soulSandPos) {
        return mutateContents(seedBag, contents -> {
            if(contents.isEmpty())
                return InteractionResult.PASS;

            Player player = context.getPlayer();
            Level level = context.getLevel();

            // 寻找下界疣
            Optional<SeedEntry> netherWartEntry = contents.findNetherWart();
            if(netherWartEntry.isEmpty())
                return InteractionResult.PASS;

            SeedEntry seedEntry = netherWartEntry.get();
            ItemStack seed = seedEntry.seed.copy();
            seed.setCount(1);

            if(player == null || !player.isShiftKeyDown()) {
                return plantSingleNetherWart(contents, context, seed, soulSandPos, seedEntry);
            } else {
                return plantNetherWartInArea(contents, context, seed, soulSandPos, seedEntry);
            }
        });
    }

    private InteractionResult plantSingleSeed(BagContents contents, UseOnContext context, ItemStack seed, BlockPos farmlandPos, SeedEntry seedEntry) {
        Player player = context.getPlayer();
        Level level = context.getLevel();

        InteractionResult result = tryPlantSeed(context, level, farmlandPos.above(), seed);
        if(result.consumesAction() && (player == null || !player.getAbilities().instabuild)) {
            contents.removeSeed(seedEntry.seed, 1);
        }
        return result;
    }

    private InteractionResult plantSingleNetherWart(BagContents contents, UseOnContext context, ItemStack seed, BlockPos soulSandPos, SeedEntry seedEntry) {
        Player player = context.getPlayer();
        Level level = context.getLevel();

        InteractionResult result = tryPlantNetherWart(level, soulSandPos.above(), seed);
        if(result.consumesAction() && (player == null || !player.getAbilities().instabuild)) {
            contents.removeSeed(seedEntry.seed, 1);
        }
        return result;
    }

    private InteractionResult plantInArea(BagContents contents, UseOnContext context, ItemStack seed, BlockPos centerPos, SeedEntry seedEntry) {
        Player player = context.getPlayer();
        Level level = context.getLevel();

        InteractionResult bestRes = InteractionResult.FAIL;
        int range = 1;
        int plantedCount = 0;

        for(int x = -range; x <= range; x++) {
            for(int z = -range; z <= range; z++) {
                if(contents.getSeedCount(seedEntry.seed) <= 0)
                    break;

                BlockPos checkPos = centerPos.offset(x, 0, z);
                if(level.getBlockState(checkPos).getBlock() instanceof FarmBlock) {
                    InteractionResult res = tryPlantSeed(context, level, checkPos.above(), seed);
                    if(res.consumesAction()) {
                        contents.removeSeed(seedEntry.seed, 1);
                        plantedCount++;
                        if(!bestRes.consumesAction())
                            bestRes = res;
                    }
                }
            }
        }

        return bestRes;
    }

    private InteractionResult plantNetherWartInArea(BagContents contents, UseOnContext context, ItemStack seed, BlockPos centerPos, SeedEntry seedEntry) {
        Player player = context.getPlayer();
        Level level = context.getLevel();

        InteractionResult bestRes = InteractionResult.FAIL;
        int range = 1;
        int plantedCount = 0;

        for(int x = -range; x <= range; x++) {
            for(int z = -range; z <= range; z++) {
                if(contents.getSeedCount(seedEntry.seed) <= 0)
                    break;

                BlockPos checkPos = centerPos.offset(x, 0, z);
                if(level.getBlockState(checkPos).is(net.minecraft.world.level.block.Blocks.SOUL_SAND)) {
                    InteractionResult res = tryPlantNetherWart(level, checkPos.above(), seed);
                    if(res.consumesAction()) {
                        contents.removeSeed(seedEntry.seed, 1);
                        plantedCount++;
                        if(!bestRes.consumesAction())
                            bestRes = res;
                    }
                }
            }
        }

        return bestRes;
    }

    // 修改方法签名，添加UseOnContext参数
    private InteractionResult tryPlantSeed(UseOnContext context, Level level, BlockPos plantPos, ItemStack seed) {
        if (!level.isEmptyBlock(plantPos)) {
            return InteractionResult.FAIL;
        }

        Block block = Block.byItem(seed.getItem());
        if (block == null) {
            return InteractionResult.FAIL;
        }

        // 检查是否是作物
        boolean isCrop = block instanceof CropBlock;

        if (!isCrop) {
            return InteractionResult.FAIL;
        }

        BlockState plantState = block.defaultBlockState();

        if (plantState.canSurvive(level, plantPos)) {
            level.setBlock(plantPos, plantState, 3);
            level.playSound(null, plantPos, SoundEvents.CROP_PLANTED, net.minecraft.sounds.SoundSource.BLOCKS, 0.8F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    private InteractionResult tryPlantNetherWart(Level level, BlockPos plantPos, ItemStack seed) {
        if (!level.isEmptyBlock(plantPos)) {
            return InteractionResult.FAIL;
        }

        Block block = Block.byItem(seed.getItem());
        if (block == null || seed.getItem() != Items.NETHER_WART) {
            return InteractionResult.FAIL;
        }

        BlockState plantState = block.defaultBlockState();

        if (plantState.canSurvive(level, plantPos)) {
            level.setBlock(plantPos, plantState, 3);
            level.playSound(null, plantPos, SoundEvents.CROP_PLANTED, net.minecraft.sounds.SoundSource.BLOCKS, 0.8F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("item.kaleidoscope_nether.seed_bag.description").withStyle(ChatFormatting.GRAY));
    }

    // 添加工具提示图像组件
    @NotNull
    @Override
    public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        BagContents contents = getContents(stack);
        if (!contents.isEmpty()) {
            return Optional.of(new SeedBagTooltip(stack));
        }
        return Optional.empty();
    }

    // 添加自动拾取功能
    public static boolean tryAutoPickup(Player player, ItemStack seedStack) {
        if (player == null || seedStack.isEmpty() || !isSeed(seedStack)) {
            return false;
        }

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof SeedBagItem) {
                boolean absorbed = mutateContents(stack, contents -> {
                    if (contents.absorbWithAutoPickup(seedStack)) {
                        return true;
                    }
                    return false;
                });

                if (absorbed) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isSeed(ItemStack stack) {
        return stack.getItem() == Items.WHEAT_SEEDS ||
                stack.getItem() == Items.CARROT ||
                stack.getItem() == Items.POTATO ||
                stack.getItem() == Items.BEETROOT_SEEDS ||
                stack.getItem() == Items.MELON_SEEDS ||
                stack.getItem() == Items.PUMPKIN_SEEDS ||
                stack.getItem() == Items.NETHER_WART ||
                (stack.getItem() instanceof BlockItem &&
                        ((BlockItem)stack.getItem()).getBlock() instanceof CropBlock);
    }

    public static void registerItemProperties() {
        ItemProperties.register(com.bmt.kaleidoscope_nether.registry.ModItems.SEED_BAG.get(),
                ResourceLocation.fromNamespaceAndPath("kaleidoscope_nether", "has_items"),
                (stack, level, entity, seed) -> {
                    BagContents contents = getContents(stack);
                    return contents.isEmpty() ? 0.0F : 1.0F;
                });
    }

    public static class SeedBagTooltip implements TooltipComponent {
        private final ItemStack seedBagStack;

        public SeedBagTooltip(ItemStack seedBagStack) {
            this.seedBagStack = seedBagStack;
        }

        public ItemStack getSeedBagStack() {
            return seedBagStack;
        }
    }

    public static class BagContents {
        public static final String TAG_SEED_ENTRIES = "seedEntries";
        public static final String TAG_SEED_ITEM = "seed";
        public static final String TAG_SEED_COUNT = "count";

        private final List<SeedEntry> entries = new ArrayList<>();

        public ItemStack writeToStack(ItemStack target) {
            CompoundTag tag = target.getTag();

            if(isEmpty()) {
                if(tag != null) {
                    tag.remove(TAG_SEED_ENTRIES);
                    if(tag.isEmpty())
                        target.setTag(null);
                }
            } else {
                ListTag entriesTag = new ListTag();
                for(SeedEntry entry : entries) {
                    CompoundTag entryTag = new CompoundTag();
                    CompoundTag itemTag = new CompoundTag();
                    entry.seed.save(itemTag);
                    entryTag.put(TAG_SEED_ITEM, itemTag);
                    entryTag.putInt(TAG_SEED_COUNT, entry.count);
                    entriesTag.add(entryTag);
                }
                target.getOrCreateTag().put(TAG_SEED_ENTRIES, entriesTag);
            }

            return target;
        }

        public static BagContents readFromStack(ItemStack target) {
            CompoundTag tag = target.getTag();
            BagContents contents = new BagContents();

            if(tag != null && tag.contains(TAG_SEED_ENTRIES)) {
                ListTag entriesTag = tag.getList(TAG_SEED_ENTRIES, CompoundTag.TAG_COMPOUND);
                for(int i = 0; i < entriesTag.size(); i++) {
                    CompoundTag entryTag = entriesTag.getCompound(i);
                    ItemStack seed = ItemStack.of(entryTag.getCompound(TAG_SEED_ITEM));
                    int count = entryTag.getInt(TAG_SEED_COUNT);
                    if(!seed.isEmpty() && count > 0) {
                        contents.entries.add(new SeedEntry(seed, count));
                    }
                }
            }

            return contents;
        }

        public static int readTotalCountFromStack(ItemStack target) {
            if (target.hasTag() && target.getTag().contains(TAG_SEED_ENTRIES)) {
                ListTag entriesTag = target.getTag().getList(TAG_SEED_ENTRIES, CompoundTag.TAG_COMPOUND);
                int total = 0;
                for(int i = 0; i < entriesTag.size(); i++) {
                    CompoundTag entryTag = entriesTag.getCompound(i);
                    total += entryTag.getInt(TAG_SEED_COUNT);
                }
                return total;
            }
            return 0;
        }

        public static <T> T mutate(ItemStack seedBag, Function<BagContents, T> action) {
            BagContents contents = readFromStack(seedBag);
            T result = action.apply(contents);
            contents.writeToStack(seedBag);
            return result;
        }

        public boolean isEmpty() {
            return entries.isEmpty();
        }

        public int getTotalCount() {
            return entries.stream().mapToInt(entry -> entry.count).sum();
        }

        public int getSeedTypesCount() {
            return entries.size();
        }

        public int getSeedCount(ItemStack seed) {
            return entries.stream()
                    .filter(entry -> ItemStack.isSameItemSameTags(entry.seed, seed))
                    .mapToInt(entry -> entry.count)
                    .findFirst()
                    .orElse(0);
        }

        // 添加公共访问方法
        public List<SeedEntry> getEntries() {
            return Collections.unmodifiableList(entries);
        }

        public boolean absorb(ItemStack other) {
            return absorbWithAutoPickup(other, false);
        }

        public boolean absorbWithAutoPickup(ItemStack other) {
            return absorbWithAutoPickup(other, true);
        }

        private boolean absorbWithAutoPickup(ItemStack other, boolean isAutoPickup) {
            if(!canFit(other))
                return false;

            Optional<SeedEntry> existingEntry = entries.stream()
                    .filter(entry -> ItemStack.isSameItemSameTags(entry.seed, other))
                    .findFirst();

            int toMove = Math.min(MAX_TOTAL_SEEDS - getTotalCount(), other.getCount());

            if(toMove == 0)
                return false;

            if(existingEntry.isPresent()) {
                // 如果已有该种子，增加数量
                SeedEntry entry = existingEntry.get();
                entry.count += toMove;

                // 如果是自动拾取，不改变位置
                if (!isAutoPickup) {
                    // 手动添加时移动到列表开头
                    entries.remove(entry);
                    entries.add(0, entry);
                }
            } else {
                if(entries.size() >= MAX_SEED_TYPES)
                    return false;

                ItemStack newSeed = other.copy();
                newSeed.setCount(1);

                SeedEntry newEntry = new SeedEntry(newSeed, toMove);

                if (isAutoPickup) {
                    // 新种子放在第二个位置
                    if (entries.isEmpty()) {
                        entries.add(newEntry);
                    } else {
                        entries.add(1, newEntry);
                    }
                } else {
                    // 新种子放在列表开头
                    entries.add(0, newEntry);
                }
            }

            other.shrink(toMove);
            return true;
        }

        public ItemStack splitOneStack() {
            if(isEmpty())
                return ItemStack.EMPTY;

            // 从列表开头取出（最后放入的种子）
            SeedEntry firstEntry = entries.get(0);
            int toTake = Math.min(firstEntry.count, firstEntry.seed.getMaxStackSize());

            ItemStack result = firstEntry.seed.copy();
            result.setCount(toTake);

            removeSeed(firstEntry.seed, toTake);
            return result;
        }

        public void removeSeed(ItemStack seed, int amount) {
            Optional<SeedEntry> entry = entries.stream()
                    .filter(e -> ItemStack.isSameItemSameTags(e.seed, seed))
                    .findFirst();

            if(entry.isPresent()) {
                SeedEntry seedEntry = entry.get();
                seedEntry.count -= amount;
                if(seedEntry.count <= 0) {
                    entries.remove(seedEntry);
                }
            }
        }

        public boolean canFit(ItemStack other) {
            if(!isSeed(other))
                return false;

            int totalCount = getTotalCount();
            if(totalCount >= MAX_TOTAL_SEEDS)
                return false;

            boolean typeExists = entries.stream()
                    .anyMatch(entry -> ItemStack.isSameItemSameTags(entry.seed, other));

            if(typeExists) {
                return totalCount < MAX_TOTAL_SEEDS;
            } else {
                return entries.size() < MAX_SEED_TYPES && totalCount < MAX_TOTAL_SEEDS;
            }
        }

        public Optional<SeedEntry> findSuitableSeedForFarmland(Level level, BlockPos plantPos) {
            if(!level.isEmptyBlock(plantPos))
                return Optional.empty();

            // 按顺序从左至右选择第一个合适的种子
            for (SeedEntry entry : entries) {
                Block block = Block.byItem(entry.seed.getItem());

                // 检查是否是作物
                boolean isCrop = block instanceof CropBlock;

                if (isCrop) {
                    if (block.defaultBlockState().canSurvive(level, plantPos)) {
                        return Optional.of(entry);
                    }
                }
            }
            return Optional.empty();
        }

        public Optional<SeedEntry> findNetherWart() {
            // 寻找下界疣
            for (SeedEntry entry : entries) {
                if (entry.seed.getItem() == Items.NETHER_WART) {
                    return Optional.of(entry);
                }
            }
            return Optional.empty();
        }

        private boolean isSeed(ItemStack stack) {
            return stack.getItem() == Items.WHEAT_SEEDS ||
                    stack.getItem() == Items.CARROT ||
                    stack.getItem() == Items.POTATO ||
                    stack.getItem() == Items.BEETROOT_SEEDS ||
                    stack.getItem() == Items.MELON_SEEDS ||
                    stack.getItem() == Items.PUMPKIN_SEEDS ||
                    stack.getItem() == Items.NETHER_WART ||
                    (stack.getItem() instanceof BlockItem &&
                            ((BlockItem)stack.getItem()).getBlock() instanceof CropBlock);
        }
    }

    public static class SeedEntry {
        public final ItemStack seed;
        public int count;

        public SeedEntry(ItemStack seed, int count) {
            this.seed = seed.copy();
            this.seed.setCount(1);
            this.count = count;
        }
    }
}