package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.API.KNTags;
import com.bmt.kaleidoscope_nether.registry.KNItems;
import com.bmt.kaleidoscope_nether.registry.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;


public class PoisonousFruit extends CropBlock {
    public PoisonousFruit(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(KNTags.Blocks.SOUL_SOIL_SAND);
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int i = blockState.getValue(AGE);
        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, randomSource.nextInt(10) == 0)) {
            blockState = blockState.setValue(AGE, i + 1);
            serverLevel.setBlock(blockPos, blockState, 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState);
        }

    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof Player living) {
            if (!(living.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FARMER_BOOTS.get() &&
                    living.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.FARMER_LEGGINGS.get() &&
                    living.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.FARMER_CHEST_PLATE.get())) {
                MobEffectInstance effect = living.getEffect(ModEffects.MYSTERIOUS_POISON.get());
                if (effect != null) {
                    if (effect.getDuration() <= 7 * 20) {
                        living.addEffect(new MobEffectInstance(ModEffects.MYSTERIOUS_POISON.get(), 9 * 20, effect.getAmplifier() + 1));
                    }
                } else {
                    living.addEffect(new MobEffectInstance(ModEffects.MYSTERIOUS_POISON.get(), 9 * 20));
                }
            }
        }
        super.entityInside(blockState, level, blockPos, entity);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return KNItems.POISONOUS_FRUIT.get();
    }


    @Override
    public boolean canSurvive(BlockState p_52282_, LevelReader p_52283_, BlockPos p_52284_) {
        return true;
    }
}
