package com.bmt.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.config.KNConfig;
import com.bmt.kaleidoscope_nether.event.*;
import com.bmt.kaleidoscope_nether.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class KaleidoscopeNether implements ModInitializer {
	public static final String MOD_ID = "kaleidoscope_nether";

	@Override
	public void onInitialize() {
		KNEffects.registerEffects();
		KNConfig.register();
		KNPotions.registerPotions();
		KNEntities.registerEntities();
		KNBlocks.registerBlocks();
		KNItems.registerItems();
		KNCreativeTabs.registerCreativeTabs();
		KNSounds.registerSounds();
		KNFoods.registerFoods();

		ModEvents.registerEvents();
		DamageEventHandler.registerEvents();
		ItemTabEventHandler.registerEvents();
		KNEventSubscriber.registerEvents();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}