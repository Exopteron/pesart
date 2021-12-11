package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Pesart;
import com.exomatic.pesart.network.packet.PacketJump;
import com.exopteron.network.ExoNetworkManager;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.ServerTask;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.World;

public class ElevatorBlock extends Block {

    public ElevatorBlock() {
        super(FabricBlockSettings.of(Material.METAL));
    }
    private void playSound(PlayerEntity player) {
        player.playSound(SoundEvents.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 1, 1);
    }
    public void goUp(PlayerEntity player, BlockState state, BlockPos pos) {
        int i;
        BlockPos pos2 = null;
        boolean found = false;
        for (i = 1; i < 5; i++) {
            pos2 = pos.add(0, i, 0);
            BlockState potentialElevator = player.world.getBlockState(pos2);
            if (potentialElevator.getBlock() instanceof ElevatorBlock) {
                found = true;
                break;
            }
        }
        if (!found) {
            return;
        }
        BlockPos pos3;
        for (i = 1; i < 3; i++) {
            pos3 = pos.add(0, i, 0);
            BlockState checkBlock = player.world.getBlockState(pos3);
            if (!checkBlock.isAir()) {
                return;
            }
        }
        player.teleport(pos2.getX() + 0.5, pos2.getY() + 1, pos2.getZ() + 0.5);
        player.addVelocity(0, 0.25, 0);
        player.velocityModified = true;
        playSound(player);
    }
    public void goDown(PlayerEntity player, BlockState state, BlockPos pos) {
        int i;
        BlockPos pos2 = null;
        boolean found = false;
        for (i = 1; i < 5; i++) {
            pos2 = pos.subtract(new Vec3i(0, i, 0));
            BlockState potentialElevator = player.world.getBlockState(pos2);
            if (potentialElevator.getBlock() instanceof ElevatorBlock) {
                found = true;
                break;
            }
        }
        if (!found) {
            return;
        }
        BlockPos pos3;
        for (i = 1; i < 3; i++) {
            pos3 = pos.add(0, i, 0);
            BlockState checkBlock = player.world.getBlockState(pos3);
            if (!checkBlock.isAir()) {
                return;
            }
        }
        player.teleport(pos2.getX() + 0.5, pos2.getY() + 1, pos2.getZ() + 0.5);
        playSound(player);
    }
}
