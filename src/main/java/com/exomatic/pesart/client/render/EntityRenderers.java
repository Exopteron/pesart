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
