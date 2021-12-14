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
package com.exomatic.pesart.client.gui;

import java.util.Optional;

import com.exomatic.pesart.blocks.entities.ExoFurnaceBlockEntity;
import com.exomatic.pesart.screen.PesartScreenInitializer;
import com.ibm.icu.util.StringTrieBuilder.Option;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ExoFurnaceGui extends SyncedGuiDescription {
    public static class WTicker extends WWidget {
        private Runnable runnable;

        public WTicker(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void tick() {
            runnable.run();
        }
    }

    private WLabel fuelLabel = new WLabel("Fuel: ");
    private ScreenHandlerContext ctx;
    private BlockPos blockEntity;
    public ExoFurnaceGui(BlockPos be, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(PesartScreenInitializer.EXO_FURNACE_GUI, syncId, playerInventory,
                getBlockInventory(context, ExoFurnaceBlockEntity.INV_SIZE), getBlockPropertyDelegate(context));
        this.ctx = context;
        this.blockEntity = be;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(170, 100);
        root.setInsets(Insets.ROOT_PANEL);
        root.add(new WTicker(() -> {
            //System.out.println("Tick");
            MinecraftClient mc = MinecraftClient.getInstance();
            BlockEntity blockEntity = mc.world.getBlockEntity(this.blockEntity);
            if (blockEntity != null && blockEntity instanceof ExoFurnaceBlockEntity furnace) {
                this.fuelLabel.setText(Text.of("Fuel: " + furnace.currentFuel));
            }
            // System.out.println("ticking " + this.blockEntity.isPresent());
        }), 0, 0);
        WItemSlot itemSlotIn = WItemSlot.of(blockInventory, 0);
        root.add(itemSlotIn, 2, 1);
        WItemSlot itemSlotOut = WItemSlot.of(blockInventory, 1);
        itemSlotIn.setFilter((stack) -> {
            Integer fuel = FuelRegistry.INSTANCE.get(stack.getItem());
            if (fuel == null) {
                return false;
            }
            return true;
        });
        fuelLabel.tick();
        root.add(fuelLabel, 6, 3);
        itemSlotOut.setInsertingAllowed(false);
        root.add(itemSlotOut, 6, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }

    public static class ExoFurnaceScreen extends CottonInventoryScreen<ExoFurnaceGui> {

        public ExoFurnaceScreen(ExoFurnaceGui description, PlayerEntity player, Text title) {
            super(description, player, title);
        }

    }
}
