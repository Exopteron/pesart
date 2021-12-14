package com.exomatic.pesart.client.gui;

import com.exomatic.pesart.screen.PesartScreenInitializer;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ScreenSetup {
    public static void setup() {
        ScreenRegistry.<ExoFurnaceGui, ExoFurnaceGui.ExoFurnaceScreen>register(PesartScreenInitializer.EXO_FURNACE_GUI, (gui, inventory, title) -> new ExoFurnaceGui.ExoFurnaceScreen(gui, inventory.player, title));
    }
}
