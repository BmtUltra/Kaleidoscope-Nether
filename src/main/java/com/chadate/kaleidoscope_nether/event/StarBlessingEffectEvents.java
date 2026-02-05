package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.chadate.kaleidoscope_nether.effect.StarBlessingBuffEffect;
import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class StarBlessingEffectEvents {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(KNEffects.STAR_BLESSING_BUFF)) {
                var effect = player.getEffect(KNEffects.STAR_BLESSING_BUFF);
                if (effect != null) {
                    int amplifier = effect.getAmplifier();
                    float reducedDamage = StarBlessingBuffEffect.getDamageReduction(event.getAmount(), amplifier);
                    event.setAmount(reducedDamage);
                }
            }
        }
    }
}