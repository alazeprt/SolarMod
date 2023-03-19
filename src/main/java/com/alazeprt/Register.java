package com.alazeprt;

import com.alazeprt.block.solar_panel;
import com.alazeprt.block.vertical_solar_panel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Register {
    public static void register(){
        final solar_panel SOLAR_PANEL = Registry.register(Registry.BLOCK,
                new Identifier("solar", "solar_panel"),
                new solar_panel(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).requiresTool().mapColor(MapColor.BLUE)));
        final vertical_solar_panel VERTICAL_SOLAR_PANEL = Registry.register(Registry.BLOCK,
                new Identifier("solar", "vertical_solar_panel"),
                new vertical_solar_panel(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).requiresTool().mapColor(MapColor.BLUE)));
        Registry.register(Registry.ITEM,
                new Identifier("solar", "solar_panel"),
                new BlockItem(SOLAR_PANEL, new FabricItemSettings()));
        Registry.register(Registry.ITEM,
                new Identifier("solar", "vertical_solar_panel"),
                new BlockItem(VERTICAL_SOLAR_PANEL, new FabricItemSettings()));
    }
}
