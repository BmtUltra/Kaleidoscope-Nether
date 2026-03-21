package com.bmt.kaleidoscope_nether.init;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.entity.BlazeHeartProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KNEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KaleidoscopeNether.MOD_ID);
    
    public static final RegistryObject<EntityType<BlazeHeartProjectile>> BLAZE_HEART_PROJECTILE = 
        ENTITIES.register("blaze_heart_projectile", 
            () -> EntityType.Builder.<BlazeHeartProjectile>of(BlazeHeartProjectile::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("blaze_heart_projectile"));
}