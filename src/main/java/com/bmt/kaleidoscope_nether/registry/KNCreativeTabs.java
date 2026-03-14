package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KNCreativeTabs {
    public static final CreativeModeTab KALEIDOSCOPE_NETHER_TAB = FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.kaleidoscope_nether"))
                    .icon(() -> new ItemStack(KNItems.BLOWGUN))
                    .displayItems((parameters, output) -> {
                        output.accept(KNItems.PRIMITIVE_MACHETE);
                        output.accept(KNItems.GHAST_TENTACLE);
                        output.accept(KNItems.ROASTED_GHAST_TENTACLE);
                        output.accept(KNItems.HAM_SLICE);
                        output.accept(KNItems.HAM);
                        output.accept(KNItems.HOGLIN_HIDE);
                        output.accept(KNItems.NETHER_CATERPILLAR);
                        output.accept(KNItems.RAW_PIGLIN_MEAT);
                        output.accept(KNItems.COOKED_PIGLIN_MEAT);
                        output.accept(KNItems.RAW_STRIDER_MEAT);
                        output.accept(KNItems.COOKED_STRIDER_MEAT);
                        output.accept(KNItems.HOGLIN_TUSK);
                        output.accept(KNItems.STRIDER_ROCK_SHELL);
                        output.accept(KNItems.GILDED_FRAGMENT);
                        output.accept(KNItems.WITHER_RIB);
                        output.accept(KNItems.STAR_DUST);
                        output.accept(KNItems.BLAZE_HEART);
                        output.accept(KNItems.CRIMSON_FRUIT);
                        output.accept(KNItems.WARPED_FRUIT);
                        output.accept(KNItems.SOUL_PEPPER);
                        output.accept(KNItems.POISONOUS_FRUIT);
                        output.accept(KNItems.LAVA_ROASTED_CHICKEN);
                        output.accept(KNItems.ROUJIAMO);
                        output.accept(KNItems.SPICY_POT);
                        output.accept(KNItems.SPICY_POT_RICE);
                        output.accept(KNItems.MAPO_TOFU);
                        output.accept(KNItems.MAPO_TOFU_RICE);
                        output.accept(KNItems.MAGMA_CREAM_STIR_FRY);
                        output.accept(KNItems.MAGMA_CREAM_STIR_FRY_RICE);
                        output.accept(KNItems.SOUL_STIR_FRY_MEAT);
                        output.accept(KNItems.SOUL_STIR_FRY_MEAT_RICE);
                        output.accept(KNItems.CARAMEL_NETHER_CATERPILLAR);
                        output.accept(KNItems.CARAMEL_NETHER_CATERPILLAR_RICE);
                        output.accept(KNItems.STAR_STEW);
                        output.accept(KNItems.SOUL_SOUP);
                        output.accept(KNItems.BLAZE_SOUP);
                        output.accept(KNItems.MAGMA_CREAM_SOUP);
                        output.accept(KNItems.WITHER_BONE_SOUP);
                        output.accept(KNItems.POISONOUS_SOUP);
                        output.accept(KNItems.GLOWING_SOUP);
                        output.accept(KNItems.CHONGQING_NOODLES);
                        output.accept(KNItems.LUOSIFEN);
                        output.accept(KNItems.SPICY_HOGLIN_RAMEN);
                        output.accept(KNItems.SAUERKRAUT_FISH);
                        output.accept(KNItems.NETHER_REED_STEW);
                        output.accept(KNItems.SOUL_RETURN_RICE);
                        output.accept(KNItems.STRIDER_NETHER_WART_STEW);
                        output.accept(KNItems.WARPED_SALAD);
                        output.accept(KNItems.CRIMSON_SALAD);
                        output.accept(KNItems.BRAISED_STRIDER);
                        output.accept(KNItems.WARPED_HOGLIN_TENDERLOIN_STEW);
                        output.accept(KNItems.GHAST_PASTA);
                        output.accept(KNItems.STAR_GHAST_PASTA);
                        output.accept(KNItems.SOUL_GLAZED_ROAST);
                        output.accept(KNItems.MAGMA_CREAM_PUDDING);
                        output.accept(KNItems.GHAST_PUDDING);
                        output.accept(KNItems.GILDED_BARBARIC_ROAST);
                        output.accept(KNItems.CRIMSON_MAGMA_STEW);
                        output.accept(KNItems.STAR_STEW_MEAT);
                        output.accept(KNItems.WARPED_CAKE);
                        output.accept(KNItems.GOLDEN_ROAST);
                        output.accept(KNItems.GLOWING_PUDDING);
                        output.accept(KNItems.LAVA_JELLY);
                        output.accept(KNItems.NETHER_CATERPILLAR_SASHIMI);
                        output.accept(KNItems.NETHER_FRIES_PLATTER);
                        output.accept(KNItems.HOGLIN_TUSK_BRAISED_MEAT);
                        output.accept(KNItems.POISONOUS_GHAST_ROAST);
                        output.accept(KNItems.SOUL_PEPPER_STIR_FRY);
                        output.accept(KNItems.STRIDER_SHELL_STIR_FRY);
                        output.accept(KNItems.FRUIT_PLATTER);
                        output.accept(KNItems.HAM_YOGURT);
                        output.accept(KNItems.GLOWING_SALAD);
                        output.accept(KNItems.BLACK_APPLE_SALAD);
                        output.accept(KNItems.RUBY_STEAK);
                        output.accept(KNItems.GHAST_KABOB);
                        output.accept(KNItems.SOUL_STRIDER_KABOB);
                        output.accept(KNItems.GOLDEN_KABOB);
                        output.accept(KNItems.BLAZING_KABOB);
                        output.accept(KNItems.CRIMSON_KABOB);
                        output.accept(KNItems.WARPED_KABOB);
                        output.accept(KNItems.GLOWING_KABOB);
                        output.accept(KNItems.FORGETFULNESS_SOUP);
                        output.accept(KNItems.BRAISED_LION_HEAD);
                        output.accept(KNItems.BRAISED_PORK_RICE);
                        output.accept(KNItems.GARLIC_OYSTERS);
                        output.accept(KNItems.COUPLES_LUNG_SLICE);
                        output.accept(KNItems.PEPPER_PORK_BELLY_CHICKEN_SOUP);
                        output.accept(KNItems.CORN_CARROT_PORK_RIB_SOUP);
                        output.accept(KNItems.NETHER_STOVE);
                    })
                    .build();

    private static CreativeModeTab register(String name, CreativeModeTab.Builder builder) {
        CreativeModeTab tab = builder.build();
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name),
                tab);
    }

    public static void registerCreativeTabs() {
    }
}