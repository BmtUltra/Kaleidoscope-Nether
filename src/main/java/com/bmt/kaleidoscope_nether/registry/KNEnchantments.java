package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker",
            () -> new Enchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET}) {
                @Override
                public int getMinLevel() {
                    return 1;
                }

                @Override
                public int getMaxLevel() {
                    return 1;
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
                public boolean canEnchant(net.minecraft.world.item.ItemStack stack) {
                    return stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem &&
                            armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
                }

                @Override
                public boolean isAllowedOnBooks() {
                    return true;
                }
            });

//    public static final RegistryObject<Enchantment> PIERCING = ENCHANTMENTS.register("piercing",
//            () -> new PiercingEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));
//
//    public static final RegistryObject<Enchantment> ANESTHESIA = ENCHANTMENTS.register("anesthesia",
//            () -> new AnesthesiaEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
}