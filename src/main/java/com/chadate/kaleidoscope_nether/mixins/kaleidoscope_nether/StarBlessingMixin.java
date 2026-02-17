package com.chadate.kaleidoscope_nether.mixins.kaleidoscope_nether;

import com.chadate.kaleidoscope_nether.registry.KNEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class StarBlessingMixin extends Entity {
    public StarBlessingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * 免疫debuff：阻止负面效果的应用
     */
    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", 
            at = @At("HEAD"), cancellable = true)
    private void onAddEffect(MobEffectInstance effectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        
        if (livingEntity.hasEffect(KNEffects.STAR_BLESSING)) {
            MobEffect effect = effectInstance.getEffect().value();
            
            // 如果是负面效果，阻止其应用
            if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    /**
     * 免疫击退：取消击退效果
     */
    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    private void onKnockback(double strength, double x, double z, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        
        if (livingEntity.hasEffect(KNEffects.STAR_BLESSING)) {
            ci.cancel(); // 取消击退
        }
    }

    /**
     * 免疫击退：在受伤时也取消击退
     */
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V"), 
            cancellable = true)
    private void onHurtKnockback(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        
        if (livingEntity.hasEffect(KNEffects.STAR_BLESSING)) {
            // 不取消伤害，只取消击退
            // 这里我们让原方法继续执行，但会在上面的knockback方法中取消击退
        }
    }
}