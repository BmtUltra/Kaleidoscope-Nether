package com.bmt.kaleidoscope_nether.enchantment;

import com.bmt.kaleidoscope_nether.item.BlowgunItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PiercingEnchantment extends Enchantment {

    public static final EnchantmentCategory BLOWGUN = EnchantmentCategory.create("blowgun", 
            item -> item instanceof BlowgunItem);
    
    public PiercingEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, BLOWGUN, slots);
    }
    
    @Override
    public int getMinLevel() {
        return 1;
    }
    
    @Override
    public int getMaxLevel() {
        return 4;
    }
    
    @Override
    public int getMinCost(int level) {
        return 1 + (level - 1) * 10;
    }
    
    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 15;
    }
    
    @Override
    public boolean isTreasureOnly() {
        return false;
    }
    
    @Override
    public boolean isTradeable() {
        return true;
    }
    
    @Override
    public boolean isDiscoverable() {
        return true;
    }
    
    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof BlowgunItem;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof BlowgunItem;
    }
    
    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}