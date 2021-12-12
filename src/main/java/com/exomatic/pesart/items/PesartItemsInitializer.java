package com.exomatic.pesart.items;

import com.exomatic.pesart.Reference;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PesartItemsInitializer {
    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new Identifier(Reference.MODID, "creative_tab"), () -> {
        return Items.STONE_BRICKS.getDefaultStack();
    });
    public static final FlightRing FLIGHT_RING = new FlightRing();
    public static void setup() {
        Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "flight_ring"), FLIGHT_RING);
    }
    public static class FlightRing extends Item {

        public FlightRing() {
            super(new Item.Settings().maxCount(1).group(CREATIVE_TAB));
        }
        public boolean containsFlightRing(Inventory inventory) {
            int i;
            for (i = 0; i <= inventory.size(); i++) {
                if (inventory.getStack(i).getItem() instanceof FlightRing) {
                    return true;
                }
            }
            return false;
        }
    }
}
