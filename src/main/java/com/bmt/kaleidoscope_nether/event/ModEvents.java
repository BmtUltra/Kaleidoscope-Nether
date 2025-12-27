package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.Config;
import com.bmt.kaleidoscope_nether.effect.CrimsonBuffEffect;
import com.bmt.kaleidoscope_nether.effect.WarpedBuffEffect;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import com.bmt.kaleidoscope_nether.registry.ModItems;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.Containers;
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
            if (player.hasEffect(ModEffects.WARPED_BUFF.get())) {
                if (WarpedBuffEffect.isAffectedMob(event.getEntity())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker) {
            if (attacker.hasEffect(ModEffects.CRIMSON_BUFF.get())) {
                float multiplier = CrimsonBuffEffect.getDamageMultiplier(attacker, event.getEntity());
                if (multiplier != 1.0f) {
                    float originalDamage = event.getAmount();
                    float newDamage = originalDamage * multiplier;
                    event.setAmount(newDamage);
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
                ItemStack caterpillar = new ItemStack(ModItems.NETHER_CATERPILLAR.get());
                Level level = (Level) event.getLevel();
                Containers.dropItemStack(level,
                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), caterpillar);
            }
        }

        if (state.is(Blocks.WEEPING_VINES) || state.is(Blocks.WEEPING_VINES_PLANT)) {
            if (event.getLevel().getRandom().nextDouble() < Config.CRIMSON_FRUIT_DROP_CHANCE.get()) {
                ItemStack crimsonFruit = new ItemStack(ModItems.CRIMSON_FRUIT.get());
                Level level = (Level) event.getLevel();
                Containers.dropItemStack(level,
                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), crimsonFruit);
            }
        }

        if (state.is(Blocks.TWISTING_VINES) || state.is(Blocks.TWISTING_VINES_PLANT)) {
            if (event.getLevel().getRandom().nextDouble() < Config.WARPED_FRUIT_DROP_CHANCE.get()) {
                ItemStack warpedFruit = new ItemStack(ModItems.WARPED_FRUIT.get());
                Level level = (Level) event.getLevel();
                Containers.dropItemStack(level,
                        event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), warpedFruit);
            }
        }
    }
}