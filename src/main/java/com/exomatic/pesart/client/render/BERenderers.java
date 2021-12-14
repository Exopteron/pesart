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
package com.exomatic.pesart.client.render;

import com.exomatic.pesart.blocks.entities.PesartBEInitializer;
import com.exomatic.pesart.client.render.be.ShelfBlockEntityRenderer;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class BERenderers {
    public static void setup() {
        BlockEntityRendererRegistry.register(PesartBEInitializer.SHELF_BLOCK_ENTITY, ShelfBlockEntityRenderer::new);
    }
}
