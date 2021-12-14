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

import com.exomatic.pesart.Reference;
import com.exomatic.pesart.client.render.entity.CubeEntityRenderer;
import com.exomatic.pesart.client.render.entity.CubeEntityRenderer.CubeEntityModel;
import com.exomatic.pesart.entities.PesartEntitiesInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class EntityRenderers {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(Reference.MODID, "cube_entity"), "main");
    public static void setup() {
        EntityRendererRegistry.register(PesartEntitiesInitializer.CUBE, (ctx) -> {
            return new CubeEntityRenderer(ctx);
        });
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
    }
}
