package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.integration.CuriosIntegration;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class GhastPendantItem extends ArmorItem {
    
    private static final ArmorMaterial GHAST_PENDANT_MATERIAL = new ArmorMaterial() {
        @Override
        public int getDurabilityForType(@NotNull Type type) {
            return 0;
        }

        @Override
        public int getDefenseForType(@NotNull Type type) {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public @NotNull SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }

        @Override
        public @NotNull String getName() {
            return "ghast_pendant";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };

    public GhastPendantItem() {
        super(GHAST_PENDANT_MATERIAL, Type.CHESTPLATE, new Properties()
                .rarity(Rarity.UNCOMMON)
                .stacksTo(1));
    }

    public static boolean isWearingGhastPendant(Player player) {
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof GhastPendantItem) {
            return true;
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