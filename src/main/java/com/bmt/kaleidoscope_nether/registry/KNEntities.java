package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.entity.BlazeHeartProjectile;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class KNEntities {
    public static final EntityType<BlazeHeartProjectile> BLAZE_HEART_PROJECTILE = register("blaze_heart_projectile",
            EntityType.Builder.<BlazeHeartProjectile>of(BlazeHeartProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("blaze_heart_projectile"));

    private static <T extends EntityType<?>> T register(String name, T entityType) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name),
                entityType);
    }

    public static void registerEntities() {
    }
}