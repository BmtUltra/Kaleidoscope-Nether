package com.bmt.kaleidoscope_nether.entity;

import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.bmt.kaleidoscope_nether.registry.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class BlazeHeartProjectile extends ThrowableItemProjectile {
    
    public BlazeHeartProjectile(EntityType<? extends BlazeHeartProjectile> entityType, Level level) {
        super(entityType, level);
    }
    
    public BlazeHeartProjectile(Level level, LivingEntity shooter) {
        super(ModEntities.BLAZE_HEART_PROJECTILE.get(), shooter, level);
    }
    
    public BlazeHeartProjectile(Level level, double x, double y, double z) {
        super(ModEntities.BLAZE_HEART_PROJECTILE.get(), x, y, z, level);
    }
    
    @Override
    protected Item getDefaultItem() {
        return KNItems.BLAZE_HEART.get();
    }
    
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        
        if (!this.level().isClientSide) {
            this.level().explode(
                this, 
                this.getX(), 
                this.getY(), 
                this.getZ(), 
                3.0F,
                false,
                Level.ExplosionInteraction.NONE
            );
            
            this.discard();
        }
    }
    
    @Override
    protected float getGravity() {
        return 0.03F;
    }
}