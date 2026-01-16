package com.bmt.kaleidoscope_nether.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GhastPendantItem extends Item {

    public GhastPendantItem() {
        super(new Item.Properties()
                .rarity(Rarity.COMMON)
                .stacksTo(1));
    }

    public static boolean hasGhastPendantInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.getItem() instanceof GhastPendantItem) {
                return true;
            }
        }

        for (ItemStack stack : player.getInventory().offhand) {
            if (!stack.isEmpty() && stack.getItem() instanceof GhastPendantItem) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.kaleidoscope_nether.ghast_pendant.tooltip.line1"));
    }
}