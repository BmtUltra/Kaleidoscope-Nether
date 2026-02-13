package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.config.Config;
import com.bmt.kaleidoscope_nether.effect.CrimsonBuffEffect;
import com.bmt.kaleidoscope_nether.effect.WarpedBuffEffect;
import com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether.ArrowAccessor;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.bmt.kaleidoscope_nether.registry.KNPotions;
import net.minecraft.world.Containers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (event.getNewTarget() instanceof Player player) {
            if (player.hasEffect(KNEffects.WARPED_BUFF.get())) {
                if (WarpedBuffEffect.isAffectedMob(event.getEntity())) {
                    event.setCanceled(true);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker) {
            if (attacker.hasEffect(KNEffects.CRIMSON_BUFF.get())) {
                float multiplier = CrimsonBuffEffect.getDamageMultiplier(attacker, event.getEntity());
                if (multiplier != 1.0f) {
                    float originalDamage = event.getAmount();
                    float newDamage = originalDamage * multiplier;
                    event.setAmount(newDamage);
                }
            }
        }

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