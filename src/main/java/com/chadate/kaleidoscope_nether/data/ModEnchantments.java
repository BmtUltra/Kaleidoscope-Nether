package com.chadate.kaleidoscope_nether.data;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> LAVA_WALKER = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, "lava_walker")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        context.register(
                LAVA_WALKER,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                2, // weight
                                1, // max level
                                Enchantment.dynamicCost(10, 0),
                                Enchantment.dynamicCost(40, 0),
                                1, // anvil cost
                                EquipmentSlotGroup.FEET
                        )
                ).build(LAVA_WALKER.location())
        );
    }
}
