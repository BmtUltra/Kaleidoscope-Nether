package com.bmt.kaleidoscope_nether.item;

import com.bmt.kaleidoscope_nether.Config;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class StarDustItem extends Item {

    public StarDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack starDustStack, @NotNull ItemStack incoming, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess carriedSlotAccessor) {
        // 移除堆叠数量限制，允许堆叠的星之尘进行修复
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
            return false;
        }

        if (incoming.isEmpty()) {
            return false; // 星之尘不支持拖出
        } else {
            return repairTargetItem(player, starDustStack, incoming, carriedSlotAccessor);
        }
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack starDustStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        // 移除堆叠数量限制，允许堆叠的星之尘进行修复
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
            return false;
        }

        ItemStack targetStack = slot.getItem();
        if (targetStack.isEmpty()) {
            return false; // 星之尘不支持拖入空槽
        } else {
            return repairTargetItem(player, starDustStack, targetStack, slot);
        }
    }

    private boolean repairTargetItem(Player player, ItemStack starDustStack, ItemStack targetStack, Object target) {
        // 检查目标物品是否有耐久
        if (!targetStack.isDamageableItem()) {
            return false;
        }

        // 检查物品是否已完全修复
        if (targetStack.getDamageValue() == 0) {
            return false;
        }


        // 检查星之尘堆叠是否为空
        if (starDustStack.isEmpty()) {
            return false;
        }

        // 执行修复
        return performRepair(player, starDustStack, targetStack, target);
    }

    private boolean performRepair(Player player, ItemStack starDustStack, ItemStack targetStack, Object target) {
        if (player.level().isClientSide()) {
            return false;
        }

        // 计算修复量：25%最大耐久 + 250点
        int maxDurability = targetStack.getMaxDamage();
        int currentDamage = targetStack.getDamageValue();

        int repairAmount = (int) (maxDurability * Config.STAR_DUST_REPAIR_PERCENTAGE.get() / 100.0f) + Config.STAR_DUST_REPAIR_FLAT.get();
        int newDamage = Math.max(0, currentDamage - repairAmount);

        // 应用修复
        targetStack.setDamageValue(newDamage);

        // 消耗1个星之尘（无论堆叠数量多少）
        starDustStack.shrink(1);

        // 更新目标槽位
        if (target instanceof Slot slot) {
            slot.setChanged();
        } else if (target instanceof SlotAccess slotAccess) {
            // 对于 SlotAccess，需要更新目标物品
            slotAccess.set(targetStack);
        }

        // 播放音效
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ANVIL_USE, SoundSource.PLAYERS, 0.8F, 1.0F);

        return true;
    }
}