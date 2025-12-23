package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlessingEnchantmentHandler {
    
    // 存储玩家最近受到的伤害类型和时间
    private static final Map<UUID, DamageRecord> playerDamageRecords = new HashMap<>();
    
    // 伤害减免持续时间（18秒，360tick）
    private static final int REDUCTION_DURATION = 18 * 20;
    
    // 每级减免百分比
    private static final float REDUCTION_PER_LEVEL = 0.05f; // 5%
    
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BLESSING.get(), chestplate);
            
            if (enchantmentLevel > 0) {
                DamageSource damageSource = event.getSource();
                UUID playerId = player.getUUID();
                String damageType = getDamageType(damageSource);
                DamageRecord record = playerDamageRecords.get(playerId);
                long currentTime = player.level().getGameTime();
                if (record != null && record.damageType.equals(damageType)) {
                    if (currentTime - record.timestamp <= REDUCTION_DURATION) {
                        float reduction = enchantmentLevel * REDUCTION_PER_LEVEL; // 每级5%
                        float reducedDamage = event.getAmount() * (1.0f - reduction);
                        event.setAmount(reducedDamage);
                        record.timestamp = currentTime;
                    } else {
                        playerDamageRecords.put(playerId, new DamageRecord(damageType, currentTime));
                    }
                } else {
                    playerDamageRecords.put(playerId, new DamageRecord(damageType, currentTime));
                }
            }
        }
    }

    private static String getDamageType(DamageSource damageSource) {
        if (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || 
            damageSource.is(DamageTypes.LAVA) || damageSource.is(DamageTypes.HOT_FLOOR)) {
            return "fire";
        } else if (damageSource.is(DamageTypes.DROWN)) {
            return "drown";
        } else if (damageSource.is(DamageTypes.FALL)) {
            return "fall";
        } else if (damageSource.is(DamageTypes.MAGIC)) {
            return "magic";
        } else if (damageSource.is(DamageTypes.ARROW) || damageSource.is(DamageTypes.TRIDENT)) {
            return "projectile";
        } else if (damageSource.is(DamageTypes.MOB_ATTACK) || damageSource.is(DamageTypes.PLAYER_ATTACK)) {
            return "melee";
        } else if (damageSource.is(DamageTypes.EXPLOSION)) {
            return "explosion";
        } else if (damageSource.is(DamageTypes.WITHER)) {
            return "wither";
        } else {
            return "other";
        }
    }

    private static class DamageRecord {
        public final String damageType;
        public long timestamp;
        
        public DamageRecord(String damageType, long timestamp) {
            this.damageType = damageType;
            this.timestamp = timestamp;
        }
    }
}