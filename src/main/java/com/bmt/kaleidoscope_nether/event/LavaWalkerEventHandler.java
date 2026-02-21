package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.KNEnchantments;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LavaWalkerEventHandler {

    @SubscribeEvent
    public static void onLivingTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        
        LivingEntity entity = event.player;
        
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
        if (boots.isEmpty()) {
            return;
        }

        int enchantmentLevel = boots.getEnchantmentLevel(KNEnchantments.LAVA_WALKER.get());
        if (enchantmentLevel <= 0) {
            return;
        }

        if (entity.isShiftKeyDown()) {
            return;
        }

        Vec3 pos = entity.position();
        Vec3 motion = entity.getDeltaMovement();
        boolean isMoving = motion.lengthSqr() > 0.0001;

        boolean inLava = entity.isInLava();
        boolean onLavaGround = entity.onGround() && entity.level().getFluidState(entity.getOnPos()).is(net.minecraft.tags.FluidTags.LAVA);

        if (inLava) {
            if (motion.y < 0.3) {
                entity.setDeltaMovement(new Vec3(motion.x, motion.y + 0.1, motion.z));
            }
        }

        if ((inLava || onLavaGround) && isMoving && entity.level() instanceof ServerLevel level && entity.tickCount % 5 == 0) {
            level.sendParticles(
                    ParticleTypes.LAVA,
                    pos.x(), pos.y() + 0.1D, pos.z(),
                    3,
                    0.2, 0.1, 0.2,
                    1.5
            );
        }
    }
}