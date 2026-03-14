package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffect;

public class DamageEventHandler {

    public static void registerEvents() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            Holder<MobEffect> tropicalStriderHolder = KNEffects.TROPICAL_STRIDER;
            if (entity.hasEffect(tropicalStriderHolder)) {
                if (source.is(DamageTypeTags.IS_FIRE)) {
                    entity.clearFire();
                    return false;
                }
            }
            return true;
        });
    }
}