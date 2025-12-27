package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEatMixin {

    @Inject(
            at = @At("HEAD"),
            method = "canEat",
            cancellable = true
    )
    private void onCanEat(boolean canAlwaysEat, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player)(Object)this;

        MobEffectInstance effect = player.getEffect(ModEffects.GLOWING_BUFF.get());
        if (effect != null && effect.getDuration() > 0) {
            cir.setReturnValue(true);
        }
    }
}