package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.init.KNItems;
import com.bmt.kaleidoscope_nether.init.KNEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnvilEventHandler {

    private static final String SHELL_COUNT_TAG = "StriderShellCount";
    private static final int REQUIRED_SHELLS = 1;

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (isBoots(left) && right.is(KNItems.STRIDER_ROCK_SHELL.get())) {
            if (EnchantmentHelper.getTagEnchantmentLevel(KNEnchantments.LAVA_WALKER.get(), left) > 0) {
                return;
            }

            CompoundTag tag = left.getOrCreateTag();
            int currentShells = tag.getInt(SHELL_COUNT_TAG);

            if (currentShells >= REQUIRED_SHELLS) {
                return;
            }

            int shellsToUse = 1;

            if (right.getCount() < shellsToUse) {
                return;
            }

            ItemStack result = left.copy();
            CompoundTag resultTag = result.getOrCreateTag();

            int newShellCount = currentShells + shellsToUse;
            resultTag.putInt(SHELL_COUNT_TAG, newShellCount);

            if (newShellCount >= REQUIRED_SHELLS) {
                result.enchant(KNEnchantments.LAVA_WALKER.get(), 1);
                resultTag.remove(SHELL_COUNT_TAG);
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