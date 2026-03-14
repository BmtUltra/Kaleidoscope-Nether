//package com.bmt.kaleidoscope_nether.enchantment;
//
//import com.bmt.kaleidoscope_nether.item.BlowgunItem;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//
//public class AnesthesiaEnchantment extends Enchantment {
//
//    public static final EnchantmentCategory BLOWGUN = PiercingEnchantment.BLOWGUN;
//
//    public AnesthesiaEnchantment(Rarity rarity, EquipmentSlot... slots) {
//        super(rarity, BLOWGUN, slots);
//    }
//
//    @Override
//    public int getMinLevel() {
//        return 1;
//    }
//
//    @Override
//    public int getMaxLevel() {
//        return 3;
//    }
//
//    @Override
//    public int getMinCost(int level) {
//        return 5 + (level - 1) * 8;
//    }
//
//    @Override
//    public int getMaxCost(int level) {
//        return this.getMinCost(level) + 10;
//    }
//
//    @Override
//    public boolean isTreasureOnly() {
//        return false;
//    }
//
//    @Override
//    public boolean isTradeable() {
//        return true;
//    }
//
//    @Override
//    public boolean isDiscoverable() {
//        return true;
//    }
//
//    @Override
//    public boolean canEnchant(ItemStack stack) {
//        return stack.getItem() instanceof BlowgunItem;
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//        return stack.getItem() instanceof BlowgunItem;
//    }
//
//    @Override
//    public boolean isAllowedOnBooks() {
//        return true;
//    }
//
//    @Override
//    public boolean checkCompatibility(Enchantment other) {
//        return super.checkCompatibility(other);
//    }
//}