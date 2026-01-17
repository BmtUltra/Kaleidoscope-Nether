package com.bmt.kaleidoscope_nether.item.SpecialFood;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SoulReturnRiceItem extends Item {
    public SoulReturnRiceItem(FoodProperties food) {
        super(new Properties().food(food));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("item.kaleidoscope_nether.soul_return_rice.tooltip.line1"));
        tooltip.add(Component.translatable("item.kaleidoscope_nether.soul_return_rice.tooltip.line2").withStyle(ChatFormatting.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && entity instanceof ServerPlayer player) {
            Optional<GlobalPos> deathLocation = player.getLastDeathLocation();
            if (deathLocation.isPresent()) {
                GlobalPos globalPos = deathLocation.get();
                ServerLevel targetLevel = player.server.getLevel(globalPos.dimension());

                if (targetLevel != null) {
                    BlockPos pos = globalPos.pos();

                    if (player.level().dimension() == globalPos.dimension()) {
                        player.teleportTo(targetLevel,
                                pos.getX() + 0.5,
                                pos.getY(),
                                pos.getZ() + 0.5,
                                player.getYRot(),
                                player.getXRot());
                    } else {
                        player.teleportTo(targetLevel,
                                pos.getX() + 0.5,
                                pos.getY(),
                                pos.getZ() + 0.5,
                                player.getYRot(),
                                player.getXRot());
                    }

                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }

            player.addEffect(new MobEffectInstance(
                    KNEffects.GHOST_BUFF.get(),
                    45 * 20,
                    0,
                    false,
                    false,
                    true
            ));
        }

        return result;
    }
}