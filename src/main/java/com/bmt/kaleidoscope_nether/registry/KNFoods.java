package com.bmt.kaleidoscope_nether.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import static com.bmt.kaleidoscope_nether.registry.KNEffects.*;
import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects.*;
import static net.minecraft.world.effect.MobEffects.*;

public class KNFoods {
    // 灵魂羊排
    public static final FoodProperties SOUL_LAMB_CHOP_ITEM = (new FoodProperties.Builder()).nutrition(13).saturationMod(0.611F)
            .effect(() -> new MobEffectInstance(GHOST_BUFF.get(), 180 * 20), 1.0F).meat().build();

    public static final FoodProperties SOUL_LAMB_CHOP_BLOCK = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.611F)
            .effect(() -> new MobEffectInstance(GHOST_BUFF.get(), 240 * 20), 1.0F).meat().build();

    // 烈焰永恒牛排
    public static final FoodProperties EVERLASTING_FLAME_STEAK = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 300, 0), 1.0F)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 600, 0), 1.0F)
            .alwaysEat()
            .build();

    // 绯红果
    public static final FoodProperties CRIMSON_FRUIT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.3f)
            .alwaysEat()
            .build();

    // 诡异果
    public static final FoodProperties WARPED_FRUIT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.3f)
            .alwaysEat()
            .build();

    // 下界猪儿虫
    public static final FoodProperties NETHER_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(18).saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(CONFUSION, 200), 1F)
            .alwaysEat()
            .build();

    // 回魂饭
    public static final FoodProperties SOUL_RETURN_RICE = new FoodProperties.Builder()
            .nutrition(16).saturationMod(1.8f)
            .effect(() -> new MobEffectInstance(GHOST_BUFF.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 熔岩烤鸡
    public static final FoodProperties LAVA_ROASTED_CHICKEN = new FoodProperties.Builder()
            .nutrition(13)
            .saturationMod(0.615f)
            .meat()
            .alwaysEat()
            .build();

    // 星之炖菜
    public static final FoodProperties STAR_STEW = new FoodProperties.Builder()
            .nutrition(16).saturationMod(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING_BUFF.get(), 600, 0), 1.0F)
            .alwaysEat()
            .build();

    // 灵魂浓汤
    public static final FoodProperties SOUL_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(GHOST_BUFF.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 生炽足兽肉
    public static final FoodProperties RAW_STRIDER_MEAT = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f)
            .meat()
            .alwaysEat()
            .build();

    // 熟炽足兽肉
    public static final FoodProperties COOKED_STRIDER_MEAT = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.8f)
            .meat()
            .alwaysEat()
            .build();

    // 疣猪火腿
    public static final FoodProperties HAM = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f)
            .meat()
            .alwaysEat()
            .build();

    // 火腿片
    public static final FoodProperties HAM_SLICE = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.2f)
            .meat()
            .alwaysEat()
            .build();

    // 烤疣猪火腿
    public static final FoodProperties ROASTED_HAM = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f)
            .meat()
            .alwaysEat()
            .build();

    // 肉夹馍
    public static final FoodProperties ROUJIAMO = new FoodProperties.Builder()
            .nutrition(8).saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 80 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 红烧炽足兽
    public static final FoodProperties BRAISED_STRIDER = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 凋零大骨汤
    public static final FoodProperties WITHER_BONE_SOUP = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 300 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 炽足兽炖下界疣
    public static final FoodProperties STRIDER_NETHER_WART_STEW = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.615f)
            .meat()
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 恶魂烤串
    public static final FoodProperties GHAST_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.9f)
            .meat()
            .alwaysEat()
            .build();

    // 恶魂触手
    public static final FoodProperties GHAST_TENTACLE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.3f)
            .meat()
            .alwaysEat()
            .build();

    // 烤恶魂触手
    public static final FoodProperties ROASTED_GHAST_TENTACLE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.6f)
            .meat()
            .alwaysEat()
            .build();

    // 恶魂意面
    public static final FoodProperties GHAST_PASTA = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(SULFUR.get(), 90 * 20, 0), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 岩浆膏浓汤
    public static final FoodProperties MAGMA_CREAM_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 480 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 岩浆膏布丁
    public static final FoodProperties MAGMA_CREAM_PUDDING = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.0f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 剧毒果
    public static final FoodProperties POISONOUS_FRUIT = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.1f)
            .alwaysEat()
            .build();

    // 剧毒浓汤
    public static final FoodProperties POISONOUS_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(MYSTERIOUS_POISON.get(), 80 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 灵魂浇汁烤肉
    public static final FoodProperties SOUL_GLAZED_ROAST = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.615f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 90 * 20, 0), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 恶魂布丁
    public static final FoodProperties GHAST_PUDDING = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 野蛮烤肉
    public static final FoodProperties GILDED_BARBARIC_ROAST = new FoodProperties.Builder()
            .nutrition(24).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 90 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 下界猪儿虫刺身
    public static final FoodProperties NETHER_CATERPILLAR_SASHIMI = new FoodProperties.Builder()
            .nutrition(19).saturationMod(0.65f)
            .effect(() -> new MobEffectInstance(MUSTARD.get(), 300 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 黄金烤肉
    public static final FoodProperties GOLDEN_ROAST = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 120 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 下界薯条拼盘
    public static final FoodProperties NETHER_FRIES_PLATTER = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(LAVA_WALKER.get(), 300 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 疣猪兽獠牙焖肉
    public static final FoodProperties HOGLIN_TUSK_BRAISED_MEAT = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 80 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 剧毒恶魂烤肉
    public static final FoodProperties POISONOUS_GHAST_ROAST = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(MYSTERIOUS_POISON.get(), 60 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 灵魂椒炒肉
    public static final FoodProperties SOUL_PEPPER_STIR_FRY = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(GHOST_BUFF.get(), 60 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 炽足兽岩壳炒肉
    public static final FoodProperties STRIDER_SHELL_STIR_FRY = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.615f)
            .effect(() -> new MobEffectInstance(LAVA_WALKER.get(), 300 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 下界果切拼盘
    public static final FoodProperties FRUIT_PLATTER = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(PRESERVATION.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 火腿酸酪
    public static final FoodProperties HAM_YOGURT = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.615f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 酸菜鱼
    public static final FoodProperties SAUERKRAUT_FISH = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 240 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 烈焰浓汤
    public static final FoodProperties BLAZE_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(LAVA_WALKER.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 熔岩果冻
    public static final FoodProperties LAVA_JELLY = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.0f)
            .effect(() -> new MobEffectInstance(LAVA_WALKER.get(), 120 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 绯红沙拉
    public static final FoodProperties CRIMSON_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(CRIMSON_BUFF.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 绯红菌岩浆膏炖肉
    public static final FoodProperties CRIMSON_MAGMA_STEW = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 诡异沙拉
    public static final FoodProperties WARPED_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(WARPED_BUFF.get(), 90 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 灵魂炽足兽烤串
    public static final FoodProperties SOUL_STRIDER_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(WARPED_BUFF.get(), 30 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 黄金烤串
    public static final FoodProperties GOLDEN_KABOB = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 60 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 烈焰烤串
    public static final FoodProperties BLAZING_KABOB = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(LAVA_WALKER.get(), 30 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 绯红烤串
    public static final FoodProperties CRIMSON_KABOB = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(CRIMSON_BUFF.get(), 45 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 诡异烤串
    public static final FoodProperties WARPED_KABOB = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(WARPED_BUFF.get(), 45 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 星之恶魂意面
    public static final FoodProperties STAR_GHAST_PASTA = new FoodProperties.Builder()
            .nutrition(16).saturationMod(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING_BUFF.get(), 600, 0), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 星之炖肉
    public static final FoodProperties STAR_STEW_MEAT = new FoodProperties.Builder()
            .nutrition(16).saturationMod(1.8f)
            .effect(() -> new MobEffectInstance(STAR_BLESSING_BUFF.get(), 600, 0), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 荧光浓汤
    public static final FoodProperties GLOWING_SOUP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(GLOWING_BUFF.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 荧光布丁
    public static final FoodProperties GLOWING_PUDDING = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.0f)
            .effect(() -> new MobEffectInstance(GLOWING_BUFF.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 荧光烤串
    public static final FoodProperties GLOWING_KABOB = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(GLOWING_BUFF.get(), 45 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 荧光沙拉
    public static final FoodProperties GLOWING_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(GLOWING_BUFF.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 黑苹果沙拉
    public static final FoodProperties BLACK_APPLE_SALAD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(PRESERVATION.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 红宝石牛排
    public static final FoodProperties RUBY_STEAK = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 240 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 下界芦苇炖菜
    public static final FoodProperties NETHER_REED_STEW = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 480 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 岩浆膏炒肉
    public static final FoodProperties MAGMA_CREAM_STIR_FRY = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 岩浆膏炒肉盖饭
    public static final FoodProperties MAGMA_CREAM_STIR_FRY_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 麻婆豆腐
    public static final FoodProperties MAPO_TOFU = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 麻婆豆腐盖饭
    public static final FoodProperties MAPO_TOFU_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 诡异蛋糕
    public static final FoodProperties WARPED_CAKE = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.4f)
            .effect(() -> new MobEffectInstance(WARPED_BUFF.get(), 60 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 重庆小面
    public static final FoodProperties CHONGQING_NOODLES = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(WARMTH.get(), 3600), 1.0F)
            .build();

    // 螺蛳粉
    public static final FoodProperties LUOSIFEN = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(WARMTH.get(), 3600), 1.0F)
            .build();

    // 灵魂炒肉
    public static final FoodProperties SOUL_STIR_FRY_MEAT = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 灵魂炒肉盖饭
    public static final FoodProperties SOUL_STIR_FRY_MEAT_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 焦糖下界猪儿虫
    public static final FoodProperties CARAMEL_NETHER_CATERPILLAR = new FoodProperties.Builder()
            .nutrition(18).saturationMod(0.36f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 焦糖下界猪儿虫盖饭
    public static final FoodProperties CARAMEL_NETHER_CATERPILLAR_RICE = new FoodProperties.Builder()
            .nutrition(19).saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 生猪灵肉
    public static final FoodProperties RAW_PIGLIN_MEAT = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.3f)
            .meat()
            .alwaysEat()
            .build();

    // 熟猪灵肉
    public static final FoodProperties COOKED_PIGLIN_MEAT = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.8f)
            .meat()
            .alwaysEat()
            .build();

    // 诡异霉烂肉
    public static final FoodProperties WARPED_HOGLIN_TENDERLOIN_STEW = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.667f)
            .effect(() -> new MobEffectInstance(FLATULENCE.get(), 35 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 麻辣疣猪兽拉面
    public static final FoodProperties SPICY_HOGLIN_RAMEN = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 麻辣香锅
    public static final FoodProperties SPICY_POT = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(VIGOR.get(), 90 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 麻辣香锅盖饭
    public static final FoodProperties SPICY_POT_RICE = new FoodProperties.Builder()
            .nutrition(14).saturationMod(0.643f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 巨兽牛角包
    public static final FoodProperties GIANT_BEAST_CROISSANT = new FoodProperties.Builder()
            .nutrition(20).saturationMod(1.75f)
            .alwaysEat()
            .build();

    // 魔眼咕噜肉
    public static final FoodProperties MAGMA_SWEET_AND_SOUR_PORK = new FoodProperties.Builder()
            .nutrition(16).saturationMod(2.0f)
            .effect(() -> new MobEffectInstance(FIRE_RESISTANCE, 2 * 60 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 孟婆汤
    public static final FoodProperties FORGETFULNESS_SOUP = new FoodProperties.Builder()
            .nutrition(8).saturationMod(1.0f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 80 * 20), 1.0F)
            .alwaysEat()
            .build();

    // 红烧狮子头
    public static final FoodProperties BRAISED_LION_HEAD = new FoodProperties.Builder()
            .nutrition(16).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 80 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 蒜蓉生蚝
    public static final FoodProperties GARLIC_OYSTERS = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 180 * 20, 0), 1.0F)
            .alwaysEat()
            .build();

    // 夫妻肺片
    public static final FoodProperties COUPLES_LUNG_SLICE = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(WARMTH.get(), 80 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 卤肉饭
    public static final FoodProperties BRAISED_PORK_RICE = new FoodProperties.Builder()
            .nutrition(13).saturationMod(0.611f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 胡椒猪肚鸡汤
    public static final FoodProperties PEPPER_PORK_BELLY_CHICKEN_SOUP = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();

    // 玉米胡萝卜排骨汤
    public static final FoodProperties CORN_CARROT_PORK_RIB_SOUP = new FoodProperties.Builder()
            .nutrition(20).saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(SATIATED_SHIELD.get(), 180 * 20), 1.0F)
            .meat()
            .alwaysEat()
            .build();
/*
    // 广式肠粉
    public static final FoodProperties CANTONESE_RICE_NOODLE_ROLL = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
            .alwaysEat()
            .build();
 */
}