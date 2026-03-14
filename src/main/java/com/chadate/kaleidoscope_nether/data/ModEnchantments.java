package com.chadate.kaleidoscope_nether.data;

import com.chadate.kaleidoscope_nether.registry.KNEnchantments;
import com.chadate.kaleidoscope_nether.registry.KNItems;
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

        context.register(
                KNEnchantments.PIERCING,
                Enchantment.enchantment(
                        Enchantment.definition(
                                HolderSet.direct(items.getOrThrow(KNItems.BLOWGUN.getKey())),
                                5,
                                4,
                                Enchantment.dynamicCost(1, 10),
                                Enchantment.dynamicCost(16, 10),
                                2,
                                EquipmentSlotGroup.MAINHAND
                        )
                ).build(KNEnchantments.PIERCING.location())
        );

        context.register(
                KNEnchantments.ANESTHESIA,
                Enchantment.enchantment(
                        Enchantment.definition(
                                HolderSet.direct(items.getOrThrow(KNItems.BLOWGUN.getKey())),
                                2,
                                3,
                                Enchantment.dynamicCost(5, 8),
                                Enchantment.dynamicCost(15, 8),
                                3,
                                EquipmentSlotGroup.MAINHAND
                        )
                ).build(KNEnchantments.ANESTHESIA.location())
        );
    }
}
