package com.chadate.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class SoulDrunkennessAirSupplyMixin {

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_nether$preventWallSuffocation(CallbackInfoReturnable<Boolean> cir) {
        if (this.hasEffect(KNEffects.SOUL_DRUNKENNESS)) {
            cir.setReturnValue(false);
        }
    }
}
