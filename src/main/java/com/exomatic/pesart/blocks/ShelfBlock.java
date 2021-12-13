package com.exomatic.pesart.blocks;

import com.exomatic.pesart.blocks.entities.ShelfBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class ShelfBlock extends TransparentBlock implements BlockEntityProvider {
    public ShelfBlock() {
        super(FabricBlockSettings.of(Material.WOOD).nonOpaque().requiresTool().strength(1.0F));
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos var1, BlockState var2) {
        return new ShelfBlockEntity(var1, var2);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return AbstractPressurePlateBlock.hasTopRim(world, blockPos) || AbstractPressurePlateBlock.sideCoversSmallSquare(world, blockPos, Direction.UP);
    }
    // not sure how to do this yet.
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity != null && blockEntity instanceof ShelfBlockEntity shelfBlockEntity) {
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), shelfBlockEntity.getHeldItem()));
            }
        }
        super.onBreak(world, pos, state, player);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (world.isClient) {
            if (player.isSneaking()) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null && blockEntity instanceof ShelfBlockEntity shelfBlockEntity) {
                    boolean showAlways = !shelfBlockEntity.showAlways;
                    if (showAlways) {
                        player.sendMessage(new TranslatableText("info.pesart.shelf.show_always.on").formatted(Formatting.GRAY).formatted(Formatting.ITALIC), false);
                    } else {
                        player.sendMessage(new TranslatableText("info.pesart.shelf.show_always.off").formatted(Formatting.GRAY).formatted(Formatting.ITALIC), false);
                    }
                }
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BIT, SoundCategory.BLOCKS, 1, 1);
            }
            return ActionResult.SUCCESS;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null && blockEntity instanceof ShelfBlockEntity shelfBlockEntity) {
            if (player.isSneaking()) {
                shelfBlockEntity.showAlways ^= true;
                world.updateListeners(pos, state, state, 2);
                return ActionResult.SUCCESS;
            } else {
                ItemStack handItem = player.getInventory().getMainHandStack().copy();
                if (handItem == null) handItem = ItemStack.EMPTY;
                if (shelfBlockEntity.getHeldItem() == null) shelfBlockEntity.setHeldItem(ItemStack.EMPTY);
                player.getInventory().setStack(player.getInventory().selectedSlot, shelfBlockEntity.getHeldItem());
                shelfBlockEntity.setHeldItem(handItem);
                world.updateListeners(pos, state, state, 2);
                return ActionResult.SUCCESS;   
            }
        }
        return ActionResult.FAIL;
    }
}
