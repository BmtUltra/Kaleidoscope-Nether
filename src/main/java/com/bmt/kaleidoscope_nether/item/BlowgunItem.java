package com.bmt.kaleidoscope_nether.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class BlowgunItem extends ProjectileWeaponItem {
    public static final int MAX_DRAW_DURATION = 7;

    public BlowgunItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity shooter, int timeLeft) {
        if (shooter instanceof Player player) {
            boolean creative = player.getAbilities().instabuild;
            ItemStack projectile = player.getProjectile(stack);

            if (!projectile.isEmpty() || creative) {
                if (projectile.isEmpty()) {
                    projectile = new ItemStack(Items.ARROW);
                }

                int useDuration = this.getUseDuration(stack) - timeLeft;
                useDuration = Math.min(useDuration, MAX_DRAW_DURATION);

                float power = getPowerForTime(useDuration);
                if (power >= 0.1F) {
                    if (!level.isClientSide) {
                        ArrowItem arrowItem = (ArrowItem)(projectile.getItem() instanceof ArrowItem ? projectile.getItem() : Items.ARROW);
                        AbstractArrow arrow = arrowItem.createArrow(level, projectile, shooter);

                        arrow.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, power * 3.0F, 1.0F);

                        arrow.setBaseDamage(arrow.getBaseDamage() * 0.8);

                        if (creative) {
                            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        } else {
                            projectile.shrink(1);
                            if (projectile.isEmpty()) {
                                player.getInventory().removeItem(projectile);
                            }
                        }

                        level.addFreshEntity(arrow);
                    }

                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                            1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);

                    player.awardStat(Stats.ITEM_USED.get(this));

                    if (!player.getAbilities().instabuild) {
                        stack.hurtAndBreak(1, player, (p) -> {
                            p.broadcastBreakEvent(player.getUsedItemHand());
                        });
                    }
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean hasAmmo = !player.getProjectile(stack).isEmpty();

        if (!player.getAbilities().instabuild && !hasAmmo) {
            return InteractionResultHolder.fail(stack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 72000;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    private static float getPowerForTime(int useTime) {
        float power = (float)useTime / (float)MAX_DRAW_DURATION;
        if (power > 1.0F) {
            power = 1.0F;
        }
        return power;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return false;
    }
}