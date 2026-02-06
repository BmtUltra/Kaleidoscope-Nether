package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.KaleidoscopeNether;
import com.chadate.kaleidoscope_nether.config.Config;
import com.chadate.kaleidoscope_nether.effect.CrimsonBuffEffect;
import com.chadate.kaleidoscope_nether.effect.WarpedBuffEffect;
import com.chadate.kaleidoscope_nether.registry.KNEffects;
import com.chadate.kaleidoscope_nether.registry.KNItems;
import com.chadate.kaleidoscope_nether.registry.KNPotions;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Containers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID)
public class ModEvents {
    
    /**
     * 安全地获取箭矢的药水内容
     * @param arrow 箭矢实体
     * @return 药水内容，如果没有则返回空内容
     */
    private static PotionContents getArrowPotionContents(Arrow arrow) {
        ItemStack pickupStack = arrow.getPickupItemStackOrigin();
        return pickupStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }
    
    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (event.getNewAboutToBeSetTarget() instanceof Player player) {
            if (player.hasEffect(KNEffects.WARPED_BUFF)) {
                if (WarpedBuffEffect.isAffectedMob(event.getEntity())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker) {
            if (attacker.hasEffect(KNEffects.CRIMSON_BUFF)) {
                float multiplier = CrimsonBuffEffect.getDamageMultiplier(attacker, event.getEntity());
                if (multiplier != 1.0f) {
                    float originalDamage = event.getAmount();
                    float newDamage = originalDamage * multiplier;
                    event.setAmount(newDamage);
                }
            }
        }

        if (event.getSource().getDirectEntity() instanceof Arrow arrow) {
            PotionContents potionContents = getArrowPotionContents(arrow);
            
            // 只有神秘毒药箭才会触发效果
            if (potionContents.potion().isPresent() && 
                potionContents.potion().get().value() == KNPotions.MYSTERIOUS_POISON.get()) {
                
                MobEffectInstance instance = event.getEntity().getEffect(KNEffects.MYSTERIOUS_POISON);
                
                if (instance != null) {
                    // 如果已有毒药效果，叠加等级但缩短时间
                    event.getEntity().addEffect(new MobEffectInstance(
                        KNEffects.MYSTERIOUS_POISON,
                        1500 / 8,
                        instance.getAmplifier() + 1
                    ));
                } else {
                    // 如果目标没有毒药效果，给一个基础效果
                    event.getEntity().addEffect(new MobEffectInstance(
                        KNEffects.MYSTERIOUS_POISON,
                        1500,
                        0
                    ));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onNetherWartHarvest(BlockEvent.BreakEvent event) {
        if (!Config.NETHER_CATERPILLAR_ENABLED.get()) {
            return;
        }

        BlockState state = event.getState();
        if (state.is(Blocks.NETHER_WART) && state.getValue(NetherWartBlock.AGE) == 3) {
            if (event.getLevel().getRandom().nextDouble() < Config.NETHER_CATERPILLAR_DROP_CHANCE.get()) {
                ItemStack caterpillar = new ItemStack(KNItems.NETHER_CATERPILLAR.get());
                Level level = (Level) event.getLevel();
                Containers.dropItemStack(level,
                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), caterpillar);
            }
        }

//        if (state.is(Blocks.WEEPING_VINES) || state.is(Blocks.WEEPING_VINES_PLANT)) {
//            if (event.getLevel().getRandom().nextDouble() < Config.CRIMSON_FRUIT_DROP_CHANCE.get()) {
//                ItemStack crimsonFruit = new ItemStack(KNItems.CRIMSON_FRUIT.get());
//                Level level = (Level) event.getLevel();
//                Containers.dropItemStack(level,
//                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), crimsonFruit);
//            }
//        }
//
//        if (state.is(Blocks.TWISTING_VINES) || state.is(Blocks.TWISTING_VINES_PLANT)) {
//            if (event.getLevel().getRandom().nextDouble() < Config.WARPED_FRUIT_DROP_CHANCE.get()) {
//                ItemStack warpedFruit = new ItemStack(KNItems.WARPED_FRUIT.get());
//                Level level = (Level) event.getLevel();
//                Containers.dropItemStack(level,
//                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), warpedFruit);
//            }
//        }
    }
}