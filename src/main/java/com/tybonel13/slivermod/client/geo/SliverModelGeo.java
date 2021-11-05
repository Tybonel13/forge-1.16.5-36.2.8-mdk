package com.tybonel13.slivermod.client.geo;

import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.entities.SliverEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SliverModelGeo extends AnimatedGeoModel {

    @Override
    public ResourceLocation getModelLocation(Object entity) {
        return new ResourceLocation(Slivers.MOD_ID, "geo/sliver.geo.json");
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
    public ResourceLocation getTextureLocation(Object entity) {
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

    @Override
    public ResourceLocation getAnimationFileLocation(Object entity) {
        return new ResourceLocation(Slivers.MOD_ID, "animations/sliver_animations.json");
    }

    @Override
    public void setLivingAnimations(IAnimatable entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone h_head = this.getAnimationProcessor().getBone("h_head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        h_head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        h_head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

    }
}
