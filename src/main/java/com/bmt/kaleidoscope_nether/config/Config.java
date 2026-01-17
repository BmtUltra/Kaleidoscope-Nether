package com.bmt.kaleidoscope_nether.config;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Arrays;
import java.util.List;

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

    public static final ForgeConfigSpec.DoubleValue CRIMSON_FRUIT_DROP_CHANCE;
    public static final ForgeConfigSpec.DoubleValue WARPED_FRUIT_DROP_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> WARPED_BUFF_AFFECTED_MOBS;

    public static final ForgeConfigSpec.DoubleValue BLESSING_ENCHANTMENT_HEAL_AMOUNT;
    public static final ForgeConfigSpec.BooleanValue BLESSING_ENCHANTMENT_ENABLED;

    public static final ForgeConfigSpec.BooleanValue BLAZE_HEART_DESTROY_TERRAIN;
    public static final ForgeConfigSpec.BooleanValue BLAZE_HEART_HURT_OWNER;

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

        CRIMSON_FRUIT_DROP_CHANCE = BUILDER
                .comment("Drop chance for Crimson Fruit from Weeping Vines (0.0 to 1.0)")
                .defineInRange("crimsonFruitDropChance", 0.6, 0.0, 1.0);

        WARPED_FRUIT_DROP_CHANCE = BUILDER
                .comment("Drop chance for Warped Fruit from Twisting Vines (0.0 to 1.0)")
                .defineInRange("warpedFruitDropChance", 0.6, 0.0, 1.0);

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

        BUILDER.push("warped_buff");

        WARPED_BUFF_AFFECTED_MOBS = BUILDER
                .comment("List of mob entity IDs that are affected by Warped Buff (e.g., minecraft:piglin, minecraft:ghast)")
                .defineList("affectedMobs",
                        Arrays.asList(
                                "minecraft:piglin",
                                "minecraft:ghast",
                                "minecraft:magma_cube",
                                "minecraft:hoglin"
                        ),
                        obj -> obj instanceof String);

        BUILDER.pop();

        BUILDER.push("blessing_enchantment");

        BLESSING_ENCHANTMENT_ENABLED = BUILDER
                .comment("Whether the Blessing enchantment is enabled")
                .define("enabled", true);

        BLESSING_ENCHANTMENT_HEAL_AMOUNT = BUILDER
                .comment("Amount of health healed when eating food with Blessing enchantment (in half-hearts, 0.5 = 1 heart)")
                .defineInRange("healAmount", 0.5, 0.0, 10.0);

        BUILDER.pop();

        BUILDER.push("blaze_heart");

        BLAZE_HEART_DESTROY_TERRAIN = BUILDER
                .comment("Whether Blaze Heart explosion destroys terrain blocks")
                .define("destroyTerrain", false);

        BLAZE_HEART_HURT_OWNER = BUILDER
                .comment("Whether Blaze Heart explosion can hurt the thrower/owner")
                .define("hurtOwner", false);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
    }
}