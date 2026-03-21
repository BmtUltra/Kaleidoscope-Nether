package com.bmt.kaleidoscope_nether.tier;

import com.bmt.kaleidoscope_nether.init.KNItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class PrimitiveMacheteTier implements Tier {
    private static final ForgeTier BASE_TIER = new ForgeTier(
            4,
            2031,
            7.0F,
            0,
            15,
            null,
            () -> Ingredient.of(KNItems.HOGLIN_TUSK.get())
    );

    @Override
    public int getUses() {
        return BASE_TIER.getUses();
    }

    @Override
    public float getSpeed() {
        return BASE_TIER.getSpeed();
    }

    @Override
    public float getAttackDamageBonus() {
        return 7.0F;
    }

    @Override
    public int getLevel() {
        return BASE_TIER.getLevel();
    }

    @Override
    public int getEnchantmentValue() {
        return BASE_TIER.getEnchantmentValue();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return BASE_TIER.getRepairIngredient();
    }
}