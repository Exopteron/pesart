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
