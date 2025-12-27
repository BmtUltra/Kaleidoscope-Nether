package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeNether.MOD_ID);
    public static final RegistryObject<CreativeModeTab> KALEIDOSCOPE_NETHER_TAB = CREATIVE_MODE_TABS.register("kaleidoscope_nether_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kaleidoscope_nether"))
                    .icon(() -> new ItemStack(KNItems.CRIMSON_FRUIT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(KNItems.RAW_GHAST_MEAT.get());
                        output.accept(KNItems.COOKED_GHAST_MEAT.get());
                        output.accept(KNItems.GHAST_TENTACLE.get());
                        output.accept(KNItems.ROASTED_GHAST_TENTACLE.get());
                        output.accept(KNItems.GHAST_HIDE.get());
                        output.accept(KNItems.HAM_SLICE.get());
                        output.accept(KNItems.HAM.get());
                        output.accept(KNItems.RAW_STRIDER_MEAT.get());
                        output.accept(KNItems.COOKED_STRIDER_MEAT.get());
                        output.accept(KNItems.HOGLIN_TUSK.get());
                        output.accept(KNItems.STRIDER_ROCK_SHELL.get());
                        output.accept(KNItems.GILDED_FRAGMENT.get());
                        output.accept(KNItems.WITHER_RIB.get());
                        output.accept(KNItems.STAR_DUST.get());
                        output.accept(KNItems.GOLDEN_PLATE.get());
                        output.accept(KNItems.BLAZE_HEART.get());
                        output.accept(KNItems.MAGMA_BUBBLE.get());
                        output.accept(KNItems.CRIMSON_FRUIT.get());
                        output.accept(KNItems.WARPED_FRUIT.get());
                        output.accept(KNItems.SOUL_PEPPER.get());
                        output.accept(KNItems.POISONOUS_FRUIT.get());
                        output.accept(KNItems.NETHER_CATERPILLAR.get());
                        output.accept(KNItems.STAR_STEW.get());
                        output.accept(KNItems.SOUL_SOUP.get());
                        output.accept(KNItems.BLAZE_SOUP.get());
                        output.accept(KNItems.MAGMA_CREAM_SOUP.get());
                        output.accept(KNItems.WITHER_BONE_SOUP.get());
                        output.accept(KNItems.POISONOUS_SOUP.get());
                        output.accept(KNItems.GLOWING_SOUP.get());
                        output.accept(KNItems.SAUERKRAUT_FISH.get());
                        output.accept(KNItems.NETHER_REED_STEW.get());
                        output.accept(KNItems.SOUL_RETURN_RICE.get());
                        output.accept(KNItems.STRIDER_NETHER_WART_STEW.get());
                        output.accept(KNItems.WARPED_SALAD.get());
                        output.accept(KNItems.CRIMSON_SALAD.get());
                        output.accept(KNItems.BRAISED_STRIDER.get());
                        output.accept(KNItems.GHAST_PASTA.get());
                        output.accept(KNItems.STAR_GHAST_PASTA.get());
                        output.accept(KNItems.SOUL_GLAZED_ROAST.get());
                        output.accept(KNItems.MAGMA_CREAM_PUDDING.get());
                        output.accept(KNItems.GHAST_PUDDING.get());
                        output.accept(KNItems.GILDED_BARBARIC_ROAST.get());
                        output.accept(KNItems.CRIMSON_MAGMA_STEW.get());
                        output.accept(KNItems.STAR_STEW_MEAT.get());
                        output.accept(KNItems.GOLDEN_ROAST.get());
                        output.accept(KNItems.GLOWING_PUDDING.get());
                        output.accept(KNItems.LAVA_JELLY.get());
                        output.accept(KNItems.NETHER_CATERPILLAR_SASHIMI.get());
                        output.accept(KNItems.NETHER_FRIES_PLATTER.get());
                        output.accept(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get());
                        output.accept(KNItems.POISONOUS_GHAST_ROAST.get());
                        output.accept(KNItems.SOUL_PEPPER_STIR_FRY.get());
                        output.accept(KNItems.STRIDER_SHELL_STIR_FRY.get());
                        output.accept(KNItems.FRUIT_PLATTER.get());
                        output.accept(KNItems.HAM_YOGURT.get());
                        output.accept(KNItems.SOUL_LAMB_CHOP.get());
                        output.accept(KNItems.GLOWING_SALAD.get());
                        output.accept(KNItems.BLACK_APPLE_SALAD.get());
                        output.accept(KNItems.RUBY_STEAK.get());
                        output.accept(KNItems.GHAST_KABOB.get());
                        output.accept(KNItems.SOUL_STRIDER_KABOB.get());
                        output.accept(KNItems.GOLDEN_KABOB.get());
                        output.accept(KNItems.BLAZING_KABOB.get());
                        output.accept(KNItems.CRIMSON_KABOB.get());
                        output.accept(KNItems.WARPED_KABOB.get());
                        output.accept(KNItems.GLOWING_KABOB.get());
                        output.accept(KNItems.LAVA_ROASTED_CHICKEN.get());
                        output.accept(KNItems.ROUJIAMO.get());
                        output.accept(KNItems.ROASTED_HAM.get());
                        output.accept(KNItems.SEED_BAG.get());
                    })
                    .build());
}