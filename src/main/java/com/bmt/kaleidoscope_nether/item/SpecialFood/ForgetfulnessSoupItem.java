package com.bmt.kaleidoscope_nether.item.SpecialFood;

import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgetfulnessSoupItem extends BowlFoodOnlyItem {
    public ForgetfulnessSoupItem(FoodProperties food) {
        super(food);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.kaleidoscope_nether.forgetfulness_soup.tooltip.line1")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof ServerPlayer player) {
            if (player.getRespawnPosition() != null) {
                player.teleportTo(
                        player.server.getLevel(player.getRespawnDimension()),
                        player.getRespawnPosition().getX() + 0.5,
                        player.getRespawnPosition().getY(),
                        player.getRespawnPosition().getZ() + 0.5,
                        player.getYRot(),
                        player.getXRot()
                );
            } else {
                player.teleportTo(
                        player.server.overworld(),
                        player.server.overworld().getSharedSpawnPos().getX() + 0.5,
                        player.server.overworld().getSharedSpawnPos().getY(),
                        player.server.overworld().getSharedSpawnPos().getZ() + 0.5,
                        player.getYRot(),
                        player.getXRot()
                );
            }

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return result;
    }
}