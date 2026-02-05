package com.chadate.kaleidoscope_nether.registry;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Optional;

public class KNEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, KaleidoscopeNether.MOD_ID);

    public static final DeferredHolder<Enchantment, Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker",
            () -> {
                HolderSet<net.minecraft.world.item.Item> supportedItems =
                        BuiltInRegistries.ITEM.getOrCreateTag(ItemTags.FOOT_ARMOR_ENCHANTABLE);

                Enchantment.EnchantmentDefinition definition = new Enchantment.EnchantmentDefinition(
                        supportedItems,
                        Optional.empty(),
                        2,
                        1,
                        new Enchantment.Cost(10, 0),
                        new Enchantment.Cost(40, 0),
                        1,
                        List.of(EquipmentSlotGroup.FEET)
                );

                Component description = Component.translatable("enchantment.kaleidoscope_nether.lava_walker");

                HolderSet<Enchantment> exclusiveSet = HolderSet.empty();

                DataComponentMap effects = DataComponentMap.EMPTY;

                return new Enchantment(description, definition, exclusiveSet, effects);
            });
}