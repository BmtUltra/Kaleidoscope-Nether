//package com.bmt.kaleidoscope_nether.event;
//
//import com.bmt.kaleidoscope_nether.registry.KNEnchantments;
//import com.bmt.kaleidoscope_nether.registry.KNItems;
//import net.fabricmc.fabric.api.event.player.UseBlockCallback;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.component.DataComponents;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.ItemEnchantments;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.AnvilBlock;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.BlockHitResult;
//
//public class AnvilEventHandler {
//
//    private static final String SHELL_COUNT_TAG = "StriderShellCount";
//    private static final int REQUIRED_SHELLS = 1;
//
//    public static void registerEvents() {
//        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
//            if (hand != player.getUsedItemHand()) return InteractionResult.PASS;
//
//            BlockPos pos = hitResult.getBlockPos();
//            BlockState state = level.getBlockState(pos);
//
//            if (state.getBlock() instanceof AnvilBlock) {
//                return onAnvilUse(player, level, hitResult);
//            }
//
//            return InteractionResult.PASS;
//        });
//    }
//
//    private static InteractionResult onAnvilUse(Player player, Level level, BlockHitResult hitResult) {
//        ItemStack left = player.getMainHandItem();
//        ItemStack right = player.getOffhandItem();
//
//        if (isBoots(left) && right.is(KNItems.STRIDER_ROCK_SHELL)) {
//            ItemEnchantments enchantments = left.getEnchantments();
//            if (enchantments.getLevel(level.holderOrThrow(KNEnchantments.LAVA_WALKER)) > 0) {
//                return InteractionResult.PASS;
//            }
//
//            if (right.getCount() < REQUIRED_SHELLS) {
//                return InteractionResult.PASS;
//            }
//
//            ItemStack result = left.copy();
//
//            ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(result.getEnchantments());
//            mutable.set(level.holderOrThrow(KNEnchantments.LAVA_WALKER), 1);
//            result.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());
//
//            CompoundTag tag = left.getOrDefault(DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY)
//                    .copyTag();
//            if (tag.contains(SHELL_COUNT_TAG)) {
//                tag.remove(SHELL_COUNT_TAG);
//            }
//
//            if (!tag.isEmpty()) {
//                result.set(DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.of(tag));
//            }
//
//            right.shrink(REQUIRED_SHELLS);
//
//            player.setItemInHand(player.getUsedItemHand(), result);
//
//            return InteractionResult.SUCCESS;
//        }
//        return InteractionResult.PASS;
//    }
//
//    private static boolean isBoots(ItemStack stack) {
//        if (stack.isEmpty()) return false;
//
//        if (stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem) {
//            return armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
//        }
//        return false;
//    }
//}