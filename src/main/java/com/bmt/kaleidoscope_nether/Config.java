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

    public static final ForgeConfigSpec.BooleanValue HOGLIN_TUSK_UPGRADE_ENABLED;
    public static final ForgeConfigSpec.DoubleValue HOGLIN_TUSK_ATTACK_BONUS; // 需要改为 FloatValue
    public static final ForgeConfigSpec.IntValue HOGLIN_TUSK_MAX_UPGRADES;

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
                .defineInRange("flat", 250, 1, 1000);

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

        BUILDER.pop();

        BUILDER.push("hoglin_tusk_upgrade");

        HOGLIN_TUSK_UPGRADE_ENABLED = BUILDER
                .comment("Whether Hoglin Tusk upgrade functionality is enabled")
                .define("enabled", true);

        HOGLIN_TUSK_ATTACK_BONUS = BUILDER
                .comment("Attack damage bonus per Hoglin Tusk upgrade (e.g., 0.5 means +0.5 attack damage)")
                .defineInRange("attackBonus", 0.5, 0.1, 5.0);

        HOGLIN_TUSK_MAX_UPGRADES = BUILDER
                .comment("Maximum number of Hoglin Tusk upgrades per tool")
                .defineInRange("maxUpgrades", 10, 1, 50);

        BUILDER.pop();
    }

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
    }
}