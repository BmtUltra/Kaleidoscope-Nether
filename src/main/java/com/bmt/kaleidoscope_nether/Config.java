package com.bmt.kaleidoscope_nether;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = KaleidoscopeNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue STAR_DUST_REPAIR_ENABLED;
    public static final ForgeConfigSpec.IntValue STAR_DUST_REPAIR_PERCENTAGE;
    public static final ForgeConfigSpec.IntValue STAR_DUST_REPAIR_FLAT;

    public static final ForgeConfigSpec.BooleanValue EVERLASTING_FLAME_STEAK_ENABLED;
    public static final ForgeConfigSpec.IntValue EVERLASTING_FLAME_STEAK_COOLDOWN;

    public static final ForgeConfigSpec.BooleanValue NETHER_CATERPILLAR_ENABLED;
    public static final ForgeConfigSpec.DoubleValue NETHER_CATERPILLAR_DROP_CHANCE;

    public static final ForgeConfigSpec.IntValue STAR_BLESSING_LEVEL_3_ARMOR_BONUS;
    public static final ForgeConfigSpec.IntValue STAR_BLESSING_LEVEL_6_TOUGHNESS_BONUS;
    public static final ForgeConfigSpec.IntValue STAR_BLESSING_LEVEL_9_KNOCKBACK_RESISTANCE;
    public static final ForgeConfigSpec.BooleanValue STAR_BLESSING_LEVEL_12_FIRE_IMMUNITY;

    public static final ForgeConfigSpec.DoubleValue GHAST_RAW_MEAT_CHANCE;
    public static final ForgeConfigSpec.DoubleValue GHAST_HIDE_CHANCE;
    public static final ForgeConfigSpec.DoubleValue GHAST_TENTACLE_CHANCE;
    public static final ForgeConfigSpec.DoubleValue HOGLIN_TUSK_CHANCE;
    public static final ForgeConfigSpec.DoubleValue HOGLIN_HAM_CHANCE;
    public static final ForgeConfigSpec.DoubleValue WITHER_RIB_CHANCE;
    public static final ForgeConfigSpec.DoubleValue STRIDER_RAW_MEAT_CHANCE;
    public static final ForgeConfigSpec.IntValue STRIDER_RAW_MEAT_MIN_COUNT;
    public static final ForgeConfigSpec.IntValue STRIDER_RAW_MEAT_MAX_COUNT;
    public static final ForgeConfigSpec.DoubleValue STRIDER_ROCK_SHELL_CHANCE;
    public static final ForgeConfigSpec.DoubleValue MAGMA_BUBBLE_CHANCE;
    public static final ForgeConfigSpec.DoubleValue BLAZE_HEART_CHANCE;
    public static final ForgeConfigSpec.DoubleValue PIGLIN_GOLDEN_PLATE_CHANCE;
    public static final ForgeConfigSpec.DoubleValue PIGLIN_BRUTE_GILDED_FRAGMENT_CHANCE;

    static {
        BUILDER.push("star_dust_repair");

        STAR_DUST_REPAIR_ENABLED = BUILDER
                .comment("Whether star dust repair functionality is enabled")
                .define("enabled", true);

        STAR_DUST_REPAIR_PERCENTAGE = BUILDER
                .comment("Star dust repair percentage (e.g., 25 means 25%)")
                .defineInRange("percentage", 25, 1, 100);

        STAR_DUST_REPAIR_FLAT = BUILDER
                .comment("Star dust flat repair value (additional durability points repaired)")
                .defineInRange("flat", 150, 1, 1000);

        BUILDER.pop();

        BUILDER.push("everlasting_flame_steak");

        EVERLASTING_FLAME_STEAK_ENABLED = BUILDER
                .comment("Whether the Everlasting Flame Steak is enabled")
                .define("enabled", true);

        EVERLASTING_FLAME_STEAK_COOLDOWN = BUILDER
                .comment("Cooldown in ticks for Everlasting Flame Steak (20 ticks = 1 second)")
                .defineInRange("cooldown", 300, 0, 1200);

        BUILDER.pop();

        BUILDER.push("drops");

        NETHER_CATERPILLAR_ENABLED = BUILDER
                .comment("Whether Nether Caterpillar drops are enabled")
                .define("netherCaterpillarEnabled", true);

        NETHER_CATERPILLAR_DROP_CHANCE = BUILDER
                .comment("Drop chance for Nether Caterpillar from Nether Wart (0.0 to 1.0)")
                .defineInRange("netherCaterpillarDropChance", 0.1, 0.0, 1.0);

        GHAST_RAW_MEAT_CHANCE = BUILDER
                .comment("Chance for Ghast to drop raw ghast meat (0.0 to 1.0)")
                .defineInRange("ghastRawMeatChance", 0.3, 0.0, 1.0);

        GHAST_HIDE_CHANCE = BUILDER
                .comment("Chance for Ghast to drop ghast hide (0.0 to 1.0)")
                .defineInRange("ghastHideChance", 0.1, 0.0, 1.0);

        GHAST_TENTACLE_CHANCE = BUILDER
                .comment("Chance for Ghast to drop ghast tentacle (0.0 to 1.0)")
                .defineInRange("ghastTentacleChance", 0.2, 0.0, 1.0);

        HOGLIN_TUSK_CHANCE = BUILDER
                .comment("Chance for adult Hoglin to drop hoglin tusk (0.0 to 1.0)")
                .defineInRange("hoglinTuskChance", 0.15, 0.0, 1.0);

        HOGLIN_HAM_CHANCE = BUILDER
                .comment("Chance for adult Hoglin to drop ham (0.0 to 1.0)")
                .defineInRange("hoglinHamChance", 0.25, 0.0, 1.0);

        WITHER_RIB_CHANCE = BUILDER
                .comment("Chance for Wither Skeleton to drop wither rib (0.0 to 1.0)")
                .defineInRange("witherRibChance", 0.2, 0.0, 1.0);

        STRIDER_RAW_MEAT_CHANCE = BUILDER
                .comment("Chance for adult Strider to drop raw strider meat (0.0 to 1.0)")
                .defineInRange("striderRawMeatChance", 0.4, 0.0, 1.0);

        STRIDER_RAW_MEAT_MIN_COUNT = BUILDER
                .comment("Minimum count of raw strider meat dropped")
                .defineInRange("striderRawMeatMinCount", 1, 1, 64);

        STRIDER_RAW_MEAT_MAX_COUNT = BUILDER
                .comment("Maximum count of raw strider meat dropped")
                .defineInRange("striderRawMeatMaxCount", 2, 1, 64);

        STRIDER_ROCK_SHELL_CHANCE = BUILDER
                .comment("Chance for adult Strider to drop strider rock shell (0.0 to 1.0)")
                .defineInRange("striderRockShellChance", 0.15, 0.0, 1.0);

        MAGMA_BUBBLE_CHANCE = BUILDER
                .comment("Chance for Magma Cube to drop magma bubble (0.0 to 1.0)")
                .defineInRange("magmaBubbleChance", 0.25, 0.0, 1.0);

        BLAZE_HEART_CHANCE = BUILDER
                .comment("Chance for Blaze to drop blaze heart (0.0 to 1.0)")
                .defineInRange("blazeHeartChance", 0.2, 0.0, 1.0);

        PIGLIN_GOLDEN_PLATE_CHANCE = BUILDER
                .comment("Chance for Piglin to drop golden plate (0.0 to 1.0)")
                .defineInRange("piglinGoldenPlateChance", 0.15, 0.0, 1.0);

        PIGLIN_BRUTE_GILDED_FRAGMENT_CHANCE = BUILDER
                .comment("Chance for Piglin Brute to drop gilded fragment (0.0 to 1.0)")
                .defineInRange("piglinBruteGildedFragmentChance", 0.1, 0.0, 1.0);

        BUILDER.pop();

        BUILDER.push("star_blessing");

        STAR_BLESSING_LEVEL_3_ARMOR_BONUS = BUILDER
                .comment("Armor bonus at level 3")
                .defineInRange("level3ArmorBonus", 2, 0, 20);

        STAR_BLESSING_LEVEL_6_TOUGHNESS_BONUS = BUILDER
                .comment("Armor toughness bonus at level 6")
                .defineInRange("level6ToughnessBonus", 1, 0, 10);

        STAR_BLESSING_LEVEL_9_KNOCKBACK_RESISTANCE = BUILDER
                .comment("Knockback resistance bonus at level 9 (0-10, where 1 = 10% resistance)")
                .defineInRange("level9KnockbackResistance", 1, 0, 10);

        STAR_BLESSING_LEVEL_12_FIRE_IMMUNITY = BUILDER
                .comment("Whether level 12 provides complete fire immunity")
                .define("level12FireImmunity", true);

        BUILDER.pop();
    }

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
    }
}