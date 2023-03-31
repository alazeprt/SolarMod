package com.alazeprt.block;

import com.alazeprt.Register;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class vertical_solar_panel extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final IntProperty LIGHT = IntProperty.of("light", 0, 15);

    public vertical_solar_panel(Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT, 0).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIGHT, WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        switch(dir) {
            case NORTH, SOUTH:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.4375f, 1.0f, 1.0f, 0.5625f);
            case EAST, WEST:
                return VoxelShapes.cuboid(0.4375f, 0.0f, 0.0f, 0.5625f, 1.0f, 1.0f);
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER)
                .with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            solar_panel_entity.light = 0;
            getDefaultState().with(LIGHT, 0);
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state){
        return new vertical_solar_panel_entity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Register.VERTICAL_SOLAR_PANEL_ENTITY, vertical_solar_panel_entity::tick);
    }

    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction){
        return vertical_solar_panel_entity.light;
    }

}
