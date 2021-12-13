package com.exomatic.pesart.client.render;

import com.exomatic.pesart.blocks.entities.PesartBEInitializer;
import com.exomatic.pesart.client.render.be.ShelfBlockEntityRenderer;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class BERenderers {
    public static void setup() {
        BlockEntityRendererRegistry.register(PesartBEInitializer.SHELF_BLOCK_ENTITY, ShelfBlockEntityRenderer::new);
    }
}
