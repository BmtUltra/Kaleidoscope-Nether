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

        if (blockState.is(KNTags.Blocks.SOUL_SOIL_SAND)) {
            if (heldItem.is(KNTags.Items.SOUL_PEPPER_TRANSFORMABLE)) {
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }

                ItemStack soulPepper = new ItemStack(ModItems.SOUL_PEPPER.get());
                if (!player.getInventory().add(soulPepper)) {
                    player.drop(soulPepper, false);
                }

                if (!level.isClientSide()) {
                    spawnSoulParticles(level, pos, player);
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    private static void spawnSoulParticles(Level level, BlockPos pos, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;

            int particleCount = 20;

            for (int i = 0; i < particleCount; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * 0.8;
                double offsetY = level.random.nextDouble() * 0.5;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.8;

                double speedX = (level.random.nextDouble() - 0.5) * 0.02;
                double speedY = level.random.nextDouble() * 0.03 + 0.01;
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