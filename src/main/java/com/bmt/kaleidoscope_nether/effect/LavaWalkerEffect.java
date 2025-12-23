package com.bmt.kaleidoscope_nether.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class LavaWalkerEffect extends MobEffect {
    public LavaWalkerEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!(livingEntity instanceof Player player && player.isSpectator())) {
            Vec3 pos = livingEntity.position();
            Vec3 movement = livingEntity.getDeltaMovement();
            Vec3 futurePos = pos.add(movement);
            BlockPos onPos = livingEntity.getOnPos();
            BlockPos futureBlockPos = new BlockPos((int) futurePos.x, (int) futurePos.y, (int) futurePos.z);

            boolean isMoving = movement.lengthSqr() > 0.0001;

            if (livingEntity.isInLava()) {
                livingEntity.setDeltaMovement(movement.add(0, 0.1, 0));

                if (isMoving && livingEntity.level() instanceof ServerLevel level && livingEntity.tickCount % 5 == 0) {
                    level.sendParticles(ParticleTypes.LAVA,
                            pos.x(), pos.y() + 0.1D, pos.z(),
                            3,
                            0.2, 0.1, 0.2, 1.5);
                }
            } else if (livingEntity.level().getFluidState(onPos).is(FluidTags.LAVA)) {
                if (livingEntity.level() instanceof ServerLevel level && isMoving && livingEntity.tickCount % 5 == 0) {
                    level.sendParticles(ParticleTypes.LAVA,
                            pos.x(), pos.y() + 0.1D, pos.z(),
                            3,
                            0.2, 0.1, 0.2, 1.5);
                }
                livingEntity.setDeltaMovement(movement.x(), Math.max(movement.y(), 0D), movement.z());
                livingEntity.setOnGround(true);
            } else if (livingEntity.level().getFluidState(futureBlockPos).is(FluidTags.LAVA) && movement.y() > -0.8) {
                if (livingEntity.level() instanceof ServerLevel level && isMoving && livingEntity.tickCount % 5 == 0) {
                    level.sendParticles(ParticleTypes.LAVA,
                            pos.x(), pos.y() + 0.1D, pos.z(),
                            3,
                            0.2, 0.1, 0.2, 1.5);
                }
                livingEntity.setDeltaMovement(movement.x(), Math.max(movement.y(), movement.y() * 0.5), movement.z());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}