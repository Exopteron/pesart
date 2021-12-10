package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Pesart;
import com.exomatic.pesart.Reference;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class PesartBlocksInitializer {
    private static HashMap<String, Block> entries = new HashMap<>();

    private static Block add(String name, Block block) {
        entries.put(name, block);
        return block;
    }

    BlastWallBlock BLAST_WALL_BASIC = (BlastWallBlock) add("blast_wall_basic", new BlastWallBlock(20.0F));
    BlastWallBlock BLAST_WALL_REINFORCED = (BlastWallBlock) add("blast_wall_reinforced", new BlastWallBlock(30.0F));
    BlastWallBlock BLAST_WALL_INDUSTRIAL = (BlastWallBlock) add("blast_wall_industrial", new BlastWallBlock(50.0F));
    BlastWallBlock BLAST_WALL_ADVANCED = (BlastWallBlock) add("blast_wall_advanced", new BlastWallBlock(80.0F));

    public static void setup() {
        Pesart.LOGGER.info("Registering blocks!");
        entries.forEach((name, block) -> {
            Pesart.LOGGER.debug("Registering block '" + name + "'!");
            Registry.register(Registry.BLOCK, new Identifier(Reference.MODID, name), block);
        });
    }
}
