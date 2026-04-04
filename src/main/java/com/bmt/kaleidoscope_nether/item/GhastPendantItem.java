package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.integration.CuriosIntegration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class GhastPendantItem extends Item {

    public GhastPendantItem() {
        super(new Properties()
                .rarity(Rarity.UNCOMMON)
                .stacksTo(1));
    }

    public static boolean hasGhastPendant(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof GhastPendantItem) {
                return true;
            }
        }

        return CuriosIntegration.hasItemInCurios(player, GhastPendantItem.class);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}