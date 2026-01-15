package com.bmt.kaleidoscope_nether.mixins.kaleidoscope_nether;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Arrow.class)
public interface ArrowAccessor {
    @Accessor("potion")
    Potion na$getPotion();
}
