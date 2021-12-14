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
package com.exomatic.pesart.client.render.entity;

import com.exomatic.pesart.Reference;
import com.exomatic.pesart.client.render.EntityRenderers;
import com.exomatic.pesart.entities.CubeEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityRenderer.CubeEntityModel> {

    public CubeEntityRenderer(Context context) {
        super(context, new CubeEntityModel(context.getPart(EntityRenderers.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(CubeEntity var1) {
        return new Identifier(Reference.MODID, "textures/entity/cube/cube.png");
    }

    public static class CubeEntityModel extends EntityModel<CubeEntity> {
        private final ModelPart base;

        public CubeEntityModel(ModelPart modelPart) {
            this.base = modelPart.getChild(EntityModelPartNames.CUBE);
        }

        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            modelPartData.addChild(EntityModelPartNames.CUBE,
                    ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F),
                    ModelTransform.pivot(0F, 0F, 0F));
            return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public void setAngles(CubeEntity var1, float var2, float var3, float var4, float var5, float var6) {

        }

        @Override
        public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green,
                float blue, float alpha) {
            ImmutableList.of(this.base).forEach((modelRenderer) -> {
                modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            });

        }

    }
}
