package com.bmt.kaleidoscope_nether.mixinHooks;

import com.bmt.kaleidoscope_nether.registry.ModItems;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class SeagullAIStealFromPlayersMixinHooks {

    public static void beforeItemShrink(EntitySeagull seagull, SeagullAIStealFromPlayers aiStealFromPlayers, CallbackInfo ci, ItemStack foodStack, ItemStack copy) {
        if (foodStack.is(ModItems.EVERLASTING_FLAME_STEAK.get())) {
            seagull.setSecondsOnFire(100);
            seagull.stealCooldown = 1500 + seagull.getRandom().nextInt(1500);
            aiStealFromPlayers.stop();
            ci.cancel();
        }
    }

}
