package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.effect.WarpedDrunkennessEffect;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "kaleidoscope_nether")
public class DrunkennessEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KNEffects.WARPED_DRUNKENNESS.get())) {
            int amplifier = entity.getEffect(KNEffects.WARPED_DRUNKENNESS.get()).getAmplifier();
            WarpedDrunkennessEffect effect = (WarpedDrunkennessEffect) KNEffects.WARPED_DRUNKENNESS.get();
            effect.onHurt(entity, event.getAmount(), amplifier);
        }
    }
}