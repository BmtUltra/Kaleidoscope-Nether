package com.bmt.kaleidoscope_nether.data;

import com.bmt.kaleidoscope_nether.registry.KNBlocks;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of(), List.of(
                new SubProviderEntry(BlockLoots::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class BlockLoots extends BlockLootSubProvider {

        protected BlockLoots() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            simpleCropBlockLoot(KNBlocks.POISONOUS_FRUIT.get(), 7, KNItems.POISONOUS_FRUIT.get(), KNItems.POISONOUS_FRUIT.get(), 3);
            simpleCropBlockLoot(KNBlocks.SOUL_PEPPER.get(), 7, ModItems.CHILI_SEED.get(), KNItems.SOUL_PEPPER.get(), 3);

        }

        private void simpleCropBlockLoot(Block cropBlock, int canHarvestAge, Item seed, Item crop, int cropCount) {
            LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, canHarvestAge));
            this.add(cropBlock, this.applyExplosionDecay(cropBlock, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(seed)))
                    .withPool(LootPool.lootPool().when(ageCondition).add(LootItem.lootTableItem(crop).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, cropCount))))));
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return KNBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
        }
    }

}
