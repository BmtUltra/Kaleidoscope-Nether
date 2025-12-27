package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BlazingEffect extends MobEffect {
    public BlazingEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
    public static void onAttack(LivingEntity attacker, LivingEntity target, int amplifier) {
        if (!attacker.level().isClientSide()) {
            target.setSecondsOnFire(6);
        }
    }
    public static List<ItemStack> transformDropsAndDropExp(List<ItemStack> drops, BlockPos blockPos, ItemStack tool, ServerLevel serverLevel, int amplifier) {
        AtomicReference<Float> expValue = new AtomicReference<>((float) 0);

        for (int i = 0; i < drops.size(); i++) {
            ItemStack itemStack = drops.get(i);
            int finalI = i;

            serverLevel.getRecipeManager().getAllRecipesFor(RecipeType.SMELTING).stream()
                    .filter(smeltingRecipe -> {
                        for (int j = 0; j < smeltingRecipe.getIngredients().size(); j++) {
                            if (smeltingRecipe.getIngredients().get(j).test(itemStack)) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .findFirst()
                    .ifPresent(smeltingRecipe -> {
                        if (serverLevel.random.nextFloat() < 1.0f) {
                            ItemStack result = smeltingRecipe.getResultItem(serverLevel.registryAccess()).copy();
                            result.setCount(result.getCount() * itemStack.getCount());
                            drops.set(finalI, result);
                            expValue.updateAndGet(v -> v + smeltingRecipe.getExperience());
                        }
                    });
        }

        float exp = expValue.get();
        if (exp > 0) {
            int i = Mth.floor(exp);
            float f = Mth.frac(exp);
            if (f != 0.0F && serverLevel.random.nextDouble() < (double) f) {
                ++i;
            }
            if (i > 0) {
                serverLevel.addFreshEntity(new ExperienceOrb(serverLevel,
                        blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, i));
            }
        }

        return drops;
    }
}