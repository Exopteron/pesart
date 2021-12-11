package com.exomatic.pesart.items;

import com.exomatic.pesart.Reference;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class PesartItemsInitializer {
    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new Identifier(Reference.MODID, "creative_tab"), () -> {
        return Items.STONE_BRICKS.getDefaultStack();
    });
    public static void setup() {

    }
}
