package com.bmt.kaleidoscope_nether.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BlessingEnchantment extends Enchantment {

    public BlessingEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
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
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem &&
                armorItem.getEquipmentSlot() == EquipmentSlot.CHEST;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}