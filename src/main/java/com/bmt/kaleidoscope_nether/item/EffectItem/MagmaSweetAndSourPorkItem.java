package com.bmt.kaleidoscope_nether.item.EffectItem;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagmaSweetAndSourPorkItem extends Item {

    private static final boolean IS_CATACLYSM_INSTALLED = ModList.get().isLoaded("cataclysm");

    public MagmaSweetAndSourPorkItem(FoodProperties food) {
        super(new Item.Properties().food(food));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (IS_CATACLYSM_INSTALLED) {
                spawnFlameWaves(level, player);
            }

            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4800, 0, false, false, true));
        }
        return super.finishUsingItem(stack, level, entity);
    }

    private void spawnFlameWaves(Level level, Player player) {
        if (!IS_CATACLYSM_INSTALLED) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY() + 1.0D;
        double playerZ = player.getZ();
        int standingOnY = Mth.floor(player.getY()) - 2;

        for (int direction = 0; direction < 8; direction++) {
            float yawRadians = (float) (Math.toRadians(90 + direction * 45)); //每45度一个方向

            for (int distance = 0; distance < 5; distance++) {
                double offset = 2.25D * (distance + 1);
                int waitTime = (int) (1.5F * distance);

                double targetX = playerX + (double) Mth.cos(yawRadians) * offset;
                double targetZ = playerZ + (double) Mth.sin(yawRadians) * offset;

                spawnFlameStrike(level, targetX, targetZ, standingOnY, playerY, yawRadians,
                        40, waitTime, waitTime, 1.0f, player);
            }
        }
    }

    private boolean spawnFlameStrike(Level level, double x, double z, double minY, double maxY,
                                     float rotation, int duration, int wait, int delay,
                                     float radius, LivingEntity player) {
        BlockPos blockpos = BlockPos.containing(x, maxY, z);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = level.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(level, blockpos1, Direction.UP)) {
                if (!level.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(level, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }
                flag = true;
                break;
            }
            blockpos = blockpos.below();
        } while (blockpos.getY() >= minY);

        if (flag) {
            spawnCataclysmFlameStrike(level, x, (double) blockpos.getY() + d0, z, rotation,
                    duration, wait, delay, radius, player);
            return true;
        }
        return false;
    }

    private void spawnCataclysmFlameStrike(Level level, double x, double y, double z,
                                           float rotation, int duration, int wait, int delay,
                                           float radius, LivingEntity player) {
        try {
            Class<?> flameStrikeClass = Class.forName("com.github.L_Ender.cataclysm.entity.effect.Flame_Strike_Entity");
            Object flameStrike = flameStrikeClass.getConstructor(
                    Level.class, double.class, double.class, double.class,
                    float.class, int.class, int.class, int.class,
                    float.class, float.class, float.class, boolean.class, LivingEntity.class
            ).newInstance(
                    level, x, y, z, rotation, duration, wait, delay,
                    radius, 6.0F, 2.0F, false, player
            );

            level.addFreshEntity((net.minecraft.world.entity.Entity) flameStrike);
        } catch (Exception e) {
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltipComponents, flag);

        tooltipComponents.add(Component.translatable("item.kaleidoscope_nether.magma_sweet_and_sour_pork.tooltip.line1")
                .withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.kaleidoscope_nether.magma_sweet_and_sour_pork.tooltip.line2")
                .withStyle(ChatFormatting.GOLD));
    }
}