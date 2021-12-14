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
package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Reference;
import com.exomatic.pesart.blocks.entities.PesartBEInitializer;
import com.exomatic.pesart.items.PesartItemsInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PesartBlocksInitializer {
    public static void setup() {
        BlockEntry.setup();
        PesartBEInitializer.setup();
    }
    public static enum BlockEntry {
        BLAST_WALL_BASIC("blast_wall_basic", new BlastWallBlock(20.0F)),
        BLAST_WALL_REINFORCED("blast_wall_reinforced", new BlastWallBlock(30.0F)),
        BLAST_WALL_INDUSTRIAL("blast_wall_industrial", new BlastWallBlock(50.0F)),
        BLAST_WALL_ADVANCED("blast_wall_advanced", new BlastWallBlock(80.0F)),
        ELEVATOR_BLOCK("elevator_block", new ElevatorBlock(7)),
        SHELF_BLOCK("shelf_block", new ShelfBlock()),
        EXO_FURNACE("exo_furnace", new ExoFurnace())
        ;
        private Identifier identifier;
        private Block block;
        private BlockItem blockItem;
        public <T extends BlockItem> T getBlockItem() {
            if (blockItem == null) {
                return null;
            }
            try {
                return (T) blockItem;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public <T extends Block> T getBlock() {
            try {
                return (T) block;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public Identifier getIdentifier() {
            return this.identifier;
        }
        private BlockEntry(String name, Block block, BlockItem item) {
            this.identifier = new Identifier(Reference.MODID, name);
            this.blockItem = item;
            this.block = block;
        }
        private BlockEntry(String name, Block block) {
            this(name, block, new BlockItem(block, new FabricItemSettings().group(PesartItemsInitializer.CREATIVE_TAB)));
        }
        public static void setup() {
            for (BlockEntry b : BlockEntry.values()) {
                Registry.register(Registry.BLOCK, b.getIdentifier(), b.getBlock());
                if (b.getBlockItem() != null) {
                    Registry.register(Registry.ITEM, b.getIdentifier(), b.getBlockItem());
                }
            }
        }
    }
}