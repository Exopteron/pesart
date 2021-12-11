package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Pesart;
import com.exomatic.pesart.Reference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

import com.exomatic.pesart.Reference;

public class PesartBlocksInitializer {
    private static HashMap<String, Block> entries = new HashMap<>();

    private static Block add(String name, Block block) {
        entries.put(name, block);
        return block;
    }

    public static final BlastWallBlock BLAST_WALL_BASIC = (BlastWallBlock) add("blast_wall_basic", new BlastWallBlock(20.0F));
    public static final BlastWallBlock BLAST_WALL_REINFORCED = (BlastWallBlock) add("blast_wall_reinforced", new BlastWallBlock(30.0F));
    public static final BlastWallBlock BLAST_WALL_INDUSTRIAL = (BlastWallBlock) add("blast_wall_industrial", new BlastWallBlock(50.0F));
    public static final BlastWallBlock BLAST_WALL_ADVANCED = (BlastWallBlock) add("blast_wall_advanced", new BlastWallBlock(80.0F));
    public static final Block ELEVATOR_BLOCK = add("elevator_block", new ElevatorBlock());
    public static void setup() {
        entries.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier(Reference.MODID, name), block);
            Registry.register(Registry.ITEM, new Identifier(Reference.MODID, name), new BlockItem(block, new FabricItemSettings()));
        });
    }
}
