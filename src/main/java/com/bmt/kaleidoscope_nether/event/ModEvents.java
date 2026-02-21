package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether.ArrowAccessor;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import com.bmt.kaleidoscope_nether.registry.KNPotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ArrowAccessor arrowAccessor) {
            if (arrowAccessor.na$getPotion() == KNPotions.MYSTERIOUS_POISON.get()) {
                MobEffectInstance instance = event.getEntity().getEffect(KNEffects.MYSTERIOUS_POISON.get());
                if (instance != null) {
                    event.getEntity().addEffect(new MobEffectInstance(KNEffects.MYSTERIOUS_POISON.get(),
                            1500 / 8,
                            instance.getAmplifier() + 1));
                }
            }
        }
    }
}