package com.alazeprt;

import com.alazeprt.block.*;
import com.alazeprt.item.silicon_chip;
import com.alazeprt.item.silicon_ingot;
import com.alazeprt.item.solar_cell;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

public class Register {
    public static final solar_panel SOLAR_PANEL = new solar_panel(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).requiresTool().mapColor(MapColor.BLUE));
    public static final vertical_solar_panel VERTICAL_SOLAR_PANEL = new vertical_solar_panel(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).requiresTool().mapColor(MapColor.BLUE));
    public static final silicon_ore SILICON_ORE = new silicon_ore(FabricBlockSettings.of(Material.STONE).strength(3.0f, 3.0f).requiresTool().mapColor(MapColor.GRAY));
    public static final silicon_block SILICON_BLOCK = new silicon_block(FabricBlockSettings.of(Material.STONE).strength(4.0f, 5.0f).requiresTool());
    public static final Item SOLAR_PANEL_ITEM = new BlockItem(SOLAR_PANEL, new FabricItemSettings());
    public static final Item VERTICAL_SOLAR_PANEL_ITEM = new BlockItem(VERTICAL_SOLAR_PANEL, new FabricItemSettings());
    public static final BlockEntityType<solar_panel_entity> SOLAR_PANEL_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("solar", "solar_block_entity"),
            FabricBlockEntityTypeBuilder.create(solar_panel_entity::new, SOLAR_PANEL).build()
    );

    public static final BlockEntityType<vertical_solar_panel_entity> VERTICAL_SOLAR_PANEL_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("solar", "vertical_solar_block_entity"),
            FabricBlockEntityTypeBuilder.create(vertical_solar_panel_entity::new, VERTICAL_SOLAR_PANEL).build()
    );


    public static void register(){
        Registry.register(Registry.BLOCK, new Identifier("solar", "solar_panel"), SOLAR_PANEL);
        Registry.register(Registry.BLOCK, new Identifier("solar", "vertical_solar_panel"), VERTICAL_SOLAR_PANEL);
        Registry.register(Registry.BLOCK, new Identifier("solar", "silicon_ore"), SILICON_ORE);
        Registry.register(Registry.BLOCK, new Identifier("solar", "silicon_block"), SILICON_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("solar", "solar_panel"), SOLAR_PANEL_ITEM);
        Registry.register(Registry.ITEM, new Identifier("solar", "vertical_solar_panel"), VERTICAL_SOLAR_PANEL_ITEM);
        final Item SILICON_ORE_ITEM = Registry.register(Registry.ITEM,
                new Identifier("solar", "silicon_ore"),
                new BlockItem(SILICON_ORE, new FabricItemSettings()));
        final Item SILICON_BLOCK_ITEM = Registry.register(Registry.ITEM,
                new Identifier("solar", "silicon_block"),
                new BlockItem(SILICON_BLOCK, new FabricItemSettings()));
        final silicon_ingot SILICON_INGOT = Registry.register(Registry.ITEM,
                new Identifier("solar", "silicon_ingot"),
                new silicon_ingot(new FabricItemSettings()));
        final silicon_chip SILICON_CHIP = Registry.register(Registry.ITEM,
                new Identifier("solar", "silicon_chip"),
                new silicon_chip(new FabricItemSettings()));
        final solar_cell SOLAR_CELL = Registry.register(Registry.ITEM,
                new Identifier("solar", "solar_cell"),
                new solar_cell(new FabricItemSettings()));
        ConfiguredFeature<?, ?> OVERWORLD_WOOL_ORE_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier("solar", "silicon_ore"),
                new ConfiguredFeature(Feature.ORE,
                        new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, SILICON_ORE.getDefaultState(), 6)));
        PlacedFeature OVERWORLD_WOOL_ORE_PLACED_FEATURE = Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier("solar", "silicon_ore"),
                new PlacedFeature(RegistryEntry.of(OVERWORLD_WOOL_ORE_CONFIGURED_FEATURE),
                        Arrays.asList(
                                CountPlacementModifier.of(16),
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))
                        )));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier("solar", "silicon_ore")));
        final ItemGroup SOLAR_GROUP = FabricItemGroupBuilder.create(
                        new Identifier("solar", "solar_group"))
                .icon(() -> new ItemStack(SOLAR_PANEL_ITEM))
                .appendItems(itemStacks -> {
                    itemStacks.add(new ItemStack(SOLAR_PANEL_ITEM));
                    itemStacks.add(new ItemStack(VERTICAL_SOLAR_PANEL_ITEM));
                    itemStacks.add(new ItemStack(SOLAR_CELL));
                    itemStacks.add(new ItemStack(SILICON_ORE_ITEM));
                    itemStacks.add(new ItemStack(SILICON_INGOT));
                    itemStacks.add(new ItemStack(SILICON_CHIP));
                    itemStacks.add(new ItemStack(SILICON_BLOCK_ITEM));
                })
                .build();
    }

}
