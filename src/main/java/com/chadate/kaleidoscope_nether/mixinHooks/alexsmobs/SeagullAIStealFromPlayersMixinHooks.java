package com.chadate.kaleidoscope_nether.mixinHooks.alexsmobs;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.chadate.kaleidoscope_nether.advancement.KNAdvancementTriggerRegistry;
import com.chadate.kaleidoscope_nether.mixins.alexsmobs.SeagullAIStealFromPlayersAccessor;
import com.chadate.kaleidoscope_nether.registry.KNItems;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SeagullAIStealFromPlayersMixinHooks {

    public static void beforeItemShrink(EntitySeagull seagull, SeagullAIStealFromPlayers aiStealFromPlayers, CallbackInfo ci, ItemStack foodStack, ItemStack copy) {
        if (foodStack.is(KNItems.EVERLASTING_FLAME_STEAK.get())) {
            seagull.igniteForSeconds(100.0F);
            seagull.stealCooldown = 1500 + seagull.getRandom().nextInt(1500);

            Player player = ((SeagullAIStealFromPlayersAccessor) aiStealFromPlayers).KN$getTarget();
            if (player instanceof ServerPlayer serverPlayer) {
                KNAdvancementTriggerRegistry.SIMPLE_ID.get().trigger(serverPlayer, "seagull_steal_failure");
            }
            aiStealFromPlayers.stop();
            ci.cancel();
        }
    }

}
