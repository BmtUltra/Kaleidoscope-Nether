package com.bmt.kaleidoscope_nether.config;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
@Config(name = KaleidoscopeNether.MOD_ID)
public class KNConfig implements ConfigData {
    
    @ConfigEntry.Category("star_dust_repair")
    @Comment("Whether star dust repair functionality is enabled")
    public boolean starDustRepairEnabled = true;
    
    @ConfigEntry.Category("star_dust_repair")
    @Comment("Star dust repair percentage (e.g., 25 means 25%)")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
    public int starDustRepairPercentage = 25;
    
    @ConfigEntry.Category("star_dust_repair")
    @Comment("Star dust flat repair value (additional durability points repaired)")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1000)
    public int starDustRepairFlat = 150;
    
    @ConfigEntry.Category("everlasting_flame_steak")
    @Comment("Whether the Everlasting Flame Steak is enabled")
    public boolean everlastingFlameSteakEnabled = true;
    
    @ConfigEntry.Category("everlasting_flame_steak")
    @Comment("Cooldown in ticks for Everlasting Flame Steak (20 ticks = 1 second)")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1200)
    public int everlastingFlameSteakCooldown = 300;
    
    @ConfigEntry.Category("warped_buff")
    @Comment("List of mob entity IDs that are affected by Warped Buff")
    public List<String> warpedBuffAffectedMobs = Arrays.asList(
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
    );
    
    @ConfigEntry.Category("blaze_heart")
    @Comment("Whether Blaze Heart explosion destroys terrain blocks")
    public boolean blazeHeartDestroyTerrain = false;
    
    @ConfigEntry.Category("blaze_heart")
    @Comment("Whether Blaze Heart explosion can hurt the thrower/owner")
    public boolean blazeHeartHurtOwner = false;

    private static KNConfig INSTANCE;
    
    public static void register() {
        AutoConfig.register(KNConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(KNConfig.class).getConfig();
    }
    
    public static KNConfig getInstance() {
        return INSTANCE;
    }

    public static java.util.function.Supplier<Boolean> getStarDustRepairEnabled() {
        return () -> getInstance().starDustRepairEnabled;
    }
    
    public static java.util.function.Supplier<Integer> getStarDustRepairPercentage() {
        return () -> getInstance().starDustRepairPercentage;
    }
    
    public static java.util.function.Supplier<Integer> getStarDustRepairFlat() {
        return () -> getInstance().starDustRepairFlat;
    }
    
    public static java.util.function.Supplier<Boolean> getEverlastingFlameSteakEnabled() {
        return () -> getInstance().everlastingFlameSteakEnabled;
    }
    
    public static java.util.function.Supplier<Integer> getEverlastingFlameSteakCooldown() {
        return () -> getInstance().everlastingFlameSteakCooldown;
    }
    
    public static java.util.function.Supplier<List<String>> getWarpedBuffAffectedMobs() {
        return () -> getInstance().warpedBuffAffectedMobs;
    }
    
    public static java.util.function.Supplier<Boolean> getBlazeHeartDestroyTerrain() {
        return () -> getInstance().blazeHeartDestroyTerrain;
    }
    
    public static java.util.function.Supplier<Boolean> getBlazeHeartHurtOwner() {
        return () -> getInstance().blazeHeartHurtOwner;
    }
}