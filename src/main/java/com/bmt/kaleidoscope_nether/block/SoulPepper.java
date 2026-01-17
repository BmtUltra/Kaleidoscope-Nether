package com.bmt.kaleidoscope_nether.block;

import com.bmt.kaleidoscope_nether.api.KNTags;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class SoulPepper extends KNCropBlockBase {
    public SoulPepper(Properties properties) {
        super(properties);
    }


    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(KNTags.Blocks.SOUL_SOIL_SAND);
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int i = blockState.getValue(AGE);
        if (i < 7 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, randomSource.nextInt(10) == 0)) {
            blockState = blockState.setValue(AGE, i + 1);
            serverLevel.setBlock(blockPos, blockState, 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState);
        }

    }

    @Override
    public Set<Item> getHandHarvestExclude() {
        return Set.of(ModItems.CHILI_SEED.get());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0) {
            spawnSoulParticles(level, blockPos);
        }
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
//        if (entity instanceof Player living) {
//            if (!(living.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FARMER_BOOTS.get() &&
//                    living.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.FARMER_LEGGINGS.get() &&
//                    living.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.FARMER_CHEST_PLATE.get())) {
//                MobEffectInstance effect = living.getEffect(KNEffects.MYSTERIOUS_POISON.get());
//                if (effect != null) {
//                    if (effect.getDuration() <= 7 * 20) {
//                        living.addEffect(new MobEffectInstance(KNEffects.MYSTERIOUS_POISON.get(), 9 * 20, effect.getAmplifier() + 1));
//                    }
//                } else {
//                    living.addEffect(new MobEffectInstance(KNEffects.MYSTERIOUS_POISON.get(), 9 * 20));
//                }
//            }
//        }
        super.entityInside(blockState, level, blockPos, entity);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CHILI_SEED.get();
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        if (blockState.getBlock() == this)
            return levelReader.getBlockState(blockpos).canSustainPlant(levelReader, blockpos, Direction.UP, this);
        return this.mayPlaceOn(levelReader.getBlockState(blockpos), levelReader, blockpos);
    }

    private static void spawnSoulParticles(Level level, BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;

        int particleCount = 1;

        for (int i = 0; i < particleCount; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * 0.8;
            double offsetY = level.random.nextDouble() * 0.5;
            double offsetZ = (level.random.nextDouble() - 0.5) * 0.8;

            double speedX = (level.random.nextDouble() - 0.5) * 0.02;
            double speedY = level.random.nextDouble() * 0.03 + 0.01;
            double speedZ = (level.random.nextDouble() - 0.5) * 0.02;

            level.addParticle(
                    ParticleTypes.SOUL,
                    x + offsetX,
                    y + offsetY,
                    z + offsetZ,
                    speedX, speedY, speedZ
            );
        }
    }
}
