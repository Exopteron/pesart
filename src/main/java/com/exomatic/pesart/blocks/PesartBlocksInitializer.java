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

    public static void setup() {
        Pesart.LOGGER.info("Registering blocks!");
        entries.forEach((name, block) -> {
            Pesart.LOGGER.debug("Registering block '" + name + "'!");
            Registry.register(Registry.BLOCK, new Identifier(Reference.MODID, name), block);
        });
    }
}
