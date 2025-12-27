package com.bmt.kaleidoscope_nether.event;

import com.bmt.kaleidoscope_nether.Config;
import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlessingEnchantmentHandler {

    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (!Config.BLESSING_ENCHANTMENT_ENABLED.get()) {
            return;
        }

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack usedItem = event.getItem();
        if (!usedItem.isEdible()) {
            return;
        }

        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BLESSING.get(), chestplate);

        if (enchantmentLevel > 0) {
            float healAmount = Config.BLESSING_ENCHANTMENT_HEAL_AMOUNT.get().floatValue();

            player.heal(healAmount);
        }
    }
}