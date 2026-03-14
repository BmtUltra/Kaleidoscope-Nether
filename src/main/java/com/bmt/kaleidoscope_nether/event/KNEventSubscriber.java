package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.effect.CrimsonEffect;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.LivingEntity;

public class KNEventSubscriber {

    public static void registerEvents() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (source.getDirectEntity() instanceof LivingEntity attacker) {
                float originalDamage = amount;
                float modifiedDamage = CrimsonEffect.calculateDamageBonus(attacker, originalDamage);
                if (modifiedDamage != originalDamage) {
                }
            }
            return true;
        });
    }
}