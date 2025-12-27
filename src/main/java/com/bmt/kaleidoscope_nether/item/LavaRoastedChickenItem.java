package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.registry.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LavaRoastedChickenItem extends Item {
    public LavaRoastedChickenItem(FoodProperties food) {
        super(new Properties().food(food));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            level.playSound(null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.LAVA_ROASTED_CHICKEN_EAT.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F);

            entity.setSecondsOnFire(29);
        }

        return super.finishUsingItem(stack, level, entity);
    }
}