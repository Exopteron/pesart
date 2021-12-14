package com.exomatic.pesart.blocks;

import com.exomatic.pesart.blocks.entities.ExoFurnaceBlockEntity;
import com.exomatic.pesart.blocks.entities.PesartBEInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExoFurnace extends BlockWithEntity {

    protected ExoFurnace() {
        super(FabricBlockSettings.of(Material.STONE).nonOpaque());
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        // TODO Auto-generated method stub
        NamedScreenHandlerFactory factory = super.createScreenHandlerFactory(state, world, pos);
        return factory;
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExoFurnaceBlockEntity(pos, state);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return checkType(type, PesartBEInitializer.EF_BLOCK_ENTITY, (world1, pos, state1, be) -> be.tick(world, pos, state));
    }
}
