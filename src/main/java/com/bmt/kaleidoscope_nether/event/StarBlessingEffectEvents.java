package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.effect.StarBlessingBuffEffect;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StarBlessingEffectEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(KNEffects.STAR_BLESSING_BUFF.get())) {
                var effect = player.getEffect(KNEffects.STAR_BLESSING_BUFF.get());
                if (effect != null) {
                    int amplifier = effect.getAmplifier();
                    float reducedDamage = StarBlessingBuffEffect.getDamageReduction(event.getAmount(), amplifier);
                    event.setAmount(reducedDamage);
                }
            }
        }
    }
}