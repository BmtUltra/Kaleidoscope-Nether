package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;

public class CrimsonBuffEffect extends MobEffect {
    public CrimsonBuffEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static boolean isInNether(LivingEntity livingEntity) {
        return livingEntity.level().dimension().equals(Level.NETHER);
    }

    public static boolean isUndead(LivingEntity target) {
        if (target instanceof Zombie ||
                target instanceof Skeleton ||
                target instanceof WitherSkeleton ||
                target instanceof ZombifiedPiglin ||
                target instanceof Phantom ||
                target instanceof WitherBoss) {
            return true;
        }

        return target.getType().is(net.minecraft.tags.EntityTypeTags.SKELETONS);
    }

    public static float getDamageMultiplier(LivingEntity attacker, LivingEntity target) {
        if (attacker.hasEffect(ModEffects.CRIMSON_BUFF.get())) {
            if (isInNether(attacker)) {
                return 1.3f;
            } else if (isUndead(target)) {
                return 1.3f;
            }
        }
        return 1.0f;
    }
}