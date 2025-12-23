package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.API.KNTags;
import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoulPepperTransformationHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        ItemStack heldItem = player.getMainHandItem();

        // 检查右键的方块是否是灵魂沙或灵魂土
        if (blockState.is(KNTags.Blocks.SOUL_SOIL_SAND)) {
            // 检查手中的物品是否在可转化标签中
            if (heldItem.is(KNTags.Items.SOUL_PEPPER_TRANSFORMABLE)) {
                // 消耗1个物品
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }

                // 给予玩家1个灵魂椒
                ItemStack soulPepper = new ItemStack(ModItems.SOUL_PEPPER.get());
                if (!player.getInventory().add(soulPepper)) {
                    player.drop(soulPepper, false);
                }

                // 生成粒子效果
                if (!level.isClientSide()) {
                    spawnSoulParticles(level, pos, player);
                }

                // 阻止默认的右键行为
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    private static void spawnSoulParticles(Level level, BlockPos pos, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            // 在方块位置生成粒子
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;

            // 生成多个灵魂粒子，形成向上飘动的效果
            int particleCount = 20;

            for (int i = 0; i < particleCount; i++) {
                // 随机偏移
                double offsetX = (level.random.nextDouble() - 0.5) * 0.8;
                double offsetY = level.random.nextDouble() * 0.5;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.8;

                // 随机速度（缓慢向上飘动）
                double speedX = (level.random.nextDouble() - 0.5) * 0.02;
                double speedY = level.random.nextDouble() * 0.03 + 0.01; // 主要向上飘动
                double speedZ = (level.random.nextDouble() - 0.5) * 0.02;

                serverLevel.sendParticles(
                        ParticleTypes.SOUL,
                        x + offsetX,
                        y + offsetY,
                        z + offsetZ,
                        1,
                        speedX, speedY, speedZ,
                        0.0
                );
            }

            // 在玩家周围也生成一些粒子，表示转化效果
            double playerX = player.getX();
            double playerY = player.getY() + player.getEyeHeight();
            double playerZ = player.getZ();

            for (int i = 0; i < 10; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * player.getBbWidth();
                double offsetY = level.random.nextDouble() * player.getBbHeight() * 0.5;
                double offsetZ = (level.random.nextDouble() - 0.5) * player.getBbWidth();

                double speedX = (level.random.nextDouble() - 0.5) * 0.01;
                double speedY = level.random.nextDouble() * 0.02 + 0.01;
                double speedZ = (level.random.nextDouble() - 0.5) * 0.01;

                serverLevel.sendParticles(
                        ParticleTypes.SOUL,
                        playerX + offsetX,
                        playerY + offsetY,
                        playerZ + offsetZ,
                        1,
                        speedX, speedY, speedZ,
                        0.0
                );
            }
        }
    }
}