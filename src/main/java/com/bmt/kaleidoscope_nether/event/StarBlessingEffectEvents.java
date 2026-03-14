//package com.bmt.kaleidoscope_nether.event;
//
//import com.bmt.kaleidoscope_nether.registry.KNEffects;
//import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
//import net.minecraft.core.Holder;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.effect.MobEffectInstance;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StarBlessingEffectEvents {
//
//    public static void registerEvents() {
//        ServerLivingEntityEvents.EFFECT_ADDED.register((entity, effect, source) -> {
//            if (effect.getEffect().value() == KNEffects.STAR_BLESSING) {
//                List<Holder<MobEffect>> effectsToRemove = new ArrayList<>();
//                for (var entry : entity.getActiveEffectsMap().entrySet()) {
//                    MobEffectInstance activeEffect = entry.getValue();
//                    if (activeEffect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
//                        effectsToRemove.add(activeEffect.getEffect());
//                    }
//                }
//                for (var effectToRemove : effectsToRemove) {
//                    entity.removeEffect(effectToRemove);
//                }
//            }
//        });
//    }
//}