package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.api.KNTags;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.bmt.kaleidoscope_nether.registry.KNEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PoisonousFruit extends KNCropBlockBase {
    public PoisonousFruit(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(KNTags.Blocks.SOUL_SOIL_SAND);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int i = blockState.getValue(AGE);
        if (i < 7 && serverLevel.random.nextInt(10) == 0) {
            blockState = blockState.setValue(AGE, i + 1);
            serverLevel.setBlock(blockPos, blockState, 2);
        }
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof Player player) {
            if (!(player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FARMER_BOOTS &&
                    player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.FARMER_LEGGINGS &&
                    player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.FARMER_CHEST_PLATE)) {

                Holder<MobEffect> mysteriousPoisonHolder = KNEffects.MYSTERIOUS_POISON;
                MobEffectInstance effect = player.getEffect(mysteriousPoisonHolder);
                if (effect != null) {
                    if (effect.getDuration() <= 7 * 20) {
                        player.addEffect(new MobEffectInstance(
                                mysteriousPoisonHolder,
                                9 * 20,
                                effect.getAmplifier() + 1
                        ));
                    }
                } else {
                    player.addEffect(new MobEffectInstance(
                            mysteriousPoisonHolder,
                            9 * 20,
                            0
                    ));
                }
            }
        }
        super.entityInside(blockState, level, blockPos, entity);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return KNItems.POISONOUS_FRUIT;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        BlockState belowState = levelReader.getBlockState(blockpos);
        return this.mayPlaceOn(belowState, levelReader, blockpos);
    }
}