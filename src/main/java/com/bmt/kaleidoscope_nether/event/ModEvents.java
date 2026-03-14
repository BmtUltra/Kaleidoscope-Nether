package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.registry.KNEffects;
import com.bmt.kaleidoscope_nether.registry.KNPotions;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;

public class ModEvents {

    private static PotionContents getArrowPotionContents(Arrow arrow) {
        ItemStack pickupStack = arrow.getPickupItemStackOrigin();
        return pickupStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }

    public static void registerEvents() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (source.getDirectEntity() instanceof Arrow arrow) {
                PotionContents potionContents = getArrowPotionContents(arrow);
                if (potionContents.potion().isPresent() &&
                        potionContents.potion().get().value() == KNPotions.MYSTERIOUS_POISON) {

                    Holder<MobEffect> mysteriousPoisonHolder = KNEffects.MYSTERIOUS_POISON;
                    MobEffectInstance instance = entity.getEffect(mysteriousPoisonHolder);

                    if (instance != null) {
                        entity.addEffect(new MobEffectInstance(
                                mysteriousPoisonHolder,
                                1500 / 8,
                                instance.getAmplifier() + 1
                        ));
                    } else {
                        entity.addEffect(new MobEffectInstance(
                                mysteriousPoisonHolder,
                                1500,
                                0
                        ));
                    }
                }
            }
            return true;
        });
    }
}