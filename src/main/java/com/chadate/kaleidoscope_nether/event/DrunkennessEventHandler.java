package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.effect.WarpedDrunkennessEffect;
import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@EventBusSubscriber(modid = "kaleidoscope_nether")
public class DrunkennessEventHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KNEffects.WARPED_DRUNKENNESS)) {
            int amplifier = Objects.requireNonNull(entity.getEffect(KNEffects.WARPED_DRUNKENNESS)).getAmplifier();
            WarpedDrunkennessEffect effect = (WarpedDrunkennessEffect) KNEffects.WARPED_DRUNKENNESS.get();
            effect.onHurt(entity, event.getNewDamage(), amplifier);
        }
    }
}