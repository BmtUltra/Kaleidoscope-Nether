package com.bmt.kaleidoscope_nether.client;

import com.bmt.kaleidoscope_nether.item.SeedBagItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SeedBagClientTooltipComponent implements ClientTooltipComponent {

    private final ItemStack seedBagStack;
    private int width;
    private int height;
    private int itemsPerRow = 6; // 每行最多6个物品
    private int itemSpacing = 18; // 物品间距

    public SeedBagClientTooltipComponent(SeedBagItem.SeedBagTooltip tooltip) {
        this.seedBagStack = tooltip.getSeedBagStack();

        SeedBagItem.BagContents contents = SeedBagItem.getContents(seedBagStack);
        if(!contents.isEmpty()) {
            // 计算总图标数量（考虑超过64的情况）
            int totalIcons = 0;
            for (SeedBagItem.SeedEntry entry : contents.getEntries()) {
                int iconCount = (int) Math.ceil((double) entry.count / 64);
                totalIcons += iconCount;
            }

            // 限制最大显示数量为64个图标
            totalIcons = Math.min(totalIcons, 64);

            // 计算行数
            int rows = (int) Math.ceil((double) totalIcons / itemsPerRow);

            // 计算宽度：取每行最大宽度
            int itemsInLastRow = totalIcons % itemsPerRow;
            if (itemsInLastRow == 0) itemsInLastRow = itemsPerRow;

            int maxItemsInRow = Math.min(totalIcons, itemsPerRow);
            width = 4 + maxItemsInRow * itemSpacing; // 边距4像素

            // 计算高度：行数 * 每行高度 + 边距
            height = 4 + rows * 18; // 边距4像素，行高18像素
        } else {
            width = 0;
            height = 0;
        }
    }

    @Override
    public void renderImage(@NotNull Font font, int tooltipX, int tooltipY, @NotNull GuiGraphics guiGraphics) {
        SeedBagItem.BagContents contents = SeedBagItem.getContents(seedBagStack);
        if(!contents.isEmpty()) {
            Minecraft mc = Minecraft.getInstance();

            // 计算总图标索引
            int totalIconIndex = 0;

            for(int i = 0; i < contents.getEntries().size(); i++) {
                SeedBagItem.SeedEntry entry = contents.getEntries().get(i);

                // 计算需要渲染的图标数量（64上限）
                int iconCount = (int) Math.ceil((double) entry.count / 64);

                // 为每个图标分配独立的位置
                for (int iconIndex = 0; iconIndex < iconCount; iconIndex++) {
                    // 限制最大显示数量为64个图标
                    if (totalIconIndex >= 64) {
                        return;
                    }

                    // 创建显示用的物品堆，设置实际数量（不超过64）
                    ItemStack seed = entry.seed.copy();
                    int displayCount = Math.min(entry.count - (iconIndex * 64), 64);
                    seed.setCount(displayCount);

                    // 计算行和列
                    int row = totalIconIndex / itemsPerRow;
                    int col = totalIconIndex % itemsPerRow;

                    // 计算每个图标的位置
                    int x = tooltipX + 2 + col * itemSpacing;
                    int y = tooltipY + 2 + row * 18;

                    // 渲染物品图标
                    guiGraphics.renderItem(seed, x, y);

                    // 渲染物品装饰（数量显示）
                    guiGraphics.renderItemDecorations(mc.font, seed, x, y);

                    totalIconIndex++;
                }
            }
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return width;
    }
}