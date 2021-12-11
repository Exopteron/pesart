package com.exomatic.pesart.callbacks;

import com.exomatic.pesart.blocks.ElevatorBlock;
import com.exomatic.pesart.callbacks.PlayerSneakCallback.Mode;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class PesartCallbackInitializer {
    public static void setup() {
        setupElevator();
    }
    // TODO organise callbacks better
    private static void setupElevator() {
        PlayerJumpOnBlockCallback.EVENT.register((player, blockstate, pos) -> {
            if (blockstate.getBlock() instanceof ElevatorBlock block) {
                block.goUp(player, blockstate, pos);
            }
        });
        PlayerSneakCallback.EVENT.register((player, mode) -> {
            if (mode == Mode.START) {
                BlockState blockstate = player.world.getBlockState(player.getBlockPos().offset(Direction.DOWN));
                Block block = blockstate.getBlock();
                if (block instanceof ElevatorBlock elevator) {
                    elevator.goDown(player, blockstate, player.getBlockPos().offset(Direction.DOWN));
                }
            }
        });
    }
}
