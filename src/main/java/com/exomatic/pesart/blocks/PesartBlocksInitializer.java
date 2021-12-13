package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Reference;
import com.exomatic.pesart.items.PesartItemsInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PesartBlocksInitializer {
    public static void setup() {
        BlockEntry.setup();
    }
    public static enum BlockEntry {
        BLAST_WALL_BASIC("blast_wall_basic", new BlastWallBlock(20.0F)),
        BLAST_WALL_REINFORCED("blast_wall_reinforced", new BlastWallBlock(30.0F)),
        BLAST_WALL_INDUSTRIAL("blast_wall_industrial", new BlastWallBlock(50.0F)),
        BLAST_WALL_ADVANCED("blast_wall_advanced", new BlastWallBlock(80.0F)),
        ELEVATOR_BLOCK("elevator_block", new ElevatorBlock(7))
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
        private <T extends Block, B extends BlockItem> BlockEntry(String name, T block, B item) {
            this.identifier = new Identifier(Reference.MODID, name);
            this.blockItem = item;
            this.block = block;
        }
        private <T extends Block> BlockEntry(String name, T block) {
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