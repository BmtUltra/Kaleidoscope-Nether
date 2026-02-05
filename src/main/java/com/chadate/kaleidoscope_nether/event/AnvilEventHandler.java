package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.registry.KNEnchantments;
import com.chadate.kaleidoscope_nether.registry.KNItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

@EventBusSubscriber(modid = com.chadate.kaleidoscope_nether.KaleidoscopeNether.MOD_ID)
public class AnvilEventHandler {

    private static final String SHELL_COUNT_TAG = "StriderShellCount";
    private static final int REQUIRED_SHELLS = 8;

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (isBoots(left) && right.is(KNItems.STRIDER_ROCK_SHELL.get())) {
            ItemEnchantments enchantments = left.getEnchantments();
            if (enchantments.getLevel(KNEnchantments.LAVA_WALKER) > 0) {
                return;
            }

            CompoundTag tag = left.getOrDefault(DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY)
                .copyTag();
            int currentShells = tag.getInt(SHELL_COUNT_TAG);

            if (currentShells >= REQUIRED_SHELLS) {
                return;
            }

            int shellsToUse = 1;

            if (right.getCount() < shellsToUse) {
                return;
            }

            ItemStack result = left.copy();
            CompoundTag resultTag = tag.copy();

            int newShellCount = currentShells + shellsToUse;
            resultTag.putInt(SHELL_COUNT_TAG, newShellCount);

            if (newShellCount >= REQUIRED_SHELLS) {
                // 应用附魔
                ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(result.getEnchantments());
                mutable.set(KNEnchantments.LAVA_WALKER, 1);
                result.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());
                resultTag.remove(SHELL_COUNT_TAG);
            }

            // 写回自定义数据
            if (!resultTag.isEmpty()) {
                result.set(DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.of(resultTag));
            }

            // 调整名称以显示强化进度（可选）
            if (currentShells < REQUIRED_SHELLS) {
                result.set(DataComponents.CUSTOM_NAME, Component.translatable(
                    result.getDescriptionId()
                ));
            }

            event.setOutput(result);
            event.setCost(shellsToUse);
            event.setMaterialCost(shellsToUse);
        }
    }

    private static boolean isBoots(ItemStack stack) {
        if (stack.isEmpty()) return false;

        if (stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem) {
            return armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
        }
        return false;
    }
}