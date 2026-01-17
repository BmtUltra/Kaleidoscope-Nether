package com.bmt.kaleidoscope_nether.item.SpecialFood;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NetherCaterpillarItem extends Item {
    public NetherCaterpillarItem(FoodProperties food) {
        super(new Properties().food(food));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = player.level();

        if (target instanceof Strider strider && strider.isBaby()) {
            if (!level.isClientSide()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                strider.setAge(0);

                level.broadcastEntityEvent(strider, (byte) 18);
                
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        
        return super.interactLivingEntity(stack, player, target, hand);
    }
}