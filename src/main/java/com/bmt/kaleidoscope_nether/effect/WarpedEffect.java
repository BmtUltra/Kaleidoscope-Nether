package com.bmt.kaleidoscope_nether.effect;

import com.bmt.kaleidoscope_nether.config.Config;
import com.bmt.kaleidoscope_nether.init.KNEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class WarpedEffect extends MobEffect {
    public WarpedEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static boolean shouldAffectMob(LivingEntity target, LivingEntity player) {
        if (!player.hasEffect(KNEffects.WARPED.get())) {
            return false;
        }
        return isAffectedMob(target);
    }

    public static boolean isAffectedMob(LivingEntity entity) {
        List<? extends String> affectedMobIds = Config.WARPED_BUFF_AFFECTED_MOBS.get();

        if (affectedMobIds.isEmpty()) {
            return false;
        }

        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        if (entityId == null) {
            return false;
        }

        String entityIdString = entityId.toString();
        return affectedMobIds.contains(entityIdString);
    }
}