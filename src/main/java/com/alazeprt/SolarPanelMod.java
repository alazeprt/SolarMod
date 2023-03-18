package com.alazeprt;

import com.alazeprt.block.solar_panel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolarPanelMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("solar");

	public static final solar_panel SOLAR_PANEL = Registry.register(Registry.BLOCK,
			new Identifier("solar", "solar_panel"),
			new solar_panel(FabricBlockSettings.of(Material.STONE).strength(5.0f, 6.0f).requiresTool().mapColor(MapColor.BLUE)));


	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("solar", "solar_panel"), new BlockItem(SOLAR_PANEL, new FabricItemSettings()));
		LOGGER.info("Solar Panel Mod v1.0-alpha Loaded");
	}
}