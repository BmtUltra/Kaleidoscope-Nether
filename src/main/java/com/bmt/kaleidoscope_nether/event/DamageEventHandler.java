package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.init.KNEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageEventHandler {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();

        if (entity.hasEffect(KNEffects.TROPICAL_STRIDER.get())) {
            if (source.is(DamageTypeTags.IS_FIRE)) {
                event.setCanceled(true);
                entity.clearFire();
            }
        }
    }
}