package com.alazeprt.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.block.DaylightDetectorBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class solar_panel extends RedstoneBlock {

    public static final BooleanProperty DAYTIME = BooleanProperty.of("daytime");

    public solar_panel(Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(DAYTIME, true));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.125f, 1f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DAYTIME);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isDay()){
            world.setBlockState(pos, state.with(DAYTIME, true));
        } else{
            world.setBlockState(pos, state.with(DAYTIME, false));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(world.isDay()){
            world.setBlockState(pos, state.with(DAYTIME, true));
        } else{
            world.setBlockState(pos, state.with(DAYTIME, false));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
