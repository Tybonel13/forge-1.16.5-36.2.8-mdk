package com.tybonel13.slivermod.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.client.geo.SliverModelGeo;
import com.tybonel13.slivermod.client.model.SliverModel;
import com.tybonel13.slivermod.entities.SliverEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SliverRenderer extends GeoEntityRenderer<SliverEntity> {
    public SliverRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SliverModelGeo());
    }

    @Override
    public RenderType getRenderType(SliverEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.getEntityTranslucent(getTextureLocation(animatable));
    }

    protected static final ResourceLocation TEXTURE_basic = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver.png");
    protected static final ResourceLocation TEXTURE_red = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_red.png");
    protected static final ResourceLocation TEXTURE_orange = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_orange.png");
    protected static final ResourceLocation TEXTURE_yellow = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_yellow.png");
    protected static final ResourceLocation TEXTURE_green = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_green.png");
    protected static final ResourceLocation TEXTURE_blue = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_blue.png");
    protected static final ResourceLocation TEXTURE_purple = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_purple.png");
    protected static final ResourceLocation TEXTURE_black = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_black.png");
    protected static final ResourceLocation TEXTURE_white = new ResourceLocation(Slivers.MOD_ID, "textures/entity/sliver_white.png");

    @Override
    public ResourceLocation getEntityTexture(SliverEntity entity) {
        if (entity instanceof SliverEntity) {
            switch (((SliverEntity)entity).getVariant()) {
                case 0:
                    return TEXTURE_basic;
                case 1:
                    return TEXTURE_red;
                case 2:
                    return TEXTURE_orange;
                case 3:
                    return TEXTURE_yellow;
                case 4:
                    return TEXTURE_green;
                case 5:
                    return TEXTURE_blue;
                case 6:
                    return TEXTURE_purple;
                case 7:
                    return TEXTURE_black;
                case 8:
                    return TEXTURE_white;
            }
        }
        return TEXTURE_basic;
    }
}
