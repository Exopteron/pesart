package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Pesart;
import com.exomatic.pesart.Reference;
import net.minecraft.block.Block;
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

    public static void setup() {
        entries.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier(Reference.MODID, name), block);
        });
    }
}
