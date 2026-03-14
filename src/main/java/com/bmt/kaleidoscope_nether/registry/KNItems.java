package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.config.KNConfig;
import com.bmt.kaleidoscope_nether.item.*;
import com.bmt.kaleidoscope_nether.item.EffectItem.*;
import com.bmt.kaleidoscope_nether.item.SpecialFood.*;
import com.bmt.kaleidoscope_nether.item.Tier.PrimitiveMacheteTier;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.ChiliItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.KitchenKnifeItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class KNItems {
    // 恶魂皮
    public static final Item GHAST_HIDE = new Item(new Item.Properties().rarity(Rarity.COMMON));

    // 疣猪兽獠牙
    public static final Item HOGLIN_TUSK = new Item(new Item.Properties().rarity(Rarity.COMMON));

    // 炽足兽岩壳
    public static final Item STRIDER_ROCK_SHELL = new Item(new Item.Properties()
            .fireResistant());

    // 镀金碎片
    public static final Item GILDED_FRAGMENT = new Item(new Item.Properties().rarity(Rarity.COMMON));

    // 凋零骨头
    public static final Item WITHER_RIB = new Item(new Item.Properties().rarity(Rarity.COMMON));

    // 下界炉灶
    public static final Item NETHER_STOVE = new BlockItem(KNBlocks.NETHER_STOVE, new Item.Properties());

    // 星之尘
    public static final Item STAR_DUST = new StarDustItem(new Item.Properties().rarity(Rarity.UNCOMMON));

    // 烈焰珍珠
    public static final Item BLAZE_HEART = new ThrowableFuelItem(new Item.Properties()
            .rarity(Rarity.COMMON)
            .stacksTo(16),
            3000);

    // 疣猪兽皮
    public static final Item HOGLIN_HIDE = new Item(new Item.Properties());

    // 吹箭筒
    public static final Item BLOWGUN = new BlowgunItem(new Item.Properties()
            .durability(384)
            .rarity(Rarity.UNCOMMON));

    // 原始砍刀
    public static final Item PRIMITIVE_MACHETE = new KitchenKnifeItem(new PrimitiveMacheteTier(), new Item.Properties().durability(2031));

    // 恶魂挂坠
    public static final Item GHAST_PENDANT = new GhastPendantItem();

    // 烈焰永恒牛排
    public static final Item EVERLASTING_FLAME_STEAK = new EverlastingFoodItem(KNFoods.EVERLASTING_FLAME_STEAK,
            KNConfig.getEverlastingFlameSteakCooldown(),
            KNConfig.getEverlastingFlameSteakEnabled(),
            Rarity.EPIC);


    // 绯红果
    public static final Item CRIMSON_FRUIT = new SpecialFruitBlockItem(KNBlocks.WEEPING_CAVE_VINES, KNFoods.CRIMSON_FRUIT, Rarity.COMMON);

    // 诡异果
    public static final Item WARPED_FRUIT = new SpecialFruitBlockItem(KNBlocks.TWISTING_CAVE_VINES, KNFoods.WARPED_FRUIT, Rarity.COMMON);

    // 下界猪儿虫
    public static final Item NETHER_CATERPILLAR = new NetherCaterpillarItem(KNFoods.NETHER_CATERPILLAR);

    // 回魂饭
    public static final Item SOUL_RETURN_RICE = new SoulReturnRiceItem(KNFoods.SOUL_RETURN_RICE);

    // 熔岩烤鸡
    public static final Item LAVA_ROASTED_CHICKEN = new LavaRoastedChickenItem(KNFoods.LAVA_ROASTED_CHICKEN);

    // 星之炖菜
    public static final Item STAR_STEW = new BowlFoodOnlyItem(KNFoods.STAR_STEW);

    // 灵魂椒
    public static final Item SOUL_PEPPER = new ChiliItem(4);

    // 灵魂浓汤
    public static final Item SOUL_SOUP = new BowlFoodOnlyItem(KNFoods.SOUL_SOUP);

    // 生炽足兽肉
    public static final Item RAW_STRIDER_MEAT = new Item(new Item.Properties().food(KNFoods.RAW_STRIDER_MEAT));

    // 熟炽足兽肉
    public static final Item COOKED_STRIDER_MEAT = new Item(new Item.Properties().food(KNFoods.COOKED_STRIDER_MEAT));

    // 疣猪火腿
    public static final Item HAM = new Item(new Item.Properties().food(KNFoods.HAM));

    // 火腿片
    public static final Item HAM_SLICE = new Item(new Item.Properties().food(KNFoods.HAM_SLICE));

    // 烤疣猪火腿
    public static final Item ROASTED_HAM = new Item(new Item.Properties().food(KNFoods.ROASTED_HAM));

    // 肉夹馍
    public static final Item ROUJIAMO = new FoodWithEffectsItem(KNFoods.ROUJIAMO);

    // 红烧炽足兽
    public static final Item BRAISED_STRIDER = new BowlFoodOnlyItem(KNFoods.BRAISED_STRIDER);

    // 凋零大骨汤
    public static final Item WITHER_BONE_SOUP = new BowlFoodOnlyItem(KNFoods.WITHER_BONE_SOUP);

    // 炽足兽炖下界疣
    public static final Item STRIDER_NETHER_WART_STEW = new BowlFoodOnlyItem(KNFoods.STRIDER_NETHER_WART_STEW);

    // 恶魂烤串
    public static final Item GHAST_KABOB = new StickReturnFoodItem(KNFoods.GHAST_KABOB);

    // 恶魂触手
    public static final Item GHAST_TENTACLE = new Item(new Item.Properties().food(KNFoods.GHAST_TENTACLE));

    // 烤恶魂触手
    public static final Item ROASTED_GHAST_TENTACLE = new Item(new Item.Properties().food(KNFoods.ROASTED_GHAST_TENTACLE));

    // 恶魂意面
    public static final Item GHAST_PASTA = new BowlFoodOnlyItem(KNFoods.GHAST_PASTA);

    // 岩浆膏浓汤
    public static final Item MAGMA_CREAM_SOUP = new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_SOUP);

    // 岩浆膏布丁
    public static final Item MAGMA_CREAM_PUDDING = new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_PUDDING);

    // 剧毒果
    public static final Item POISONOUS_FRUIT = new MysteriousPoisonFoodBlockItem(KNBlocks.POISONOUS_FRUIT, KNFoods.POISONOUS_FRUIT, Rarity.COMMON);

    // 剧毒浓汤
    public static final Item POISONOUS_SOUP = new BowlFoodOnlyItem(KNFoods.POISONOUS_SOUP);

    // 灵魂浇汁烤肉
    public static final Item SOUL_GLAZED_ROAST = new BowlFoodOnlyItem(KNFoods.SOUL_GLAZED_ROAST);

    // 恶魂布丁
    public static final Item GHAST_PUDDING = new BowlFoodOnlyItem(KNFoods.GHAST_PUDDING);

    // 野蛮烤肉
    public static final Item GILDED_BARBARIC_ROAST = new BowlFoodOnlyItem(KNFoods.GILDED_BARBARIC_ROAST);

    // 下界猪儿虫刺身
    public static final Item NETHER_CATERPILLAR_SASHIMI = new BowlFoodOnlyItem(KNFoods.NETHER_CATERPILLAR_SASHIMI);

    // 黄金烤肉
    public static final Item GOLDEN_ROAST = new BowlFoodOnlyItem(KNFoods.GOLDEN_ROAST);

    // 下界薯条拼盘
    public static final Item NETHER_FRIES_PLATTER = new BowlFoodOnlyItem(KNFoods.NETHER_FRIES_PLATTER);

    // 疣猪兽獠牙焖肉
    public static final Item HOGLIN_TUSK_BRAISED_MEAT = new FoodWithEffectsItem(KNFoods.HOGLIN_TUSK_BRAISED_MEAT);

    // 剧毒恶魂烤肉
    public static final Item POISONOUS_GHAST_ROAST = new BowlFoodOnlyItem(KNFoods.POISONOUS_GHAST_ROAST);

    // 灵魂椒炒肉
    public static final Item SOUL_PEPPER_STIR_FRY = new BowlFoodOnlyItem(KNFoods.SOUL_PEPPER_STIR_FRY);

    // 炽足兽岩壳炒肉
    public static final Item STRIDER_SHELL_STIR_FRY = new BowlFoodOnlyItem(KNFoods.STRIDER_SHELL_STIR_FRY);

    // 下界果切拼盘
    public static final Item FRUIT_PLATTER = new BowlFoodOnlyItem(KNFoods.FRUIT_PLATTER);

    // 火腿酸酪
    public static final Item HAM_YOGURT = new BowlFoodOnlyItem(KNFoods.HAM_YOGURT);

    // 酸菜鱼
    public static final Item SAUERKRAUT_FISH = new BowlFoodOnlyItem(KNFoods.SAUERKRAUT_FISH);

    // 烈焰浓汤
    public static final Item BLAZE_SOUP = new BowlFoodOnlyItem(KNFoods.BLAZE_SOUP);

    // 熔岩果冻
    public static final Item LAVA_JELLY = new BowlFoodOnlyItem(KNFoods.LAVA_JELLY);

    // 绯红沙拉
    public static final Item CRIMSON_SALAD = new BowlFoodOnlyItem(KNFoods.CRIMSON_SALAD);

    // 绯红菌岩浆膏炖肉
    public static final Item CRIMSON_MAGMA_STEW = new BowlFoodOnlyItem(KNFoods.CRIMSON_MAGMA_STEW);

    // 诡异沙拉
    public static final Item WARPED_SALAD = new BowlFoodOnlyItem(KNFoods.WARPED_SALAD);

    // 灵魂炽足兽烤串
    public static final Item SOUL_STRIDER_KABOB = new StickReturnFoodItem(KNFoods.SOUL_STRIDER_KABOB);

    // 黄金烤串
    public static final Item GOLDEN_KABOB = new StickReturnFoodItem(KNFoods.GOLDEN_KABOB);

    // 烈焰烤串
    public static final Item BLAZING_KABOB = new StickReturnFoodItem(KNFoods.BLAZING_KABOB);

    // 绯红烤串
    public static final Item CRIMSON_KABOB = new StickReturnFoodItem(KNFoods.CRIMSON_KABOB);

    // 诡异烤串
    public static final Item WARPED_KABOB = new StickReturnFoodItem(KNFoods.WARPED_KABOB);

    // 星之恶魂意面
    public static final Item STAR_GHAST_PASTA = new BowlFoodOnlyItem(KNFoods.STAR_GHAST_PASTA);

    // 星之炖肉
    public static final Item STAR_STEW_MEAT = new BowlFoodOnlyItem(KNFoods.STAR_STEW_MEAT);

    // 荧光浓汤
    public static final Item GLOWING_SOUP = new BowlFoodOnlyItem(KNFoods.GLOWING_SOUP);

    // 荧光布丁
    public static final Item GLOWING_PUDDING = new BowlFoodOnlyItem(KNFoods.GLOWING_PUDDING);

    // 荧光烤串
    public static final Item GLOWING_KABOB = new StickReturnFoodItem(KNFoods.GLOWING_KABOB);

    // 荧光沙拉
    public static final Item GLOWING_SALAD = new BowlFoodOnlyItem(KNFoods.GLOWING_SALAD);

    // 黑苹果沙拉
    public static final Item BLACK_APPLE_SALAD = new BowlFoodOnlyItem(KNFoods.BLACK_APPLE_SALAD);

    // 红宝石牛排
    public static final Item RUBY_STEAK = new BowlFoodOnlyItem(KNFoods.RUBY_STEAK);

    // 下界芦苇炖菜
    public static final Item NETHER_REED_STEW = new BowlFoodOnlyItem(KNFoods.NETHER_REED_STEW);

    // 岩浆膏炒肉
    public static final Item MAGMA_CREAM_STIR_FRY = new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY);

    // 岩浆膏炒肉盖饭
    public static final Item MAGMA_CREAM_STIR_FRY_RICE = new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY_RICE);

    // 麻婆豆腐
    public static final Item MAPO_TOFU = new BowlFoodOnlyItem(KNFoods.MAPO_TOFU);

    // 麻婆豆腐盖饭
    public static final Item MAPO_TOFU_RICE = new BowlFoodOnlyItem(KNFoods.MAPO_TOFU_RICE);

    // 诡异蛋糕
    public static final Item WARPED_CAKE = new FoodWithEffectsItem(KNFoods.WARPED_CAKE);

    // 重庆小面
    public static final Item CHONGQING_NOODLES = new BowlFoodOnlyItem(KNFoods.CHONGQING_NOODLES);

    // 螺蛳粉
    public static final Item LUOSIFEN = new BowlFoodOnlyItem(KNFoods.LUOSIFEN);

    // 灵魂炒肉
    public static final Item SOUL_STIR_FRY_MEAT = new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT);

    // 灵魂炒肉盖饭
    public static final Item SOUL_STIR_FRY_MEAT_RICE = new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT_RICE);

    // 焦糖下界猪儿虫
    public static final Item CARAMEL_NETHER_CATERPILLAR = new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR);

    // 焦糖下界猪儿虫盖饭
    public static final Item CARAMEL_NETHER_CATERPILLAR_RICE = new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR_RICE);

    // 生猪灵肉
    public static final Item RAW_PIGLIN_MEAT = new Item(new Item.Properties().food(KNFoods.RAW_PIGLIN_MEAT));

    // 熟猪灵肉
    public static final Item COOKED_PIGLIN_MEAT = new Item(new Item.Properties().food(KNFoods.COOKED_PIGLIN_MEAT));

    // 诡异霉烂肉
    public static final Item WARPED_HOGLIN_TENDERLOIN_STEW = new BowlFoodOnlyItem(KNFoods.WARPED_HOGLIN_TENDERLOIN_STEW);

    // 麻辣疣猪兽拉面
    public static final Item SPICY_HOGLIN_RAMEN = new BowlFoodOnlyItem(KNFoods.SPICY_HOGLIN_RAMEN);

    // 麻辣香锅
    public static final Item SPICY_POT = new BowlFoodOnlyItem(KNFoods.SPICY_POT);

    // 麻辣香锅盖饭
    public static final Item SPICY_POT_RICE = new BowlFoodOnlyItem(KNFoods.SPICY_POT_RICE);

    // 孟婆汤
    public static final Item FORGETFULNESS_SOUP = new ForgetfulnessSoupItem(KNFoods.FORGETFULNESS_SOUP);

    // 红烧狮子头
    public static final Item BRAISED_LION_HEAD = new BowlFoodOnlyItem(KNFoods.BRAISED_LION_HEAD);

    // 蒜蓉生蚝
    public static final Item GARLIC_OYSTERS = new BowlFoodOnlyItem(KNFoods.GARLIC_OYSTERS);

    // 夫妻肺片
    public static final Item COUPLES_LUNG_SLICE = new BowlFoodOnlyItem(KNFoods.COUPLES_LUNG_SLICE);

    // 卤肉饭
    public static final Item BRAISED_PORK_RICE = new BowlFoodOnlyItem(KNFoods.BRAISED_PORK_RICE);

    // 胡椒猪肚鸡汤
    public static final Item PEPPER_PORK_BELLY_CHICKEN_SOUP = new BowlFoodOnlyItem(KNFoods.PEPPER_PORK_BELLY_CHICKEN_SOUP);

    // 玉米胡萝卜排骨汤
    public static final Item CORN_CARROT_PORK_RIB_SOUP = new BowlFoodOnlyItem(KNFoods.CORN_CARROT_PORK_RIB_SOUP);

    public static void registerItems() {
        // 基础材料
        register("ghast_hide", GHAST_HIDE);
        register("hoglin_tusk", HOGLIN_TUSK);
        register("strider_rock_shell", STRIDER_ROCK_SHELL);
        register("gilded_fragment", GILDED_FRAGMENT);
        register("wither_rib", WITHER_RIB);
        register("hoglin_hide", HOGLIN_HIDE);

        // 工具和武器
        register("blowgun", BLOWGUN);
        register("primitive_machete", PRIMITIVE_MACHETE);
        register("ghast_pendant", GHAST_PENDANT);

        // 特殊物品
        register("star_dust", STAR_DUST);
        register("blaze_heart", BLAZE_HEART);
        register("everlasting_flame_steak", EVERLASTING_FLAME_STEAK);

        // 方块物品
        register("nether_stove", NETHER_STOVE);
        register("crimson_fruit", CRIMSON_FRUIT);
        register("warped_fruit", WARPED_FRUIT);
        register("poisonous_fruit", POISONOUS_FRUIT);

        // 食物
        register("nether_caterpillar", NETHER_CATERPILLAR);
        register("soul_return_rice", SOUL_RETURN_RICE);
        register("lava_roasted_chicken", LAVA_ROASTED_CHICKEN);
        register("star_stew", STAR_STEW);
        register("soul_pepper", SOUL_PEPPER);
        register("soul_soup", SOUL_SOUP);
        register("raw_strider_meat", RAW_STRIDER_MEAT);
        register("cooked_strider_meat", COOKED_STRIDER_MEAT);
        register("ham", HAM);
        register("ham_slice", HAM_SLICE);
        register("roasted_ham", ROASTED_HAM);
        register("roujiamo", ROUJIAMO);
        register("braised_strider", BRAISED_STRIDER);
        register("wither_bone_soup", WITHER_BONE_SOUP);
        register("strider_nether_wart_stew", STRIDER_NETHER_WART_STEW);
        register("ghast_kabob", GHAST_KABOB);
        register("ghast_tentacle", GHAST_TENTACLE);
        register("roasted_ghast_tentacle", ROASTED_GHAST_TENTACLE);
        register("ghast_pasta", GHAST_PASTA);
        register("magma_cream_soup", MAGMA_CREAM_SOUP);
        register("magma_cream_pudding", MAGMA_CREAM_PUDDING);
        register("poisonous_soup", POISONOUS_SOUP);
        register("soul_glazed_roast", SOUL_GLAZED_ROAST);
        register("ghast_pudding", GHAST_PUDDING);
        register("gilded_barbaric_roast", GILDED_BARBARIC_ROAST);
        register("nether_caterpillar_sashimi", NETHER_CATERPILLAR_SASHIMI);
        register("golden_roast", GOLDEN_ROAST);
        register("nether_fries_platter", NETHER_FRIES_PLATTER);
        register("hoglin_tusk_braised_meat", HOGLIN_TUSK_BRAISED_MEAT);
        register("poisonous_ghast_roast", POISONOUS_GHAST_ROAST);
        register("soul_pepper_stir_fry", SOUL_PEPPER_STIR_FRY);
        register("strider_shell_stir_fry", STRIDER_SHELL_STIR_FRY);
        register("fruit_platter", FRUIT_PLATTER);
        register("ham_yogurt", HAM_YOGURT);
        register("sauerkraut_fish", SAUERKRAUT_FISH);
        register("blaze_soup", BLAZE_SOUP);
        register("lava_jelly", LAVA_JELLY);
        register("crimson_salad", CRIMSON_SALAD);
        register("crimson_magma_stew", CRIMSON_MAGMA_STEW);
        register("warped_salad", WARPED_SALAD);
        register("soul_strider_kabob", SOUL_STRIDER_KABOB);
        register("golden_kabob", GOLDEN_KABOB);
        register("blazing_kabob", BLAZING_KABOB);
        register("crimson_kabob", CRIMSON_KABOB);
        register("warped_kabob", WARPED_KABOB);
        register("star_ghast_pasta", STAR_GHAST_PASTA);
        register("star_stew_meat", STAR_STEW_MEAT);
        register("glowing_soup", GLOWING_SOUP);
        register("glowing_pudding", GLOWING_PUDDING);
        register("glowing_kabob", GLOWING_KABOB);
        register("glowing_salad", GLOWING_SALAD);
        register("black_apple_salad", BLACK_APPLE_SALAD);
        register("ruby_steak", RUBY_STEAK);
        register("nether_reed_stew", NETHER_REED_STEW);
        register("magma_cream_stir_fry", MAGMA_CREAM_STIR_FRY);
        register("magma_cream_stir_fry_rice", MAGMA_CREAM_STIR_FRY_RICE);
        register("mapo_tofu", MAPO_TOFU);
        register("mapo_tofu_rice", MAPO_TOFU_RICE);
        register("warped_cake", WARPED_CAKE);
        register("chongqing_noodles", CHONGQING_NOODLES);
        register("luosifen", LUOSIFEN);
        register("soul_stir_fry_meat", SOUL_STIR_FRY_MEAT);
        register("soul_stir_fry_meat_rice", SOUL_STIR_FRY_MEAT_RICE);
        register("caramel_nether_caterpillar", CARAMEL_NETHER_CATERPILLAR);
        register("caramel_nether_caterpillar_rice", CARAMEL_NETHER_CATERPILLAR_RICE);
        register("raw_piglin_meat", RAW_PIGLIN_MEAT);
        register("cooked_piglin_meat", COOKED_PIGLIN_MEAT);
        register("warped_hoglin_tenderloin_stew", WARPED_HOGLIN_TENDERLOIN_STEW);
        register("spicy_hoglin_ramen", SPICY_HOGLIN_RAMEN);
        register("spicy_pot", SPICY_POT);
        register("spicy_pot_rice", SPICY_POT_RICE);
        register("forgetfulness_soup", FORGETFULNESS_SOUP);
        register("braised_lion_head", BRAISED_LION_HEAD);
        register("garlic_oysters", GARLIC_OYSTERS);
        register("couples_lung_slice", COUPLES_LUNG_SLICE);
        register("braised_pork_rice", BRAISED_PORK_RICE);
        register("pepper_pork_belly_chicken_soup", PEPPER_PORK_BELLY_CHICKEN_SOUP);
        register("corn_carrot_pork_rib_soup", CORN_CARROT_PORK_RIB_SOUP);
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeNether.MOD_ID, name),
                item);
    }
}
