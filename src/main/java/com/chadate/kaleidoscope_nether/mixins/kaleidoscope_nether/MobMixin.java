package com.chadate.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.chadate.kaleidoscope_nether.effect.WarpedBuffEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Inject(
            method = "getTarget()Lnet/minecraft/world/entity/LivingEntity;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void onGetTarget(CallbackInfoReturnable<LivingEntity> cir) {
        @Nullable LivingEntity target = cir.getReturnValue();

        if (target instanceof Player player) {
            Mob self = (Mob) (Object) this;

            if (WarpedBuffEffect.isAffectedMob(self)) {
                if (WarpedBuffEffect.shouldAffectMob(self, player)) {
                    cir.setReturnValue(null);
                }
            }
        }
    }

    @Inject(
            method = "setTarget(Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onSetTarget(LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player player) {
            Mob self = (Mob) (Object) this;
            if (WarpedBuffEffect.isAffectedMob(self)) {
                if (WarpedBuffEffect.shouldAffectMob(self, player)) {
                    ci.cancel();
                }
            }
        }
    }
}