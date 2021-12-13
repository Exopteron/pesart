package com.exomatic.pesart.blocks.entities;

import com.exomatic.pesart.blocks.PesartBlocksInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class PesartBEInitializer {
    public static BlockEntityType<ShelfBlockEntity> SHELF_BLOCK_ENTITY;
    public static void setup() {
        SHELF_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PesartBlocksInitializer.BlockEntry.SHELF_BLOCK.getIdentifier(), FabricBlockEntityTypeBuilder.create(ShelfBlockEntity::new, PesartBlocksInitializer.BlockEntry.SHELF_BLOCK.getBlock()).build());
    }
}
