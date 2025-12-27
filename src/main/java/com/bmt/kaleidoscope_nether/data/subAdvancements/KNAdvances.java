package com.bmt.kaleidoscope_nether.data.subAdvancements;

import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class KNAdvances implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement().display(KNItems.CRIMSON_FRUIT.get(), Component.translatable("advancements.kaleidoscope_nether.root.title"), Component.translatable("advancements.kaleidoscope_nether.root.description"), ResourceLocation.parse("kaleidoscope_cookery:textures/advancement/background.png"), FrameType.TASK, false, false, false).addCriterion("kaleidoscope_nether", PlayerTrigger.TriggerInstance.tick()).save(saver, "kaleidoscope_nether/root");

        Advancement getStarDust = Advancement.Builder.advancement().parent(root).display(KNItems.STAR_DUST.get(), Component.translatable("advancements.kaleidoscope_nether.get_star_dust.title"), Component.translatable("advancements.kaleidoscope_nether.get_star_dust.description"), null, FrameType.TASK, true, true, false).addCriterion("get_star_dust", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.STAR_DUST.get())).save(saver, "kaleidoscope_nether/get_star_dust");
        Advancement getStarStew = Advancement.Builder.advancement().parent(getStarDust).display(KNItems.STAR_GHAST_PASTA.get(), Component.translatable("advancements.kaleidoscope_nether.get_star_stew.title"), Component.translatable("advancements.kaleidoscope_nether.get_star_stew.description"), null, FrameType.TASK, true, true, false).addCriterion("get_star_stew", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.STAR_STEW.get(), KNItems.STAR_GHAST_PASTA.get(), KNItems.STAR_STEW_MEAT.get())).save(saver, "kaleidoscope_nether/get_star_stew");
        Advancement getSoulReturnRice = Advancement.Builder.advancement().parent(getStarDust).display(KNItems.BLAZING_KABOB.get(), Component.translatable("advancements.kaleidoscope_nether.get_soul_return_rice.title"), Component.translatable("advancements.kaleidoscope_nether.get_soul_return_rice.description"), null, FrameType.GOAL, true, true, false).addCriterion("get_soul_return_rice", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.SOUL_RETURN_RICE.get())).save(saver, "kaleidoscope_nether/get_soul_return_rice");


        Advancement getSeedBag = Advancement.Builder.advancement().parent(root).display(KNItems.SEED_BAG.get(), Component.translatable("advancements.kaleidoscope_nether.get_seed_bag.title"), Component.translatable("advancements.kaleidoscope_nether.get_seed_bag.description"), null, FrameType.TASK, true, true, false).addCriterion("get_seed_bag", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.SEED_BAG.get())).save(saver, "kaleidoscope_nether/get_seed_bag");

        Advancement getNetherCaterpillar = Advancement.Builder.advancement().parent(root).display(KNItems.NETHER_CATERPILLAR.get(), Component.translatable("advancements.kaleidoscope_nether.get_nether_caterpillar.title"), Component.translatable("advancements.kaleidoscope_nether.get_nether_caterpillar.description"), null, FrameType.TASK, true, true, false).addCriterion("get_nether_caterpillar", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.NETHER_CATERPILLAR.get())).save(saver, "kaleidoscope_nether/get_nether_caterpillar");

        Advancement getHoglinTusk = Advancement.Builder.advancement().parent(root).display(KNItems.HOGLIN_TUSK.get(), Component.translatable("advancements.kaleidoscope_nether.get_hoglin_tusk.title"), Component.translatable("advancements.kaleidoscope_nether.get_hoglin_tusk.description"), null, FrameType.TASK, true, true, false).addCriterion("get_hoglin_tusk", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.HOGLIN_TUSK.get())).save(saver, "kaleidoscope_nether/get_hoglin_tusk");
        Advancement getHoglinTuskBraisedMeat = Advancement.Builder.advancement().parent(getHoglinTusk).display(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get(), Component.translatable("advancements.kaleidoscope_nether.get_hoglin_tusk_braised_meat.title"), Component.translatable("advancements.kaleidoscope_nether.get_hoglin_tusk_braised_meat.description"), null, FrameType.TASK, true, true, false).addCriterion("get_hoglin_tusk_braised_meat", InventoryChangeTrigger.TriggerInstance.hasItems(KNItems.HOGLIN_TUSK_BRAISED_MEAT.get())).save(saver, "kaleidoscope_nether/get_hoglin_tusk_braised_meat");

        Advancement getBlazingBuff = Advancement.Builder.advancement().parent(root).display(KNItems.BLAZING_KABOB.get(), Component.translatable("advancements.kaleidoscope_nether.get_blazing_buff.title"), Component.translatable("advancements.kaleidoscope_nether.get_blazing_buff.description"), null, FrameType.GOAL, true, true, false).addCriterion("get_blazing_buff", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(ModEffects.BLAZING.get()))).save(saver, "kaleidoscope_nether/get_blazing_buff");

        Advancement eatLavaRoastedChicken = Advancement.Builder.advancement().parent(root).display(KNItems.LAVA_ROASTED_CHICKEN.get(), Component.translatable("advancements.kaleidoscope_nether.eat_lava_roasted_chicken.title"), Component.translatable("advancements.kaleidoscope_nether.eat_lava_roasted_chicken.description"), null, FrameType.GOAL, true, true, false).addCriterion("eat_lava_roasted_chicken", ConsumeItemTrigger.TriggerInstance.usedItem(KNItems.LAVA_ROASTED_CHICKEN.get())).save(saver, "kaleidoscope_nether/eat_lava_roasted_chicken");


    }
}
