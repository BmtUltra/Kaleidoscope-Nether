package com.bmt.kaleidoscope_nether.mixinHooks.alexsmobs;

import com.bmt.kaleidoscope_nether.advancement.KNAdvancementTriggerRegistry;
import com.bmt.kaleidoscope_nether.mixins.alexsmobs.SeagullAIStealFromPlayersAccessor;
import com.bmt.kaleidoscope_nether.init.KNItems;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class SeagullAIStealFromPlayersMixinHooks {

    public static void beforeItemShrink(EntitySeagull seagull, SeagullAIStealFromPlayers aiStealFromPlayers, CallbackInfo ci, ItemStack foodStack, ItemStack copy) {
        if (foodStack.is(KNItems.EVERLASTING_FLAME_STEAK.get())) {
            seagull.setSecondsOnFire(100);
            seagull.stealCooldown = 1500 + seagull.getRandom().nextInt(1500);

            Player player = ((SeagullAIStealFromPlayersAccessor) aiStealFromPlayers).KN$getTarget();
            if (player instanceof ServerPlayer serverPlayer) {
                KNAdvancementTriggerRegistry.SIMPLE_ID.trigger(serverPlayer, "seagull_steal_failure");
            }
            aiStealFromPlayers.stop();
            ci.cancel();
        }
    }

}
