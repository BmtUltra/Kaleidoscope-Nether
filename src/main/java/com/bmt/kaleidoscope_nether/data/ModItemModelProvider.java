package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeNether.MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        ResourceLocation bagItem = ForgeRegistries.ITEMS.getKey(KNItems.SEED_BAG.get());
        if (bagItem != null) {
            ItemModelBuilder emptyBag = basicItem(KaleidoscopeNether.id("seed_bag"));
            ItemModelBuilder hasItemsBag = basicItem(KaleidoscopeNether.id("seed_bag_has_items"));

            getBuilder(bagItem.toString())
                    .override().model(emptyBag).predicate(KaleidoscopeNether.id("has_items"), 0.0F).end()
                    .override().model(hasItemsBag).predicate(KaleidoscopeNether.id("has_items"), 1.0F).end();
        }
    }
}