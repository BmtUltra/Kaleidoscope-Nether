package com.bmt.kaleidoscope_nether.registry;

import com.bmt.kaleidoscope_nether.Config;
import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.bmt.kaleidoscope_nether.item.*;
import com.google.common.collect.Sets;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TippedArrowItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

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

    public static final RegistryObject<Item> GHAST_HIDE = registerWithTab("ghast_hide",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 疣猪兽獠牙
    public static final RegistryObject<Item> HOGLIN_TUSK = registerWithTab("hoglin_tusk",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> STRIDER_ROCK_SHELL = registerWithTab("strider_rock_shell",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant()));

    public static final RegistryObject<Item> GILDED_FRAGMENT = registerWithTab("gilded_fragment",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> WITHER_RIB = registerWithTab("wither_rib",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 星之尘
    public static final RegistryObject<Item> STAR_DUST = registerWithTab("star_dust",
            () -> new StarDustItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 碎金块
    public static final RegistryObject<Item> GOLDEN_PLATE = registerWithTab("golden_plate",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // 烈焰珍珠
    public static final RegistryObject<Item> BLAZE_HEART = registerWithTab("blaze_heart",
            () -> new ThrowableFuelItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .stacksTo(16),
                    3000));

    // 熔岩泡泡
    public static final RegistryObject<Item> MAGMA_BUBBLE = registerWithTab("magma_bubble",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SEED_BAG = registerWithTab("seed_bag",
            () -> new SeedBagItem(new Item.Properties().rarity(Rarity.COMMON)));

    // 吹箭筒
    public static final RegistryObject<Item> BLOWGUN = ITEMS.register("blowgun",
            () -> new BlowgunItem(new Item.Properties()
                    .durability(384)
                    .rarity(Rarity.UNCOMMON)));

    //药水箭
    public static final RegistryObject<Item> MYSTERIOUS_POISON_ARROW = ITEMS.register("mysterious_poison_arrow",
            () -> new TippedArrowItem(new Item.Properties()
                    .stacksTo(64)) {
                @Override
                public @NotNull ItemStack getDefaultInstance() {
                    ItemStack stack = new ItemStack(this);
                    PotionUtils.setPotion(stack, KNPotions.MYSTERIOUS_POISON.get());
                    return stack;
                }
            });

    public static final RegistryObject<Item> EVERLASTING_FLAME_STEAK = registerWithTab("everlasting_flame_steak",
            () -> new EverlastingFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(1.2f)
                            .build(),
                    Config.EVERLASTING_FLAME_STEAK_COOLDOWN::get,
                    Config.EVERLASTING_FLAME_STEAK_ENABLED::get,
                    Rarity.EPIC
            ));

    public static final RegistryObject<Item> CRIMSON_FRUIT = registerWithTab("crimson_fruit",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .build(),
                    KNEffects.CRIMSON_BUFF,
                    600, //30s
                    0,
                    Rarity.COMMON
            ));

    public static final RegistryObject<Item> WARPED_FRUIT = registerWithTab("warped_fruit",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .build(),
                    KNEffects.WARPED_BUFF,
                    600, // 30秒
                    0,
                    Rarity.COMMON
            ));

    public static final RegistryObject<Item> NETHER_CATERPILLAR = registerWithTab("nether_caterpillar",
            () -> new NetherCaterpillarItem(
                    new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.1f)
                            .build()
            ));

    public static final RegistryObject<Item> SOUL_RETURN_RICE = registerWithTab("soul_return_rice",
            () -> new SoulReturnRiceItem(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.7f)
                            .build()
            ));

    public static final RegistryObject<Item> LAVA_ROASTED_CHICKEN = registerWithTab("lava_roasted_chicken",
            () -> new LavaRoastedChickenItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.9f)
                            .meat()
                            .build()
            ));

    public static final RegistryObject<Item> STAR_STEW = registerWithTab("star_stew",
            () -> new StarStewItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.8f)
                            .build()
            ));

    // 灵魂椒
    public static final RegistryObject<Item> SOUL_PEPPER = registerWithTab("soul_pepper",
            () -> new SoulPepperItem(
                    new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.0f)
                            .build(),
                    15 // 15秒持续时间
            ));

    public static final RegistryObject<Item> SOUL_SOUP = registerWithTab("soul_soup",
            () -> new GhostBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.7f)
                            .build(),
                    30 // 30秒持续时间
            ));

    public static final RegistryObject<Item> RAW_STRIDER_MEAT = registerWithTab("raw_strider_meat",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.3f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> COOKED_STRIDER_MEAT = registerWithTab("cooked_strider_meat",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.8f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> HAM = registerWithTab("ham",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.5f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> HAM_SLICE = registerWithTab("ham_slice",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.2f)
                    .meat()
                    .build())));

    // 烤火腿
    public static final RegistryObject<Item> ROASTED_HAM = registerWithTab("roasted_ham",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.8f)
                    .meat()
                    .build())));


    public static final RegistryObject<Item> ROUJIAMO = registerWithTab("roujiamo",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(9)
                    .saturationMod(0.7f)
                    .build())));

    public static final RegistryObject<Item> BRAISED_STRIDER = registerWithTab("braised_strider",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.6f)
                    .build())));

    public static final RegistryObject<Item> RAW_GHAST_MEAT = registerWithTab("raw_ghast_meat",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.3f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> COOKED_GHAST_MEAT = registerWithTab("cooked_ghast_meat",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.8f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> WITHER_BONE_SOUP = registerWithTab("wither_bone_soup",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.9f)
                    .build())));

    public static final RegistryObject<Item> STRIDER_NETHER_WART_STEW = registerWithTab("strider_nether_wart_stew",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(11)
                    .saturationMod(0.8f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> GHAST_KABOB = registerWithTab("ghast_kabob",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.3f)
                    .meat()
                    .build())));


    public static final RegistryObject<Item> GHAST_TENTACLE = registerWithTab("ghast_tentacle",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.3f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> ROASTED_GHAST_TENTACLE = registerWithTab("roasted_ghast_tentacle",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.7f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> GHAST_PASTA = registerWithTab("ghast_pasta",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(0.6f)
                    .meat()
                    .build())));

    public static final RegistryObject<Item> MAGMA_CREAM_SOUP = registerWithTab("magma_cream_soup",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.6f)
                    .build())));

    public static final RegistryObject<Item> MAGMA_CREAM_PUDDING = registerWithTab("magma_cream_pudding",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(0.5f)
                    .build())));

    // 剧毒果
    public static final RegistryObject<Item> POISONOUS_FRUIT = registerWithTab("poisonous_fruit",
            () -> new MysteriousPoisonFoodBlockItem(
                    KNBlocks.POISONOUS_FRUIT.get(),
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.1f)
                            .alwaysEat()
                            .build(),
                    30, // 30秒
                    0,
                    Rarity.COMMON
            ));

    // 剧毒浓汤
    public static final RegistryObject<Item> POISONOUS_SOUP = registerWithTab("poisonous_soup",
            () -> new MysteriousPoisonFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.6f)
                            .alwaysEat()
                            .build(),
                    45, // 45秒
                    0,
                    Rarity.COMMON
            ));

    // 灵魂浇汁烤肉
    public static final RegistryObject<Item> SOUL_GLAZED_ROAST = registerWithTab("soul_glazed_roast",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.8f)
                    .meat()
                    .build())));

    // 恶魂布丁
    public static final RegistryObject<Item> GHAST_PUDDING = registerWithTab("ghast_pudding",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.5f)
                    .build())));


    // 镀金野蛮烤肉
    public static final RegistryObject<Item> GILDED_BARBARIC_ROAST = registerWithTab("gilded_barbaric_roast",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(16)
                    .saturationMod(1.4f)
                    .meat()
                    .build())));

    // 下界猪儿虫刺身
    public static final RegistryObject<Item> NETHER_CATERPILLAR_SASHIMI = registerWithTab("nether_caterpillar_sashimi",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.3f)
                    .build())));

    // 黄金烤肉
    public static final RegistryObject<Item> GOLDEN_ROAST = registerWithTab("golden_roast",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(15)
                    .saturationMod(1.1f)
                    .meat()
                    .build())));

    // 下界薯条拼盘
    public static final RegistryObject<Item> NETHER_FRIES_PLATTER = registerWithTab("nether_fries_platter",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationMod(0.9f)
                    .build())));

    // 疣猪兽獠牙焖肉
    public static final RegistryObject<Item> HOGLIN_TUSK_BRAISED_MEAT = registerWithTab("hoglin_tusk_braised_meat",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(11)
                    .saturationMod(0.9f)
                    .meat()
                    .build())));

    // 剧毒恶魂烤肉
    public static final RegistryObject<Item> POISONOUS_GHAST_ROAST = registerWithTab("poisonous_ghast_roast",
            () -> new MysteriousPoisonFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(0.8f)
                            .meat()
                            .alwaysEat()
                            .build(),
                    60, // 60秒
                    1,  // 2级效果
                    Rarity.COMMON
            ));

    // 灵魂椒炒肉
    public static final RegistryObject<Item> SOUL_PEPPER_STIR_FRY = registerWithTab("soul_pepper_stir_fry",
            () -> new GhostBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.7f)
                            .meat()
                            .build(),
                    45 // 45秒持续时间
            ));

    // 炽足兽岩壳炒肉
    public static final RegistryObject<Item> STRIDER_SHELL_STIR_FRY = registerWithTab("strider_shell_stir_fry",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.9f)
                    .meat()
                    .build())));

    // 四色果切拼盘
    public static final RegistryObject<Item> FRUIT_PLATTER = registerWithTab("fruit_platter",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.7f)
                    .build())));

    // 火腿酸酪
    public static final RegistryObject<Item> HAM_YOGURT = registerWithTab("ham_yogurt",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(0.6f)
                    .build())));

//    public static final RegistryObject<Item> SOUL_LAMB_CHOP = registerWithTab("soul_lamb_chop",
//            () -> new BowlFoodBlockItem(KNBlocks.SOUL_LAMB_CHOP_BLOCK.get(), KNFoods.SOUL_LAMB_CHOP_ITEM));

    public static final RegistryObject<Item> SAUERKRAUT_FISH = registerWithTab("sauerkraut_fish",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.8f)
                    .build())));

    // 烈焰浓汤
    public static final RegistryObject<Item> BLAZE_SOUP = registerWithTab("blaze_soup",
            () -> new BlazingBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(0.6f)
                            .build(),
                    120 // 30秒持续时间
            ));

    // 熔岩果冻
    public static final RegistryObject<Item> LAVA_JELLY = registerWithTab("lava_jelly",
            () -> new BlazingBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.3f)
                            .build(),
                    120 // 15秒持续时间
            ));

    // 绯红沙拉
    public static final RegistryObject<Item> CRIMSON_SALAD = registerWithTab("crimson_salad",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.5f)
                            .build(),
                    KNEffects.CRIMSON_BUFF,
                    900, // 45秒
                    0,
                    Rarity.COMMON
            ));

    // 绯红菌岩浆膏炖肉
    public static final RegistryObject<Item> CRIMSON_MAGMA_STEW = registerWithTab("crimson_magma_stew",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationMod(0.8f)
                            .meat()
                            .build(),
                    KNEffects.CRIMSON_BUFF,
                    1200, // 60秒
                    0,
                    Rarity.COMMON
            ));

    // 诡异沙拉
    public static final RegistryObject<Item> WARPED_SALAD = registerWithTab("warped_salad",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.5f)
                            .build(),
                    KNEffects.WARPED_BUFF,
                    900, // 45秒
                    0,
                    Rarity.COMMON
            ));


    // 灵魂炽足兽烤串
    public static final RegistryObject<Item> SOUL_STRIDER_KABOB = registerWithTab("soul_strider_kabob",
            () -> new GhostBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.4f)
                            .meat()
                            .build(),
                    60 // 60秒持续时间
            ));

    // 黄金烤串
    public static final RegistryObject<Item> GOLDEN_KABOB = registerWithTab("golden_kabob",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.5f)
                    .meat()
                    .build())));

    // 烈焰烤串
    public static final RegistryObject<Item> BLAZING_KABOB = registerWithTab("blazing_kabob",
            () -> new BlazingBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.3f)
                            .meat()
                            .build(),
                    90 // 90秒持续时间
            ));

    // 绯红烤串
    public static final RegistryObject<Item> CRIMSON_KABOB = registerWithTab("crimson_kabob",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.2f)
                            .meat()
                            .build(),
                    KNEffects.CRIMSON_BUFF,
                    1200, // 60秒
                    0,
                    Rarity.COMMON
            ));

    // 诡异烤串
    public static final RegistryObject<Item> WARPED_KABOB = registerWithTab("warped_kabob",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.2f)
                            .meat()
                            .build(),
                    KNEffects.WARPED_BUFF,
                    1200, // 60秒
                    0,
                    Rarity.COMMON
            ));

    // 星之恶魂意面
    public static final RegistryObject<Item> STAR_GHAST_PASTA = registerWithTab("star_ghast_pasta",
            () -> new StarStewItem(
                    new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationMod(0.7f)
                            .meat()
                            .build()
            ));

    // 星之炖肉
    public static final RegistryObject<Item> STAR_STEW_MEAT = registerWithTab("star_stew_meat",
            () -> new StarStewItem(
                    new FoodProperties.Builder()
                            .nutrition(11)
                            .saturationMod(0.6f)
                            .meat()
                            .build()
            ));


    // 荧光浓汤
    public static final RegistryObject<Item> GLOWING_SOUP = registerWithTab("glowing_soup",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.5f)
                            .alwaysEat()
                            .build(),
                    KNEffects.GLOWING_BUFF,
                    1200, // 60秒
                    0,
                    Rarity.COMMON
            ));

    // 荧光布丁
    public static final RegistryObject<Item> GLOWING_PUDDING = registerWithTab("glowing_pudding",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.5f)
                            .alwaysEat()
                            .build(),
                    KNEffects.GLOWING_BUFF,
                    900, // 45秒
                    0,
                    Rarity.COMMON
            ));

    // 荧光烤串
    public static final RegistryObject<Item> GLOWING_KABOB = registerWithTab("glowing_kabob",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(0.3f)
                            .meat()
                            .alwaysEat()
                            .build(),
                    KNEffects.GLOWING_BUFF,
                    1800, // 90秒
                    0,
                    Rarity.COMMON
            ));

    // 荧光沙拉
    public static final RegistryObject<Item> GLOWING_SALAD = registerWithTab("glowing_salad",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.6f)
                            .alwaysEat()
                            .build(),
                    KNEffects.GLOWING_BUFF,
                    900, // 45秒
                    0,
                    Rarity.COMMON
            ));

    // 黑苹果沙拉
    public static final RegistryObject<Item> BLACK_APPLE_SALAD = registerWithTab("black_apple_salad",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(0.5f)
                    .alwaysEat()
                    .build())));

    // 红宝石牛排
    public static final RegistryObject<Item> RUBY_STEAK = registerWithTab("ruby_steak",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(11)
                    .saturationMod(1.0f)
                    .meat()
                    .alwaysEat()
                    .build())));

    // 下界芦苇炖菜
    public static final RegistryObject<Item> NETHER_REED_STEW = registerWithTab("nether_reed_stew",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.5f)
                    .alwaysEat()
                    .build())));

    // 岩浆膏炒肉
    public static final RegistryObject<Item> MAGMA_CREAM_STIR_FRY = registerWithTab("magma_cream_stir_fry",
            () -> new BlazingBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.7f)
                            .meat()
                            .build(),
                    60 // 60秒持续时间
            ));

    // 岩浆膏炒肉盖饭
    public static final RegistryObject<Item> MAGMA_CREAM_STIR_FRY_RICE = registerWithTab("magma_cream_stir_fry_rice",
            () -> new BlazingBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(0.8f)
                            .meat()
                            .build(),
                    90 // 90秒持续时间
            ));

    // 麻婆豆腐
    public static final RegistryObject<Item> MAPO_TOFU = registerWithTab("mapo_tofu",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.6f)
                    .build())));

    // 麻婆豆腐盖饭
    public static final RegistryObject<Item> MAPO_TOFU_RICE = registerWithTab("mapo_tofu_rice",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationMod(0.9f)
                    .build())));

    // 诡异蛋糕
    public static final RegistryObject<Item> WARPED_CAKE = registerWithTab("warped_cake",
            () -> new SpecialFruitItem(
                    new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build(),
                    KNEffects.WARPED_BUFF,
                    1200, // 60秒
                    0,
                    Rarity.COMMON
            ));

    // 重庆小面
    public static final RegistryObject<Item> CHONGQING_NOODLES = registerWithTab("chongqing_noodles",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.7f)
                    .build())));

    // 螺蛳粉
    public static final RegistryObject<Item> LUOSIFEN = registerWithTab("luosifen",
            () -> new Item(foodItem(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(0.8f)
                    .build())));

    // 灵魂炒肉
    public static final RegistryObject<Item> SOUL_STIR_FRY_MEAT = registerWithTab("soul_stir_fry_meat",
            () -> new GhostBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.7f)
                            .meat()
                            .build(),
                    60 // 60秒持续时间
            ));

    // 灵魂炒肉盖饭
    public static final RegistryObject<Item> SOUL_STIR_FRY_MEAT_RICE = registerWithTab("soul_stir_fry_meat_rice",
            () -> new GhostBuffFoodItem(
                    new FoodProperties.Builder()
                            .nutrition(14)
                            .saturationMod(1.0f)
                            .meat()
                            .build(),
                    90 // 90秒持续时间
            ));

}