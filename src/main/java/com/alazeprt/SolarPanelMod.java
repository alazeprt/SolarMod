package com.alazeprt;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolarPanelMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("solar");

	@Override
	public void onInitialize() {
		Register.register();
		LOGGER.info("Solar Panel Mod v1.2.1-develop Loaded");
	}


}