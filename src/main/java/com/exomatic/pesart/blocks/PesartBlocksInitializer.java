package com.exomatic.pesart.blocks;

import com.exomatic.pesart.Reference;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PesartBlocksInitializer {
    public static final Block ELEVATOR_BLOCK = new ElevatorBlock();
    public static void setup() {
        Registry.register(Registry.BLOCK, new Identifier(Reference.MODID, "elevator_block"), ELEVATOR_BLOCK);
    }
}
