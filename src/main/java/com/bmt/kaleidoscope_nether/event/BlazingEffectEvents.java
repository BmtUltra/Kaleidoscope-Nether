package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.BlazingEffect;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlazingEffectEvents {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (attacker.hasEffect(ModEffects.BLAZING.get())) {
                int amplifier = attacker.getEffect(ModEffects.BLAZING.get()).getAmplifier();
                BlazingEffect.onAttack(attacker, event.getEntity(), amplifier);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer() != null &&
                event.getPlayer().hasEffect(ModEffects.BLAZING.get()) &&
                event.getPlayer().isShiftKeyDown()) {

            Player player = event.getPlayer();
            int amplifier = player.getEffect(ModEffects.BLAZING.get()).getAmplifier();

            BlockState state = event.getState();
            BlockPos pos = event.getPos();

            if (!event.getLevel().isClientSide() && event.getLevel() instanceof ServerLevel serverLevel) {
                // 获取原始掉落物
                List<ItemStack> drops = Block.getDrops(state, serverLevel, pos, null, player, player.getMainHandItem());

                List<ItemStack> transformedDrops = BlazingEffect.transformDropsAndDropExp(
                        drops, pos, player.getMainHandItem(), serverLevel, amplifier
                );

                event.setCanceled(true);
                serverLevel.destroyBlock(pos, false);

                for (ItemStack stack : transformedDrops) {
                    if (!stack.isEmpty()) {
                        net.minecraft.world.entity.item.ItemEntity itemEntity = new net.minecraft.world.entity.item.ItemEntity(
                                serverLevel,
                                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                stack
                        );
                        serverLevel.addFreshEntity(itemEntity);
                    }
                }
            }
        }
    }
}