package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.registry.KNEnchantments;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        context.register(
                KNEnchantments.LAVA_WALKER,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                2,
                                1,
                                Enchantment.dynamicCost(10, 0),
                                Enchantment.dynamicCost(40, 0),
                                1,
                                EquipmentSlotGroup.FEET
                        )
                ).build(KNEnchantments.LAVA_WALKER.location())
        );
    }
}
