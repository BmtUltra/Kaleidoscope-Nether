package com.bmt.kaleidoscope_nether.enchantment;

import com.bmt.kaleidoscope_nether.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LavaWalkerEnchantment extends Enchantment {

    public LavaWalkerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem &&
                armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    private static void applyLavaWalkerEffect(LivingEntity entity) {
        if (entity instanceof Player player && player.isSpectator()) {
            return;
        }

        Vec3 pos = entity.position();
        Vec3 movement = entity.getDeltaMovement();
        Vec3 futurePos = pos.add(movement);
        BlockPos onPos = entity.getOnPos();
        BlockPos futureBlockPos = new BlockPos((int) futurePos.x, (int) futurePos.y, (int) futurePos.z);

        boolean isMoving = movement.lengthSqr() > 0.0001;

        if (entity.isInLava()) {
            entity.setDeltaMovement(movement.add(0, 0.1, 0));

            if (isMoving && entity.level() instanceof ServerLevel level && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
        } else if (entity.level().getFluidState(onPos).is(FluidTags.LAVA)) {
            if (entity.level() instanceof ServerLevel level && isMoving && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
            entity.setDeltaMovement(movement.x(), Math.max(movement.y(), 0D), movement.z());
            entity.setOnGround(true);
        } else if (entity.level().getFluidState(futureBlockPos).is(FluidTags.LAVA) && movement.y() > -0.8) {
            if (entity.level() instanceof ServerLevel level && isMoving && entity.tickCount % 5 == 0) {
                level.sendParticles(ParticleTypes.LAVA,
                        pos.x(), pos.y() + 0.1D, pos.z(),
                        3,
                        0.2, 0.1, 0.2, 1.5);
            }
            entity.setDeltaMovement(movement.x(), Math.max(movement.y(), movement.y() * 0.5), movement.z());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player != null) {
            Player player = event.player;

            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

            if (!boots.isEmpty()) {
                int lavaWalkerLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.LAVA_WALKER.get(), boots);

                if (lavaWalkerLevel > 0) {
                    applyLavaWalkerEffect(player);
                }
            }
        }
    }
}