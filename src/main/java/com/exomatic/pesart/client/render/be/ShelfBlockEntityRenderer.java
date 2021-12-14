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
package com.exomatic.pesart.client.render.be;

import com.exomatic.pesart.blocks.entities.ShelfBlockEntity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import net.minecraft.client.render.model.json.ModelTransformation;

public class ShelfBlockEntityRenderer implements BlockEntityRenderer<ShelfBlockEntity> {
    public ShelfBlockEntityRenderer(Context ctx) {

    }

    @Override
    public void render(ShelfBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light,
            int overlay) {
        matrices.push();
        // Move the item
        matrices.translate(0.5, 0.25, 0.5);

        // Rotate the item
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * 4));

        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getHeldItem(),
                ModelTransformation.Mode.GROUND, light, overlay, matrices, vertexConsumers, 0);

        // Mandatory call after GL calls
        matrices.pop();
        if (!blockEntity.getHeldItem().isEmpty()) {
            try {
                MinecraftClient mc = MinecraftClient.getInstance();
                BlockHitResult hitResult = (BlockHitResult) mc.crosshairTarget;
                if (hitResult.getBlockPos().equals(blockEntity.getPos()) || blockEntity.showAlways) {
                    String text = blockEntity.getHeldItem().getName().getString();
                    int count = blockEntity.getHeldItem().getCount();
                    if (count > 1) {
                        text += " x" + count;
                    }
                    float f = 1.0f;
                    int i = 0;
                    matrices.push();
                    matrices.translate(0.5, f, 0.5);
                    matrices.multiply(mc.gameRenderer.getCamera().getRotation());
                    matrices.scale(-0.025f, -0.025f, 0.025f);
                    Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                    float g = mc.options.getTextBackgroundOpacity(0.25f);
                    int j = (int) (g * 255.0f) << 24;
                    TextRenderer textRenderer = mc.textRenderer;
                    float h = -textRenderer.getWidth(text) / 2;
                    textRenderer.draw(text, h, (float) i, 0x20FFFFFF, false, matrix4f, vertexConsumers, true, j, light);
                    textRenderer.draw(text, h, (float) i, -1, false, matrix4f, vertexConsumers, false, 0, light);
                    matrices.pop();
                }
            } catch (Exception e) {
    
            }
        }
    }

}
