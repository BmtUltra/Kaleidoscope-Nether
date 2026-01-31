package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.config.Config;
import com.bmt.kaleidoscope_nether.item.*;
import com.bmt.kaleidoscope_nether.item.EffectItem.*;
import com.bmt.kaleidoscope_nether.item.SpecialFood.*;
import com.bmt.kaleidoscope_nether.item.Tier.PrimitiveMacheteTier;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodOnlyItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.ChiliItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import com.github.ysbbbbbb.kaleidoscopecookery.item.KitchenKnifeItem;
import com.google.common.collect.Sets;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class KNItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KaleidoscopeNether.MOD_ID);
    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static Item.Properties basicItem() {
        return new Item.Properties();
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }

    // 恶魂皮
    public static final RegistryObject<Item> GHAST_HIDE = registerWithTab("ghast_hide",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    // 疣猪兽獠牙
    public static final RegistryObject<Item> HOGLIN_TUSK = registerWithTab("hoglin_tusk",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    // 炽足兽岩壳
    public static final RegistryObject<Item> STRIDER_ROCK_SHELL = registerWithTab("strider_rock_shell",
            () -> new Item(new Item.Properties()
                    .fireResistant()));

    // 镀金碎片
    public static final RegistryObject<Item> GILDED_FRAGMENT = registerWithTab("gilded_fragment",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    // 凋零骨头
    public static final RegistryObject<Item> WITHER_RIB = registerWithTab("wither_rib",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    // 星之尘
    public static final RegistryObject<Item> STAR_DUST = registerWithTab("star_dust",
            () -> new StarDustItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 烈焰珍珠
    public static final RegistryObject<Item> BLAZE_HEART = registerWithTab("blaze_heart",
            () -> new ThrowableFuelItem(new Item.Properties()
                    .rarity(Rarity.COMMON)
                    .stacksTo(16),
                    3000));

    // 疣猪兽皮
    public static final RegistryObject<Item> HOGLIN_HIDE = registerWithTab("hoglin_hide",
            () -> new Item(new Item.Properties()));

    // 吹箭筒
    public static final RegistryObject<Item> BLOWGUN = ITEMS.register("blowgun",
            () -> new BlowgunItem(new Item.Properties()
                    .durability(384)
                    .rarity(Rarity.UNCOMMON)));

    // 原始砍刀
    public static final RegistryObject<Item> PRIMITIVE_MACHETE = registerWithTab("primitive_machete",
            () -> new KitchenKnifeItem(
                    new PrimitiveMacheteTier(),
                    new Item.Properties()
                            .durability(2031)
                            //.rarity(Rarity.UNCOMMON)
            ));

    // 恶魂挂坠
    public static final RegistryObject<Item> GHAST_PENDANT = registerWithTab("ghast_pendant",
            () -> new GhastPendantItem());

    // 烈焰永恒牛排
    public static final RegistryObject<Item> EVERLASTING_FLAME_STEAK = registerWithTab("everlasting_flame_steak",
            () -> new EverlastingFoodItem(KNFoods.EVERLASTING_FLAME_STEAK,
                    Config.EVERLASTING_FLAME_STEAK_COOLDOWN::get,
                    Config.EVERLASTING_FLAME_STEAK_ENABLED::get,
                    Rarity.EPIC
            ));

    // 绯红果
    public static final RegistryObject<Item> CRIMSON_FRUIT = registerWithTab("crimson_fruit",
            () -> new SpecialFruitBlockItem(
                    KNBlocks.WEEPING_CAVE_VINES.get(),
                    KNFoods.CRIMSON_FRUIT,
                    KNEffects.CRIMSON_BUFF,
                    30*20,
                    0,
                    Rarity.COMMON
            ));

    // 诡异果
    public static final RegistryObject<Item> WARPED_FRUIT = registerWithTab("warped_fruit",
            () -> new SpecialFruitBlockItem(
                    KNBlocks.TWISTING_CAVE_VINES.get(),
                    KNFoods.WARPED_FRUIT,
                    KNEffects.WARPED_BUFF,
                    30*20,
                    0,
                    Rarity.COMMON
            ));

    // 下界猪儿虫
    public static final RegistryObject<Item> NETHER_CATERPILLAR = registerWithTab("nether_caterpillar",
            () -> new NetherCaterpillarItem(KNFoods.NETHER_CATERPILLAR));

    // 回魂饭
    public static final RegistryObject<Item> SOUL_RETURN_RICE = registerWithTab("soul_return_rice",
            () -> new SoulReturnRiceItem(KNFoods.SOUL_RETURN_RICE));

    // 熔岩烤鸡
    public static final RegistryObject<Item> LAVA_ROASTED_CHICKEN = registerWithTab("lava_roasted_chicken",
            () -> new LavaRoastedChickenItem(KNFoods.LAVA_ROASTED_CHICKEN));

    // 星之炖菜
    public static final RegistryObject<Item> STAR_STEW = registerWithTab("star_stew",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_STEW));

    // 灵魂椒
    public static final RegistryObject<Item> SOUL_PEPPER = registerWithTab("soul_pepper",
            () -> new ChiliItem(4));

    // 灵魂浓汤
    public static final RegistryObject<Item> SOUL_SOUP = registerWithTab("soul_soup",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_SOUP));

    // 生炽足兽肉
    public static final RegistryObject<Item> RAW_STRIDER_MEAT = registerWithTab("raw_strider_meat",
            () -> new Item(foodItem(KNFoods.RAW_STRIDER_MEAT)));

    // 熟炽足兽肉
    public static final RegistryObject<Item> COOKED_STRIDER_MEAT = registerWithTab("cooked_strider_meat",
            () -> new Item(foodItem(KNFoods.COOKED_STRIDER_MEAT)));

    // 疣猪火腿
    public static final RegistryObject<Item> HAM = registerWithTab("ham",
            () -> new Item(foodItem(KNFoods.HAM)));

    // 火腿片
    public static final RegistryObject<Item> HAM_SLICE = registerWithTab("ham_slice",
            () -> new Item(foodItem(KNFoods.HAM_SLICE)));

    // 烤疣猪火腿
    public static final RegistryObject<Item> ROASTED_HAM = registerWithTab("roasted_ham",
            () -> new Item(foodItem(KNFoods.ROASTED_HAM)));

    // 肉夹馍
    public static final RegistryObject<Item> ROUJIAMO = registerWithTab("roujiamo",
            () -> new FoodWithEffectsItem(KNFoods.ROUJIAMO));

    // 红烧炽足兽
    public static final RegistryObject<Item> BRAISED_STRIDER = registerWithTab("braised_strider",
            () -> new BowlFoodOnlyItem(KNFoods.BRAISED_STRIDER));

    // 凋零大骨汤
    public static final RegistryObject<Item> WITHER_BONE_SOUP = registerWithTab("wither_bone_soup",
            () -> new BowlFoodOnlyItem(KNFoods.WITHER_BONE_SOUP));

    // 炽足兽炖下界疣
    public static final RegistryObject<Item> STRIDER_NETHER_WART_STEW = registerWithTab("strider_nether_wart_stew",
            () -> new BowlFoodOnlyItem(KNFoods.STRIDER_NETHER_WART_STEW));

    // 恶魂烤串
    public static final RegistryObject<Item> GHAST_KABOB = registerWithTab("ghast_kabob",
            () -> new StickReturnFoodItem(KNFoods.GHAST_KABOB));

    // 恶魂触手
    public static final RegistryObject<Item> GHAST_TENTACLE = registerWithTab("ghast_tentacle",
            () -> new Item(foodItem(KNFoods.GHAST_TENTACLE)));

    // 烤恶魂触手
    public static final RegistryObject<Item> ROASTED_GHAST_TENTACLE = registerWithTab("roasted_ghast_tentacle",
            () -> new Item(foodItem(KNFoods.ROASTED_GHAST_TENTACLE)));

    // 恶魂意面
    public static final RegistryObject<Item> GHAST_PASTA = registerWithTab("ghast_pasta",
            () -> new BowlFoodOnlyItem(KNFoods.GHAST_PASTA));

    // 岩浆膏浓汤
    public static final RegistryObject<Item> MAGMA_CREAM_SOUP = registerWithTab("magma_cream_soup",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_SOUP));

    // 岩浆膏布丁
    public static final RegistryObject<Item> MAGMA_CREAM_PUDDING = registerWithTab("magma_cream_pudding",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_PUDDING));

    // 剧毒果
    public static final RegistryObject<Item> POISONOUS_FRUIT = registerWithTab("poisonous_fruit",
            () -> new MysteriousPoisonFoodBlockItem(
                    KNBlocks.POISONOUS_FRUIT.get(),
                    KNFoods.POISONOUS_FRUIT,
                    30, // 30秒
                    0,
                    Rarity.COMMON
            ));

    // 剧毒浓汤
    public static final RegistryObject<Item> POISONOUS_SOUP = registerWithTab("poisonous_soup",
            () -> new BowlFoodOnlyItem(KNFoods.POISONOUS_SOUP));

    // 灵魂浇汁烤肉
    public static final RegistryObject<Item> SOUL_GLAZED_ROAST = registerWithTab("soul_glazed_roast",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_GLAZED_ROAST));

    // 恶魂布丁
    public static final RegistryObject<Item> GHAST_PUDDING = registerWithTab("ghast_pudding",
            () -> new BowlFoodOnlyItem(KNFoods.GHAST_PUDDING));

    // 野蛮烤肉
    public static final RegistryObject<Item> GILDED_BARBARIC_ROAST = registerWithTab("gilded_barbaric_roast",
            () -> new BowlFoodOnlyItem(KNFoods.GILDED_BARBARIC_ROAST));

    // 下界猪儿虫刺身
    public static final RegistryObject<Item> NETHER_CATERPILLAR_SASHIMI = registerWithTab("nether_caterpillar_sashimi",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_CATERPILLAR_SASHIMI));

    // 黄金烤肉
    public static final RegistryObject<Item> GOLDEN_ROAST = registerWithTab("golden_roast",
            () -> new BowlFoodOnlyItem(KNFoods.GOLDEN_ROAST));

    // 下界薯条拼盘
    public static final RegistryObject<Item> NETHER_FRIES_PLATTER = registerWithTab("nether_fries_platter",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_FRIES_PLATTER));

    // 疣猪兽獠牙焖肉
    public static final RegistryObject<Item> HOGLIN_TUSK_BRAISED_MEAT = registerWithTab("hoglin_tusk_braised_meat",
            () -> new FoodWithEffectsItem(KNFoods.HOGLIN_TUSK_BRAISED_MEAT));

    // 剧毒恶魂烤肉
    public static final RegistryObject<Item> POISONOUS_GHAST_ROAST = registerWithTab("poisonous_ghast_roast",
            () -> new BowlFoodOnlyItem(KNFoods.POISONOUS_GHAST_ROAST));

    // 灵魂椒炒肉
    public static final RegistryObject<Item> SOUL_PEPPER_STIR_FRY = registerWithTab("soul_pepper_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_PEPPER_STIR_FRY));

    // 炽足兽岩壳炒肉
    public static final RegistryObject<Item> STRIDER_SHELL_STIR_FRY = registerWithTab("strider_shell_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.STRIDER_SHELL_STIR_FRY));

    // 下界果切拼盘
    public static final RegistryObject<Item> FRUIT_PLATTER = registerWithTab("fruit_platter",
            () -> new BowlFoodOnlyItem(KNFoods.FRUIT_PLATTER));

    // 火腿酸酪
    public static final RegistryObject<Item> HAM_YOGURT = registerWithTab("ham_yogurt",
            () -> new BowlFoodOnlyItem(KNFoods.HAM_YOGURT));

    // 酸菜鱼
    public static final RegistryObject<Item> SAUERKRAUT_FISH = registerWithTab("sauerkraut_fish",
            () -> new BowlFoodOnlyItem(KNFoods.SAUERKRAUT_FISH));

    // 烈焰浓汤
    public static final RegistryObject<Item> BLAZE_SOUP = registerWithTab("blaze_soup",
            () -> new BowlFoodOnlyItem(KNFoods.BLAZE_SOUP));

    // 熔岩果冻
    public static final RegistryObject<Item> LAVA_JELLY = registerWithTab("lava_jelly",
            () -> new BowlFoodOnlyItem(KNFoods.LAVA_JELLY));

    // 绯红沙拉
    public static final RegistryObject<Item> CRIMSON_SALAD = registerWithTab("crimson_salad",
            () -> new BowlFoodOnlyItem(KNFoods.CRIMSON_SALAD));

    // 绯红菌岩浆膏炖肉
    public static final RegistryObject<Item> CRIMSON_MAGMA_STEW = registerWithTab("crimson_magma_stew",
            () -> new BowlFoodOnlyItem(KNFoods.CRIMSON_MAGMA_STEW));

    // 诡异沙拉
    public static final RegistryObject<Item> WARPED_SALAD = registerWithTab("warped_salad",
            () -> new BowlFoodOnlyItem(KNFoods.WARPED_SALAD));

    // 灵魂炽足兽烤串
    public static final RegistryObject<Item> SOUL_STRIDER_KABOB = registerWithTab("soul_strider_kabob",
            () -> new StickReturnFoodItem(KNFoods.SOUL_STRIDER_KABOB));

    // 黄金烤串
    public static final RegistryObject<Item> GOLDEN_KABOB = registerWithTab("golden_kabob",
            () -> new StickReturnFoodItem(KNFoods.GOLDEN_KABOB));

    // 烈焰烤串
    public static final RegistryObject<Item> BLAZING_KABOB = registerWithTab("blazing_kabob",
            () -> new StickReturnFoodItem(KNFoods.BLAZING_KABOB));

    // 绯红烤串
    public static final RegistryObject<Item> CRIMSON_KABOB = registerWithTab("crimson_kabob",
            () -> new StickReturnFoodItem(KNFoods.CRIMSON_KABOB));

    // 诡异烤串
    public static final RegistryObject<Item> WARPED_KABOB = registerWithTab("warped_kabob",
            () -> new StickReturnFoodItem(KNFoods.WARPED_KABOB));

    // 星之恶魂意面
    public static final RegistryObject<Item> STAR_GHAST_PASTA = registerWithTab("star_ghast_pasta",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_GHAST_PASTA));

    // 星之炖肉
    public static final RegistryObject<Item> STAR_STEW_MEAT = registerWithTab("star_stew_meat",
            () -> new BowlFoodOnlyItem(KNFoods.STAR_STEW_MEAT));

    // 荧光浓汤
    public static final RegistryObject<Item> GLOWING_SOUP = registerWithTab("glowing_soup",
            () -> new BowlFoodOnlyItem(KNFoods.GLOWING_SOUP));

    // 荧光布丁
    public static final RegistryObject<Item> GLOWING_PUDDING = registerWithTab("glowing_pudding",
            () -> new BowlFoodOnlyItem(KNFoods.GLOWING_PUDDING));

    // 荧光烤串
    public static final RegistryObject<Item> GLOWING_KABOB = registerWithTab("glowing_kabob",
            () -> new StickReturnFoodItem(KNFoods.GLOWING_KABOB));

    // 荧光沙拉
    public static final RegistryObject<Item> GLOWING_SALAD = registerWithTab("glowing_salad",
            () -> new BowlFoodOnlyItem(KNFoods.GLOWING_SALAD));

    // 黑苹果沙拉
    public static final RegistryObject<Item> BLACK_APPLE_SALAD = registerWithTab("black_apple_salad",
            () -> new BowlFoodOnlyItem(KNFoods.BLACK_APPLE_SALAD));

    // 红宝石牛排
    public static final RegistryObject<Item> RUBY_STEAK = registerWithTab("ruby_steak",
            () -> new BowlFoodOnlyItem(KNFoods.RUBY_STEAK));

    // 下界芦苇炖菜
    public static final RegistryObject<Item> NETHER_REED_STEW = registerWithTab("nether_reed_stew",
            () -> new BowlFoodOnlyItem(KNFoods.NETHER_REED_STEW));

    // 岩浆膏炒肉
    public static final RegistryObject<Item> MAGMA_CREAM_STIR_FRY = registerWithTab("magma_cream_stir_fry",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY));

    // 岩浆膏炒肉盖饭
    public static final RegistryObject<Item> MAGMA_CREAM_STIR_FRY_RICE = registerWithTab("magma_cream_stir_fry_rice",
            () -> new BowlFoodOnlyItem(KNFoods.MAGMA_CREAM_STIR_FRY_RICE));

    // 麻婆豆腐
    public static final RegistryObject<Item> MAPO_TOFU = registerWithTab("mapo_tofu",
            () -> new BowlFoodOnlyItem(KNFoods.MAPO_TOFU));

    // 麻婆豆腐盖饭
    public static final RegistryObject<Item> MAPO_TOFU_RICE = registerWithTab("mapo_tofu_rice",
            () -> new BowlFoodOnlyItem(KNFoods.MAPO_TOFU_RICE));

    // 诡异蛋糕
    public static final RegistryObject<Item> WARPED_CAKE = registerWithTab("warped_cake",
            () -> new FoodWithEffectsItem(KNFoods.WARPED_CAKE));

    // 重庆小面
    public static final RegistryObject<Item> CHONGQING_NOODLES = registerWithTab("chongqing_noodles",
            () -> new BowlFoodOnlyItem(KNFoods.CHONGQING_NOODLES));

    // 螺蛳粉
    public static final RegistryObject<Item> LUOSIFEN = registerWithTab("luosifen",
            () -> new BowlFoodOnlyItem(KNFoods.LUOSIFEN));

    // 灵魂炒肉
    public static final RegistryObject<Item> SOUL_STIR_FRY_MEAT = registerWithTab("soul_stir_fry_meat",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT));

    // 灵魂炒肉盖饭
    public static final RegistryObject<Item> SOUL_STIR_FRY_MEAT_RICE = registerWithTab("soul_stir_fry_meat_rice",
            () -> new BowlFoodOnlyItem(KNFoods.SOUL_STIR_FRY_MEAT_RICE));

    // 焦糖下界猪儿虫
    public static final RegistryObject<Item> CARAMEL_NETHER_CATERPILLAR = registerWithTab("caramel_nether_caterpillar",
            () -> new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR));

    // 焦糖下界猪儿虫盖饭
    public static final RegistryObject<Item> CARAMEL_NETHER_CATERPILLAR_RICE = registerWithTab("caramel_nether_caterpillar_rice",
            () -> new BowlFoodOnlyItem(KNFoods.CARAMEL_NETHER_CATERPILLAR_RICE));

    // 生猪灵肉
    public static final RegistryObject<Item> RAW_PIGLIN_MEAT = registerWithTab("raw_piglin_meat",
            () -> new Item(foodItem(KNFoods.RAW_PIGLIN_MEAT)));

    // 熟猪灵肉
    public static final RegistryObject<Item> COOKED_PIGLIN_MEAT = registerWithTab("cooked_piglin_meat",
            () -> new Item(foodItem(KNFoods.COOKED_PIGLIN_MEAT)));

    // 诡异霉烂肉
    public static final RegistryObject<Item> WARPED_HOGLIN_TENDERLOIN_STEW = registerWithTab("warped_hoglin_tenderloin_stew",
            () -> new BowlFoodOnlyItem(KNFoods.WARPED_HOGLIN_TENDERLOIN_STEW));

    // 麻辣疣猪兽拉面
    public static final RegistryObject<Item> SPICY_HOGLIN_RAMEN = registerWithTab("spicy_hoglin_ramen",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_HOGLIN_RAMEN));

    // 麻辣香锅
    public static final RegistryObject<Item> SPICY_POT = registerWithTab("spicy_pot",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_POT));

    // 麻辣香锅盖饭
    public static final RegistryObject<Item> SPICY_POT_RICE = registerWithTab("spicy_pot_rice",
            () -> new BowlFoodOnlyItem(KNFoods.SPICY_POT_RICE));

    // 巨兽牛角包
    public static final RegistryObject<Item> GIANT_BEAST_CROISSANT = registerWithTab("giant_beast_croissant",
            () -> new CataclysmBuffFoodItem(KNFoods.GIANT_BEAST_CROISSANT, 240));

    // 魔眼咕噜肉
    public static final RegistryObject<Item> MAGMA_SWEET_AND_SOUR_PORK = registerWithTab("magma_sweet_and_sour_pork",
            () -> new MagmaSweetAndSourPorkItem(KNFoods.MAGMA_SWEET_AND_SOUR_PORK));

    // 孟婆汤
    public static final RegistryObject<Item> FORGETFULNESS_SOUP = registerWithTab("forgetfulness_soup",
            () -> new ForgetfulnessSoupItem(KNFoods.FORGETFULNESS_SOUP));

    // 红烧狮子头
    public static final RegistryObject<Item> BRAISED_LION_HEAD = registerWithTab("braised_lion_head",
            () -> new BowlFoodOnlyItem(KNFoods.BRAISED_LION_HEAD));

    // 蒜蓉生蚝
    public static final RegistryObject<Item> GARLIC_OYSTERS = registerWithTab("garlic_oysters",
            () -> new BowlFoodOnlyItem(KNFoods.GARLIC_OYSTERS));

    // 夫妻肺片
    public static final RegistryObject<Item> COUPLES_LUNG_SLICE = registerWithTab("couples_lung_slice",
            () -> new BowlFoodOnlyItem(KNFoods.COUPLES_LUNG_SLICE));

    // 卤肉饭
    public static final RegistryObject<Item> BRAISED_PORK_RICE = registerWithTab("braised_pork_rice",
            () -> new BowlFoodOnlyItem(KNFoods.BRAISED_PORK_RICE));

    // 胡椒猪肚鸡汤
    public static final RegistryObject<Item> PEPPER_PORK_BELLY_CHICKEN_SOUP = registerWithTab("pepper_pork_belly_chicken_soup",
            () -> new BowlFoodOnlyItem(KNFoods.PEPPER_PORK_BELLY_CHICKEN_SOUP));

    // 玉米胡萝卜排骨汤
    public static final RegistryObject<Item> CORN_CARROT_PORK_RIB_SOUP = registerWithTab("corn_carrot_pork_rib_soup",
            () -> new BowlFoodOnlyItem(KNFoods.CORN_CARROT_PORK_RIB_SOUP));

    // 广式肠粉
    public static final RegistryObject<Item> CANTONESE_RICE_NOODLE_ROLL = registerWithTab("cantonese_rice_noodle_roll",
            () -> new BowlFoodOnlyItem(KNFoods.CANTONESE_RICE_NOODLE_ROLL));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}