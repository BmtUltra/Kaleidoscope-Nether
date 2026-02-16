package com.chadate.kaleidoscope_nether.event;

import com.chadate.kaleidoscope_nether.effect.CrimsonBuffEffect;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = "kaleidoscope_nether")
public class KNEventSubscriber {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        // 检查攻击者是否是生物实体
        if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            float originalDamage = event.getNewDamage();

            // 使用CrimsonBuffEffect计算伤害加成
            float modifiedDamage = CrimsonBuffEffect.calculateDamageBonus(attacker, originalDamage);

            // 如果伤害有变化，设置新的伤害值
            if (modifiedDamage != originalDamage) {
                event.setNewDamage(modifiedDamage);
            }
        }
    }
}