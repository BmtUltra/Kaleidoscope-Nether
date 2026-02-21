package com.bmt.kaleidoscope_nether.config;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

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

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> WARPED_BUFF_AFFECTED_MOBS;

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

        BUILDER.push("warped_buff");

        WARPED_BUFF_AFFECTED_MOBS = BUILDER
                .comment("List of mob entity IDs that are affected by Warped Buff (e.g., minecraft:piglin, minecraft:ghast)")
                .defineList("affectedMobs",
                        Arrays.asList(
                                "minecraft:piglin",
                                "minecraft:piglin_brute",
                                "minecraft:zombified_piglin",
                                "minecraft:hoglin",
                                "minecraft:zoglin",
                                "minecraft:ghast",
                                "minecraft:magma_cube",
                                "minecraft:blaze",
                                "minecraft:wither_skeleton",
                                "minecraft:wither"
                        ),
                        obj -> obj instanceof String);

        BUILDER.pop();

        BUILDER.push("blaze_heart（还没做好）");

        BLAZE_HEART_DESTROY_TERRAIN = BUILDER
                .comment("Whether Blaze Heart explosion destroys terrain blocks")
                .define("destroyTerrain", false);

        BLAZE_HEART_HURT_OWNER = BUILDER
                .comment("Whether Blaze Heart explosion can hurt the thrower/owner")
                .define("hurtOwner", false);

        BUILDER.pop();
    }
    public static final ForgeConfigSpec SPEC = BUILDER.build();
}