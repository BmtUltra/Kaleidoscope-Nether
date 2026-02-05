package com.chadate.kaleidoscope_nether.effect;

import com.chadate.kaleidoscope_nether.config.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class WarpedBuffEffect extends MobEffect {
    public WarpedBuffEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    public static boolean isAffectedMob(LivingEntity entity) {
        List<? extends String> affectedMobIds = Config.WARPED_BUFF_AFFECTED_MOBS.get();

        if (affectedMobIds.isEmpty()) {
            return false;
        }

        ResourceLocation entityId = Registries.ENTITY_TYPE.location();

        String entityIdString = entityId.toString();
        return affectedMobIds.contains(entityIdString);
    }
}