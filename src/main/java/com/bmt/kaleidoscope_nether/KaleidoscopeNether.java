package com.bmt.kaleidoscope_nether;

import com.bmt.kaleidoscope_nether.advancement.KNAdvancementTriggerRegistry;
import com.bmt.kaleidoscope_nether.config.Config;
import com.bmt.kaleidoscope_nether.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(KaleidoscopeNether.MOD_ID)
public class KaleidoscopeNether {
    public static final String MOD_ID = "kaleidoscope_nether";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public KaleidoscopeNether(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        KNBlocks.BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        KNItems.ITEMS.register(modEventBus);
        KNEffects.EFFECTS.register(modEventBus);
        KNCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        KNEnchantments.ENCHANTMENTS.register(modEventBus);
        KNSounds.SOUND_EVENTS.register(modEventBus);
        KNEntities.ENTITIES.register(modEventBus);
        KNPotions.POISONS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        KNFoodBiteRegistry.init();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MOD_ID, name);
    }

    public static ResourceLocation fromNamespaceAndPath(String path, String name) {
        return ResourceLocation.tryBuild(path, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(KNComposterRegistry::register);
        KNAdvancementTriggerRegistry.init();
    }
}