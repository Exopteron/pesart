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
