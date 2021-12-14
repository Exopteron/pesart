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
package com.exomatic.pesart.screen;

import com.exomatic.pesart.blocks.PesartBlocksInitializer;
import com.exomatic.pesart.blocks.entities.ExoFurnaceBlockEntity;
import com.exomatic.pesart.client.gui.ExoFurnaceGui;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

public class PesartScreenInitializer {
    // def a better way to do this. Can't be bothered to find it!
    public static ScreenHandlerType<ExoFurnaceGui> EXO_FURNACE_GUI = ScreenHandlerRegistry.registerExtended(PesartBlocksInitializer.BlockEntry.EXO_FURNACE.getIdentifier(), (syncId, inventory, buf) -> {
        BlockPos pos = buf.readBlockPos();
        BlockEntity bEntity = inventory.player.world.getBlockEntity(pos);
        if (bEntity == null) {
            return null;
        }
        if (!(bEntity instanceof ExoFurnaceBlockEntity)) {
            return null;
        }
        return new ExoFurnaceGui(pos, syncId, inventory, ScreenHandlerContext.EMPTY);
    });
    public static void setup() {

    }
}
