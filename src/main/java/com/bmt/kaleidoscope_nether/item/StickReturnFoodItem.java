package com.bmt.kaleidoscope_nether.item;

import com.github.ysbbbbbb.kaleidoscopecookery.api.item.IHasContainer;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

public class StickReturnFoodItem extends FoodWithEffectsItem implements IHasContainer {
    public StickReturnFoodItem(FoodProperties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, entity);
        ItemStack stick = new ItemStack(Items.STICK);

        if (itemStack.isEmpty()) {
            return stick;
        }

        if (entity instanceof Player player) {
            ItemHandlerHelper.giveItemToPlayer(player, stick);
        } else {
            ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), stick);
            level.addFreshEntity(itemEntity);
        }

        return itemStack;
    }

    @Override
    public Item getContainerItem() {
        return Items.STICK;
    }
}