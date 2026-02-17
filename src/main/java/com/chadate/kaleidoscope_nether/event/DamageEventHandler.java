package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = com.chadate.kaleidoscope_nether.KaleidoscopeNether.MOD_ID)
public class DamageEventHandler {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KNEffects.TROPICAL_STRIDER)) {
            if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.setCanceled(true);
                entity.clearFire();
            }
        }
    }
}