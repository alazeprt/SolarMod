package com.alazeprt.block;

import com.alazeprt.Register;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alazeprt.block.solar_panel.LIGHT;
import static com.alazeprt.block.solar_panel.WATERLOGGED;

public class solar_panel_entity extends BlockEntity {
    public solar_panel_entity(BlockPos pos, BlockState state){
        super(Register.SOLAR_PANEL_ENTITY, pos, state);
    }
    static int light = 0;
    public static void tick(World world, BlockPos pos, BlockState state, solar_panel_entity be) {
        long tick = world.getTimeOfDay();
        long time = tick%24000;
        if (state.get(WATERLOGGED)) {
            solar_panel_entity.light = 0;
            state.with(LIGHT, 0);
        } else{
            if(time >= 0 && time <= 12000){
                light = 15;
            } else if(time >= 12001 && time <= 12100){
                light = 14;
            } else if(time >= 12101 && time <= 12200){
                light = 13;
            } else if(time >= 12201 && time <= 12300){
                light = 12;
            } else if(time >= 12301 && time <= 12400){
                light = 11;
            } else if(time >= 12401 && time <= 12500){
                light = 10;
            } else if(time >= 12501 && time <= 12600){
                light = 9;
            } else if(time >= 12601 && time <= 12700){
                light = 7;
            } else if(time >= 12701 && time <= 12800){
                light = 5;
            } else if(time >= 12801 && time <= 12900){
                light = 3;
            } else if(time >= 12901 && time <= 13000){
                light = 1;
            } else if(time >= 22001 && time <= 22200){
                light = 1;
            } else if(time >= 22201 && time <= 22400){
                light = 3;
            } else if(time >= 22401 && time <= 22600){
                light = 5;
            } else if(time >= 22601 && time <= 22800){
                light = 7;
            } else if(time >= 22801 && time <= 23000){
                light = 9;
            } else if(time >= 23001 && time <= 23200){
                light = 11;
            }  else if(time >= 23201 && time <= 23400){
                light = 12;
            } else if(time >= 23401 && time <= 23600){
                light = 13;
            } else if(time >= 23601 && time <= 23800){
                light = 14;
            } else if(time >= 23801){
                light = 15;
            } else {
                light = 0;
            }
        }
        world.setBlockState(pos, state.with(LIGHT, light));
    }
    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("light", light);
        super.writeNbt(nbt);
    }
}
