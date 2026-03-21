package com.bmt.kaleidoscope_nether.data.subAdvancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class KNAdvances implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper existingFileHelper) {
//        Advancement root = Advancement.Builder.advancement()
//                .display(KNItems.CRIMSON_FRUIT.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.root.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.root.description"),
//                        ResourceLocation.parse("kaleidoscope_nether:textures/advancement/background.png"),
//                        FrameType.TASK, false, false, false)
//                .addCriterion("mod_items", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.CRIMSON_FRUIT.get()))
//                .save(saver, "kaleidoscope_nether/root");
//
//        Advancement getStarDust = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.STAR_DUST.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.star_dust.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.star_dust.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("star_dust", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.STAR_DUST.get()))
//                .save(saver, "kaleidoscope_nether/star_dust");
//
//        Advancement getStarStew = Advancement.Builder.advancement()
//                .parent(getStarDust)
//                .display(KNItems.STAR_GHAST_PASTA.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.get_star_stew.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.get_star_stew.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("get_star_stew", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(KNTags.Items.STAR_BLESSING_FOODS).build()))
//                .save(saver, "kaleidoscope_nether/get_star_stew");
//
//        Advancement getSoulReturnRice = Advancement.Builder.advancement()
//                .parent(getStarDust)
//                .display(KNItems.SOUL_RETURN_RICE.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.soul_return_rice.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.soul_return_rice.description"),
//                        null, FrameType.GOAL, true, true, false)
//                .addCriterion("soul_return_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SOUL_RETURN_RICE.get()))
//                .save(saver, "kaleidoscope_nether/soul_return_rice");
//
//        Advancement starBlessingBuff = Advancement.Builder.advancement()
//                .parent(getStarDust)
//                .display(KNItems.STAR_STEW.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.star_blessing_buff.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.star_blessing_buff.description"),
//                        null, FrameType.GOAL, true, true, false)
//                .addCriterion("star_blessing_buff", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(KNEffects.STAR_BLESSING.get())))
//                .save(saver, "kaleidoscope_nether/star_blessing_buff");
//
//        Advancement starDiet = Advancement.Builder.advancement()
//                .parent(starBlessingBuff)
//                .display(KNItems.STAR_STEW_MEAT.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.star_diet.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.star_diet.description"),
//                        null, FrameType.CHALLENGE, true, true, false)
//                .addCriterion("soul_return_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SOUL_RETURN_RICE.get()))
//                .addCriterion("star_stew", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_STEW.get()))
//                .addCriterion("star_ghast_pasta", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_GHAST_PASTA.get()))
//                .addCriterion("star_stew_meat", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.STAR_STEW_MEAT.get()))
//                .requirements(RequirementsStrategy.AND)
//                .save(saver, "kaleidoscope_nether/star_diet");
//
//        Advancement netherAgriculturist = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.CRIMSON_FRUIT.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_agriculturist.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_agriculturist.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("crimson_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.CRIMSON_FRUIT.get()))
//                .addCriterion("warped_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.WARPED_FRUIT.get()))
//                .addCriterion("poisonous_fruit", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.POISONOUS_FRUIT.get()))
//                .addCriterion("soul_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.SOUL_PEPPER.get()))
//                .requirements(RequirementsStrategy.AND)
//                .save(saver, "kaleidoscope_nether/nether_agriculturist");
//
//        Advancement getNetherCaterpillar = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.NETHER_CATERPILLAR.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("nether_caterpillar", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.NETHER_CATERPILLAR.get()))
//                .save(saver, "kaleidoscope_nether/nether_caterpillar");
//
//        Advancement netherCaterpillarFeast = Advancement.Builder.advancement()
//                .parent(getNetherCaterpillar)
//                .display(KNItems.NETHER_CATERPILLAR_SASHIMI.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar_feast.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.nether_caterpillar_feast.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("nether_caterpillar_sashimi", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.NETHER_CATERPILLAR_SASHIMI.get()))
//                .addCriterion("caramel_nether_caterpillar", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CARAMEL_NETHER_CATERPILLAR.get()))
//                .addCriterion("caramel_nether_caterpillar_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CARAMEL_NETHER_CATERPILLAR_RICE.get()))
//                .requirements(RequirementsStrategy.AND)
//                .save(saver, "kaleidoscope_nether/nether_caterpillar_feast");
//
//        Advancement getHoglinTusk = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.HOGLIN_TUSK.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("hoglin_tusk", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.HOGLIN_TUSK.get()))
//                .save(saver, "kaleidoscope_nether/hoglin_tusk");
//
//        Advancement getHoglinTuskBraisedMeat = Advancement.Builder.advancement()
//                .parent(getHoglinTusk)
//                .display(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk_braised_meat.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.hoglin_tusk_braised_meat.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("hoglin_tusk_braised_meat", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get()))
//                .save(saver, "kaleidoscope_nether/hoglin_tusk_braised_meat");
//
//        Advancement tuskKnife = Advancement.Builder.advancement()
//                .parent(getHoglinTusk)
//                .display(KNItems.PRIMITIVE_MACHETE.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.tusk_knife.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.tusk_knife.description"),
//                        null, FrameType.GOAL, true, true, false)
//                .addCriterion("primitive_machete", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.PRIMITIVE_MACHETE.get()))
//                .save(saver, "kaleidoscope_nether/tusk_knife");
//
//        Advancement eatLavaRoastedChicken = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.LAVA_ROASTED_CHICKEN.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.lava_roasted_chicken.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.lava_roasted_chicken.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("lava_roasted_chicken", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.LAVA_ROASTED_CHICKEN.get()))
//                .save(saver, "kaleidoscope_nether/lava_roasted_chicken");
//
//        Advancement gildedFragment = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.GILDED_FRAGMENT.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.gilded_fragment.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.gilded_fragment.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("gilded_fragment", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.GILDED_FRAGMENT.get()))
//                .save(saver, "kaleidoscope_nether/gilded_fragment");
//
//        Advancement gildedBarbaricRoast = Advancement.Builder.advancement()
//                .parent(gildedFragment)
//                .display(KNItems.GILDED_BARBARIC_ROAST.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.gilded_barbaric_roast.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.gilded_barbaric_roast.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("gilded_barbaric_roast", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.GILDED_BARBARIC_ROAST.get()))
//                .save(saver, "kaleidoscope_nether/gilded_barbaric_roast");
//
//        Advancement witherRib = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.WITHER_RIB.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.wither_rib.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.wither_rib.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("wither_rib", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.WITHER_RIB.get()))
//                .save(saver, "kaleidoscope_nether/wither_rib");
//
//        Advancement witherBoneSoup = Advancement.Builder.advancement()
//                .parent(witherRib)
//                .display(KNItems.WITHER_BONE_SOUP.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.wither_bone_soup.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.wither_bone_soup.description"),
//                        null, FrameType.TASK, true, true, false)
//                .addCriterion("wither_bone_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.WITHER_BONE_SOUP.get()))
//                .save(saver, "kaleidoscope_nether/wither_bone_soup");
//
//        Advancement eightCuisines = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.ROUJIAMO.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.eight_cuisines.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.eight_cuisines.description"),
//                        null, FrameType.GOAL, true, true, false)
//                .addCriterion("spicy_pot", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SPICY_POT.get()))
//                .addCriterion("spicy_pot_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SPICY_POT_RICE.get()))
//                .addCriterion("mapo_tofu", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.MAPO_TOFU.get()))
//                .addCriterion("mapo_tofu_rice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.MAPO_TOFU_RICE.get()))
//                .addCriterion("chongqing_noodles", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.CHONGQING_NOODLES.get()))
//                .addCriterion("luosifen", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.LUOSIFEN.get()))
//                .addCriterion("sauerkraut_fish", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.SAUERKRAUT_FISH.get()))
//                .addCriterion("roujiamo", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.ROUJIAMO.get()))
//                .addCriterion("forgetfulness_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.FORGETFULNESS_SOUP.get()))
//                .addCriterion("braised_lion_head", ConsumeItemTrigger.TriggerInstance.usedItem((ItemLike) KNFoodBiteRegistry.BRAISED_LION_HEAD))
//                .addCriterion("braised_pork_rice", ConsumeItemTrigger.TriggerInstance.usedItem((ItemLike) KNFoodBiteRegistry.BRAISED_PORK_RICE))
//                .addCriterion("garlic_oysters", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.GARLIC_OYSTERS.get()))
//                .addCriterion("couples_lung_slice", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.COUPLES_LUNG_SLICE.get()))
//                .addCriterion("pepper_pork_belly_chicken_soup", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.PEPPER_PORK_BELLY_CHICKEN_SOUP.get()))
//                .addCriterion("corn_carrot_pork_rib_soup", ConsumeItemTrigger.TriggerInstance.usedItem((ItemLike) KNFoodBiteRegistry.CORN_CARROT_PORK_RIB_SOUP))
//                .requirements(RequirementsStrategy.AND)
//                .save(saver, "kaleidoscope_nether/eight_cuisines");
//
//        Advancement.Builder SeagullStealFailureTest = Advancement.Builder.advancement()
//                .parent(root)
//                .display(KNItems.EVERLASTING_FLAME_STEAK.get(),
//                        Component.translatable("advancements.kaleidoscope_nether.seagull_steal_failure.title"),
//                        Component.translatable("advancements.kaleidoscope_nether.seagull_steal_failure.description"),
//                        null, FrameType.CHALLENGE, true, true, true)
//                .addCriterion("seagull_steal_failure", KNAdvancementTrigger.Instance.id("seagull_steal_failure"));
//
//        JsonObject jsonObject = ConditionalAdvancement.builder()
//                .addCondition(new ModLoadedCondition("alexsmobs"))
//                .addAdvancement(SeagullStealFailureTest)
//                .write();
//        ModAdvancementProvider.BUILD_CONDITION.accept(jsonObject, ResourceLocation.withDefaultNamespace("kaleidoscope_nether/seagull_steal_failure"));
    }
}