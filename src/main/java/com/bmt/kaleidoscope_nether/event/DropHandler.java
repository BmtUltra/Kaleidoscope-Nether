package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModItems;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DropHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Ghast) {
            // 恶魂肉
            if (event.getEntity().getRandom().nextFloat() < 0.3f) {
                ItemStack rawGhastMeat = new ItemStack(ModItems.RAW_GHAST_MEAT.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        rawGhastMeat
                ));
            }

            // 恶魂皮
            if (event.getEntity().getRandom().nextFloat() < 0.1f) {
                ItemStack ghastHide = new ItemStack(ModItems.GHAST_HIDE.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        ghastHide
                ));
            }

            // 恶魂触手
            if (event.getEntity().getRandom().nextFloat() < 0.2f) {
                ItemStack ghastTentacle = new ItemStack(ModItems.GHAST_TENTACLE.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        ghastTentacle
                ));
            }
        }

        if (event.getEntity() instanceof Hoglin hoglin && !hoglin.isBaby()) {
            // 疣猪兽獠牙
            if (event.getEntity().getRandom().nextFloat() < 0.15f) {
                ItemStack hoglinTusk = new ItemStack(ModItems.HOGLIN_TUSK.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        hoglinTusk
                ));
            }

            // 火腿
            if (event.getEntity().getRandom().nextFloat() < 0.25f) {
                ItemStack ham = new ItemStack(ModItems.HAM.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        ham
                ));
            }
        }

        if (event.getEntity() instanceof WitherSkeleton) {
            // 凋零肋骨
            if (event.getEntity().getRandom().nextFloat() < 0.2f) {
                ItemStack witherRib = new ItemStack(ModItems.WITHER_RIB.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        witherRib
                ));
            }
        }

        if (event.getEntity() instanceof Strider strider && !strider.isBaby()) {
            // 炽足兽肉
            if (event.getEntity().getRandom().nextFloat() < 0.4f) {
                int count = 1 + event.getEntity().getRandom().nextInt(2);
                ItemStack rawStriderMeat = new ItemStack(ModItems.RAW_STRIDER_MEAT.get(), count);
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        rawStriderMeat
                ));
            }

            // 炽足兽岩壳
            if (event.getEntity().getRandom().nextFloat() < 0.15f) {
                ItemStack striderRockShell = new ItemStack(ModItems.STRIDER_ROCK_SHELL.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        striderRockShell
                ));
            }
        }

        if (event.getEntity() instanceof MagmaCube) {
            // 熔岩泡泡
            if (event.getEntity().getRandom().nextFloat() < 0.25f) {
                ItemStack magmaBubble = new ItemStack(ModItems.MAGMA_BUBBLE.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        magmaBubble
                ));
            }
        }

        if (event.getEntity() instanceof Blaze) {
            // 烈焰之心
            if (event.getEntity().getRandom().nextFloat() < 0.2f) {
                ItemStack blazeHeart = new ItemStack(ModItems.BLAZE_HEART.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        blazeHeart
                ));
            }
        }

        if (event.getEntity() instanceof Piglin) {
            // 碎金块
            if (event.getEntity().getRandom().nextFloat() < 0.15f) {
                ItemStack goldenPlate = new ItemStack(ModItems.GOLDEN_PLATE.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        goldenPlate
                ));
            }
        }

        if (event.getEntity() instanceof PiglinBrute) {
            // 镀金裂片
            if (event.getEntity().getRandom().nextFloat() < 0.1f) {
                ItemStack gildedFragment = new ItemStack(ModItems.GILDED_FRAGMENT.get());
                event.getDrops().add(new net.minecraft.world.entity.item.ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        gildedFragment
                ));
            }
        }
    }
}