package com.chadate.kaleidoscope_nether.effect;

import com.chadate.kaleidoscope_nether.config.Config;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WarpedBuffEffect extends MobEffect {
    public WarpedBuffEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    public static boolean isAffectedMob(LivingEntity entity) {
        List<? extends String> affectedMobIds = Config.WARPED_BUFF_AFFECTED_MOBS.get();

        if (affectedMobIds.isEmpty()) {
            return false;
        }

        ResourceLocation entityId = entity.getType().builtInRegistryHolder().key().location();
        String entityIdString = entityId.toString();
        return affectedMobIds.contains(entityIdString);
    }

    public static boolean shouldAffectMob(LivingEntity target, LivingEntity player) {
        if (!player.hasEffect(com.chadate.kaleidoscope_nether.registry.KNEffects.WARPED_BUFF)) {
            return false;
        }
        return isAffectedMob(target);
    }
}