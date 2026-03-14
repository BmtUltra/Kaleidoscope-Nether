package com.bmt.kaleidoscope_nether.client;

import com.bmt.kaleidoscope_nether.client.renderer.entity.BlazeHeartProjectileRenderer;
import com.bmt.kaleidoscope_nether.registry.KNEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientEvents {

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(KNEntities.BLAZE_HEART_PROJECTILE,
                BlazeHeartProjectileRenderer::new);
    }
}