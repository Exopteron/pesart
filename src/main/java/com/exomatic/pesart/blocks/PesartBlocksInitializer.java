package com.exomatic.pesart.blocks;

import net.minecraft.block.Block;

import java.util.HashMap;

public class PesartBlocksInitializer {
    private static HashMap<String, Block> entries = new HashMap<>();

    private static Block add(String name, Block block) {
        entries.put(name, block);
        return block;
    }

    public static void setup() {
        
    }
}
