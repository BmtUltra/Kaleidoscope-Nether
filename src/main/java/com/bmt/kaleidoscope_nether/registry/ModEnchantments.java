package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.enchantment.BlessingEnchantment;
import com.bmt.kaleidoscope_nether.enchantment.LavaWalkerEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, KaleidoscopeNether.MOD_ID);

    public static final RegistryObject<Enchantment> BLESSING = ENCHANTMENTS.register("blessing",
            () -> new BlessingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR, EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker",
            () -> new LavaWalkerEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR, EquipmentSlot.FEET));
}