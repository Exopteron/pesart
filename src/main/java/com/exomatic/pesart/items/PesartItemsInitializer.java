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
package com.exomatic.pesart.items;

import com.exomatic.pesart.Reference;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
