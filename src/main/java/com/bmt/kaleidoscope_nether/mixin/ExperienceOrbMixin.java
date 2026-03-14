package com.bmt.kaleidoscope_nether.mixin;

import com.bmt.kaleidoscope_nether.effect.GlowingEffect;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrb.class)
public class ExperienceOrbMixin {
    @Inject(method = "playerTouch", at = @At("HEAD"))
    private void onPlayerTouch(Player player, CallbackInfo ci) {
        GlowingEffect.onExperienceOrbPickup(player, (ExperienceOrb) (Object) this);
    }
}