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

import com.exomatic.pesart.blocks.entities.ShelfBlockEntity;
import com.exomatic.pesart.network.PesartNetworkInitializer;
import com.exomatic.pesart.network.packet.PacketSetShelfConfig;
import com.exopteron.network.ExoNetworkManager;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

// TODO THERE ARE ABSOLUTELY GOING TO BE SYNC ISSUES. I WILL FIX THIS TOMMOROW
public class ShelfConfigGui extends LightweightGuiDescription {
    public static class ShelfConfigScreen extends CottonClientScreen {

        public ShelfConfigScreen(GuiDescription description) {
            super(description);
        }

    }
    private MinecraftClient mc = MinecraftClient.getInstance();
    private ShelfBlockEntity blockEntity;
    private boolean showTag;

    public ShelfConfigGui(ShelfBlockEntity blockEntity) {
        this.blockEntity = blockEntity;
        this.showTag = blockEntity.showAlways;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);
        root.add(new WLabel("Shelf Configuration"), 4, 0);
        WButton button = new WButton();
        button.setOnClick(() -> {
            this.showTag ^= true;
            showAlwaysButton(button);
            this.sync();
        });
        root.add(button, 0, 1);
        showAlwaysButton(button);
        root.validate(this);
    }
    // TODO unhardcode this later
    private void showAlwaysButton(WButton button) {
        MutableText t = new TranslatableText("gui.pesart.shelf.show_always.off").formatted(Formatting.RED);
        if (this.showTag) {
            t = new TranslatableText("gui.pesart.shelf.show_always.on").formatted(Formatting.GREEN);
        }
        button.setLabel(t);
        button.setSize(mc.textRenderer.getWidth(t.getString()) + 15, 1);
    }
    private void sync() {
        this.blockEntity.showAlways = this.showTag;
        PesartNetworkInitializer.INSTANCE.sendPacketToServer(new PacketSetShelfConfig(this.blockEntity.getPos(), this.showTag));
    }
}
