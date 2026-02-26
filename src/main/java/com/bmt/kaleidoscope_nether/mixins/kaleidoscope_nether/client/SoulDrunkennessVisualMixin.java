package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.bmt.kaleidoscope_nether.registry.KNEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class SoulDrunkennessVisualMixin {

    @Inject(method = "isViewBlocking(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z",
            at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_nether$preventViewBlocking(BlockGetter level, BlockPos pos,
                                                         CallbackInfoReturnable<Boolean> cir) {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(KNEffects.SOUL_DRUNKENNESS.get())) {
            cir.setReturnValue(false);
        }
    }

}
