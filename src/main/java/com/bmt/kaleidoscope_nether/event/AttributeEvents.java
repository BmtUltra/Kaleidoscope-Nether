package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeEvents {

    private static final UUID DEFAULT_STAR_BLESSING_UUID = UUID.fromString("3c4d5e6f-7a8b-9c0d-1e2f-a3b4c5d6e7f8");
    private static final UUID DEFAULT_HEALTH_MODIFIER_UUID = UUID.fromString("5e6f7a8b-9c0d-1e2f-3a4b-c5d6e7f8a9b0");

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player && !event.getLevel().isClientSide()) {
            AttributeInstance starBlessingAttr = player.getAttribute(ModAttributes.STAR_BLESSING.get());

            if (starBlessingAttr != null) {
                boolean hasDefaultValue = starBlessingAttr.getModifier(DEFAULT_STAR_BLESSING_UUID) != null;

                if (!hasDefaultValue) {
                    AttributeModifier defaultModifier = new AttributeModifier(
                            DEFAULT_STAR_BLESSING_UUID,
                            "Default Star Blessing",
                            0.0,
                            AttributeModifier.Operation.ADDITION
                    );
                    starBlessingAttr.addPermanentModifier(defaultModifier);

                    updatePlayerMaxHealth(player, 0);
                }
            }
        }
    }

    private static void updatePlayerMaxHealth(Player player, int starBlessingLevel) {
        float healthIncrease = starBlessingLevel * 2.0f;

        player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .removeModifier(DEFAULT_HEALTH_MODIFIER_UUID);

        AttributeModifier healthModifier = new AttributeModifier(
                DEFAULT_HEALTH_MODIFIER_UUID,
                "Star Blessing Health Bonus",
                healthIncrease,
                AttributeModifier.Operation.ADDITION
        );
        player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .addPermanentModifier(healthModifier);

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }
}