package com.bmt.kaleidoscope_nether.registry;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class KNBrewingRecipes {
    public static void register() {
        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.AWKWARD)),
                Ingredient.of(KNItems.POISONOUS_FRUIT.get()),
                PotionUtils.setPotion(Items.POTION.getDefaultInstance(), KNPotions.MYSTERIOUS_POISON.get())
        );
    }
}
