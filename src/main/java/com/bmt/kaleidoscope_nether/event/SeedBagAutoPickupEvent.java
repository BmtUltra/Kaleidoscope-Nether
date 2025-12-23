package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.item.SeedBagItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SeedBagAutoPickupEvent {

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Player player = event.getEntity();
        ItemEntity itemEntity = event.getItem();

        if (SeedBagItem.tryAutoPickup(player, itemEntity.getItem())) {
            event.setCanceled(true);
            itemEntity.discard();
        }
    }
}