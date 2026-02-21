package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StarBlessingEffectEvents {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().hasEffect(KNEffects.STAR_BLESSING.get())) {
            float originalDamage = event.getAmount();
            float newDamage = originalDamage * 0.2F;
            event.setAmount(newDamage);
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEffectInstance().getEffect() == KNEffects.STAR_BLESSING.get()) {
            List<net.minecraft.world.effect.MobEffect> effectsToRemove = new ArrayList<>();

            for (MobEffectInstance effect : event.getEntity().getActiveEffects()) {
                if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effect.getEffect());
                }
            }

            for (net.minecraft.world.effect.MobEffect effect : effectsToRemove) {
                event.getEntity().removeEffect(effect);
            }
        }
    }
}