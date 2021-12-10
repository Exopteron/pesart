package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Pesart;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElevatorBlock extends Block {

    public ElevatorBlock() {
        super(FabricBlockSettings.of(Material.METAL));
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // TODO do this later
        if (!world.isClient) {
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                if (player.isSneaking()) {
                    Pesart.LOGGER.info("Sneaking");
                }
            }
        }
    }
}
