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
                    .icon(() -> new ItemStack(ModItems.CRIMSON_FRUIT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RAW_GHAST_MEAT.get());
                        output.accept(ModItems.COOKED_GHAST_MEAT.get());
                        output.accept(ModItems.GHAST_TENTACLE.get());
                        output.accept(ModItems.ROASTED_GHAST_TENTACLE.get());
                        output.accept(ModItems.GHAST_HIDE.get());
                        output.accept(ModItems.HAM_SLICE.get());
                        output.accept(ModItems.HAM.get());
                        output.accept(ModItems.RAW_STRIDER_MEAT.get());
                        output.accept(ModItems.COOKED_STRIDER_MEAT.get());
                        output.accept(ModItems.HOGLIN_TUSK.get());
                        output.accept(ModItems.STRIDER_ROCK_SHELL.get());
                        output.accept(ModItems.GILDED_FRAGMENT.get());
                        output.accept(ModItems.WITHER_RIB.get());
                        output.accept(ModItems.STAR_DUST.get());
                        output.accept(ModItems.GOLDEN_PLATE.get());
                        output.accept(ModItems.BLAZE_HEART.get());
                        output.accept(ModItems.MAGMA_BUBBLE.get());
                        output.accept(ModItems.CRIMSON_FRUIT.get());
                        output.accept(ModItems.WARPED_FRUIT.get());
                        output.accept(ModItems.SOUL_PEPPER.get());
                        output.accept(ModItems.POISONOUS_FRUIT.get());
                        output.accept(ModItems.NETHER_CATERPILLAR.get());
                        output.accept(ModItems.STAR_STEW.get());
                        output.accept(ModItems.SOUL_SOUP.get());
                        output.accept(ModItems.BLAZE_SOUP.get());
                        output.accept(ModItems.MAGMA_CREAM_SOUP.get());
                        output.accept(ModItems.WITHER_BONE_SOUP.get());
                        output.accept(ModItems.POISONOUS_SOUP.get());
                        output.accept(ModItems.GLOWING_SOUP.get());
                        output.accept(ModItems.SAUERKRAUT_FISH.get());
                        output.accept(ModItems.NETHER_REED_STEW.get());
                        output.accept(ModItems.SOUL_RETURN_RICE.get());
                        output.accept(ModItems.STRIDER_NETHER_WART_STEW.get());
                        output.accept(ModItems.WARPED_SALAD.get());
                        output.accept(ModItems.CRIMSON_SALAD.get());
                        output.accept(ModItems.BRAISED_STRIDER.get());
                        output.accept(ModItems.GHAST_PASTA.get());
                        output.accept(ModItems.STAR_GHAST_PASTA.get());
                        output.accept(ModItems.SOUL_GLAZED_ROAST.get());
                        output.accept(ModItems.MAGMA_CREAM_PUDDING.get());
                        output.accept(ModItems.GHAST_PUDDING.get());
                        output.accept(ModItems.GILDED_BARBARIC_ROAST.get());
                        output.accept(ModItems.CRIMSON_MAGMA_STEW.get());
                        output.accept(ModItems.STAR_STEW_MEAT.get());
                        output.accept(ModItems.GOLDEN_ROAST.get());
                        output.accept(ModItems.GLOWING_PUDDING.get());
                        output.accept(ModItems.LAVA_JELLY.get());
                        output.accept(ModItems.NETHER_CATERPILLAR_SASHIMI.get());
                        output.accept(ModItems.NETHER_FRIES_PLATTER.get());
                        output.accept(ModItems.HOGLIN_TUSK_BRAISED_MEAT.get());
                        output.accept(ModItems.POISONOUS_GHAST_ROAST.get());
                        output.accept(ModItems.SOUL_PEPPER_STIR_FRY.get());
                        output.accept(ModItems.STRIDER_SHELL_STIR_FRY.get());
                        output.accept(ModItems.FRUIT_PLATTER.get());
                        output.accept(ModItems.HAM_YOGURT.get());
                        output.accept(ModItems.SOUL_LAMB_CHOP.get());
                        output.accept(ModItems.GLOWING_SALAD.get());
                        output.accept(ModItems.BLACK_APPLE_SALAD.get());
                        output.accept(ModItems.RUBY_STEAK.get());
                        output.accept(ModItems.GHAST_KABOB.get());
                        output.accept(ModItems.SOUL_STRIDER_KABOB.get());
                        output.accept(ModItems.GOLDEN_KABOB.get());
                        output.accept(ModItems.BLAZING_KABOB.get());
                        output.accept(ModItems.CRIMSON_KABOB.get());
                        output.accept(ModItems.WARPED_KABOB.get());
                        output.accept(ModItems.GLOWING_KABOB.get());
                        output.accept(ModItems.LAVA_ROASTED_CHICKEN.get());
                        output.accept(ModItems.ROUJIAMO.get());
                        output.accept(ModItems.ROASTED_HAM.get());
                        output.accept(ModItems.SEED_BAG.get());
                    })
                    .build());
}