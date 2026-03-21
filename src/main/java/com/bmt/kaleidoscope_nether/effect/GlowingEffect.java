//package com.bmt.kaleidoscope_nether.effect;
//
//import com.bmt.kaleidoscope_nether.init.KNEffects;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.ExperienceOrb;
//import net.minecraftforge.event.entity.player.PlayerXpEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.Objects;
//
//@Mod.EventBusSubscriber(modid = "kaleidoscope_nether")
//public class GlowingEffect extends MobEffect {
//
//    public GlowingEffect(int color) {
//        super(MobEffectCategory.BENEFICIAL, color);
//    }
//
//    @Override
//    public boolean isDurationEffectTick(int duration, int amplifier) {
//        return false;
//    }
//
//    @SubscribeEvent
//    public static void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
//        Player player = event.getEntity();
//        ExperienceOrb orb = event.getOrb();
//
//        if (player.hasEffect(KNEffects.GLOWING.get())) {
//            int amplifier = Objects.requireNonNull(player.getEffect(KNEffects.GLOWING.get())).getAmplifier();
//
//            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
//            int originalValue = orb.getValue();
//            int bonusValue = (int) (originalValue * (bonusMultiplier - 1.0f));
//
//            if (bonusValue > 0) {
//                player.giveExperiencePoints(bonusValue);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPlayerXpChange(PlayerXpEvent.XpChange event) {
//        Player player = event.getEntity();
//
//        if (player.hasEffect(KNEffects.GLOWING.get())) {
//            int amplifier = Objects.requireNonNull(player.getEffect(KNEffects.GLOWING.get())).getAmplifier();
//
//            float bonusMultiplier = 1.0f + (amplifier + 1) * 0.5f;
//            int originalAmount = event.getAmount();
//            int bonusAmount = (int) (originalAmount * (bonusMultiplier - 1.0f));
//
//            if (bonusAmount > 0) {
//                event.setAmount(originalAmount + bonusAmount);
//            }
//        }
//    }
//}