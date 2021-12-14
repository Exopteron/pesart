/* 
Copyright (c) 2021 Exopteron, PrismaticYT

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exomatic.pesart.blocks.entities;

import com.exomatic.pesart.blocks.PesartBlocksInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class PesartBEInitializer {
    public static BlockEntityType<ShelfBlockEntity> SHELF_BLOCK_ENTITY;
    public static BlockEntityType<ExoFurnaceBlockEntity> EF_BLOCK_ENTITY;
    public static void setup() {
        SHELF_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PesartBlocksInitializer.BlockEntry.SHELF_BLOCK.getIdentifier(), FabricBlockEntityTypeBuilder.create(ShelfBlockEntity::new, PesartBlocksInitializer.BlockEntry.SHELF_BLOCK.getBlock()).build());
        EF_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PesartBlocksInitializer.BlockEntry.EXO_FURNACE.getIdentifier(), FabricBlockEntityTypeBuilder.create(ExoFurnaceBlockEntity::new, PesartBlocksInitializer.BlockEntry.EXO_FURNACE.getBlock()).build());
    }
}
