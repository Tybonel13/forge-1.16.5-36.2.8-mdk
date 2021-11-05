package com.tybonel13.slivermod.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tybonel13.slivermod.entities.SliverEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class SliverModel<T extends SliverEntity> extends EntityModel<T> {

        private final ModelRenderer h_head;
        private final ModelRenderer HeadMidEnd;
        private final ModelRenderer HeadMid_r1;
        private final ModelRenderer HeadEnd_r1;
        private final ModelRenderer Hair;
        private final ModelRenderer HairMid_r1;
        private final ModelRenderer HairRight_r1;
        private final ModelRenderer Shoulder;
        private final ModelRenderer Arm;
        private final ModelRenderer Arm_r1;
        private final ModelRenderer ForeArm;
        private final ModelRenderer Claw_r1;
        private final ModelRenderer ForeArm_r1;
        private final ModelRenderer Body;
        private final ModelRenderer LowerBody_r1;
        private final ModelRenderer LowerTorso_r1;
        private final ModelRenderer Neck;
        private final ModelRenderer Neck_r1;
        private final ModelRenderer Tail;
        private final ModelRenderer LowerTail;
        private final ModelRenderer Tail_r1;
        private final ModelRenderer TailLeft;
        private final ModelRenderer TailSplitLeft_r1;
        private final ModelRenderer EndLeft;
        private final ModelRenderer TailEndLeft_r1;
        private final ModelRenderer TailRight;
        private final ModelRenderer TailSplitRight_r1;
        private final ModelRenderer EndRight;
        private final ModelRenderer TailEndRight_r1;

        public SliverModel() {
            textureWidth = 64;
            textureHeight = 64;

            h_head = new ModelRenderer(this);
            h_head.setRotationPoint(0.0145F, 3.0315F, 0.5F);
            h_head.setTextureOffset(8, 31).addBox(-3.878F, -3.007F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
            h_head.setTextureOffset(0, 0).addBox(-2.878F, -3.007F, -2.5F, 7.0F, 4.0F, 5.0F, 0.0F, false);

            HeadMidEnd = new ModelRenderer(this);
            HeadMidEnd.setRotationPoint(3.9742F, -1.16F, 0.0F);
            h_head.addChild(HeadMidEnd);


            HeadMid_r1 = new ModelRenderer(this);
            HeadMid_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
            HeadMidEnd.addChild(HeadMid_r1);
            setRotationAngle(HeadMid_r1, 0.0F, 0.0F, 0.2182F);
            HeadMid_r1.setTextureOffset(26, 23).addBox(-0.1951F, -1.4988F, -2.0F, 5.0F, 3.0F, 4.0F, 0.0F, false);

            HeadEnd_r1 = new ModelRenderer(this);
            HeadEnd_r1.setRotationPoint(4.5575F, 1.084F, 0.0F);
            HeadMidEnd.addChild(HeadEnd_r1);
            setRotationAngle(HeadEnd_r1, 0.0F, 0.0F, 0.3927F);
            HeadEnd_r1.setTextureOffset(30, 4).addBox(-1.4804F, -1.1517F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

            Hair = new ModelRenderer(this);
            Hair.setRotationPoint(-4.1667F, -3.4601F, 0.0F);
            h_head.addChild(Hair);


            HairMid_r1 = new ModelRenderer(this);
            HairMid_r1.setRotationPoint(1.0F, 0.5F, 0.0F);
            Hair.addChild(HairMid_r1);
            setRotationAngle(HairMid_r1, 0.0F, 0.0F, 0.4363F);
            HairMid_r1.setTextureOffset(13, 14).addBox(-2.6176F, -0.0305F, -0.5F, 3.0F, 0.0F, 1.0F, 0.0F, false);

            HairRight_r1 = new ModelRenderer(this);
            HairRight_r1.setRotationPoint(1.4532F, 0.7113F, -2.0F);
            Hair.addChild(HairRight_r1);
            setRotationAngle(HairRight_r1, 0.0F, 0.0F, 0.4363F);
            HairRight_r1.setTextureOffset(0, 0).addBox(-2.1176F, -0.0305F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, false);
            HairRight_r1.setTextureOffset(0, 1).addBox(-2.1176F, -0.0305F, 3.5F, 2.0F, 0.0F, 1.0F, 0.0F, false);

            Shoulder = new ModelRenderer(this);
            Shoulder.setRotationPoint(1.0519F, 8.8551F, 0.5F);
            Shoulder.setTextureOffset(30, 16).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

            Arm = new ModelRenderer(this);
            Arm.setRotationPoint(1.2589F, 0.0168F, 0.0F);
            Shoulder.addChild(Arm);


            Arm_r1 = new ModelRenderer(this);
            Arm_r1.setRotationPoint(0.2411F, -0.0168F, 0.0F);
            Arm.addChild(Arm_r1);
            setRotationAngle(Arm_r1, 0.0F, 0.0F, 0.3054F);
            Arm_r1.setTextureOffset(19, 0).addBox(-3.6129F, -0.4822F, -0.5F, 10.0F, 1.0F, 1.0F, 0.0F, false);

            ForeArm = new ModelRenderer(this);
            ForeArm.setRotationPoint(6.0F, 1.75F, 0.0F);
            Arm.addChild(ForeArm);


            Claw_r1 = new ModelRenderer(this);
            Claw_r1.setRotationPoint(-0.5162F, -3.4394F, 0.0F);
            ForeArm.addChild(Claw_r1);
            setRotationAngle(Claw_r1, 0.0F, 0.0F, 3.0543F);
            Claw_r1.setTextureOffset(16, 34).addBox(-4.9187F, -0.8862F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, false);

            ForeArm_r1 = new ModelRenderer(this);
            ForeArm_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
            ForeArm.addChild(ForeArm_r1);
            setRotationAngle(ForeArm_r1, 0.0F, 0.0F, 1.2654F);
            ForeArm_r1.setTextureOffset(32, 30).addBox(-4.6009F, -0.8429F, -0.5F, 5.0F, 1.0F, 1.0F, -0.002F, false);

            Body = new ModelRenderer(this);
            Body.setRotationPoint(3.7073F, 12.442F, 0.5F);
            setRotationAngle(Body, 0.0F, 0.0F, 0.0436F);
            Body.setTextureOffset(0, 9).addBox(-6.1554F, -6.0868F, -3.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);

            LowerBody_r1 = new ModelRenderer(this);
            LowerBody_r1.setRotationPoint(-1.2505F, 0.1277F, 0.0F);
            Body.addChild(LowerBody_r1);
            setRotationAngle(LowerBody_r1, 0.0F, 0.0F, 0.1745F);
            LowerBody_r1.setTextureOffset(0, 19).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);

            LowerTorso_r1 = new ModelRenderer(this);
            LowerTorso_r1.setRotationPoint(-1.1135F, -0.0255F, 0.0F);
            Body.addChild(LowerTorso_r1);
            setRotationAngle(LowerTorso_r1, 0.0F, 0.0F, -0.6545F);
            LowerTorso_r1.setTextureOffset(19, 4).addBox(-1.6793F, -4.2499F, -2.5F, 3.0F, 5.0F, 5.0F, 0.0F, false);

            Neck = new ModelRenderer(this);
            Neck.setRotationPoint(-4.1135F, -4.0255F, 0.0F);
            Body.addChild(Neck);


            Neck_r1 = new ModelRenderer(this);
            Neck_r1.setRotationPoint(-0.0215F, -1.9327F, 0.0F);
            Neck.addChild(Neck_r1);
            setRotationAngle(Neck_r1, 0.0F, 0.0F, 0.1309F);
            Neck_r1.setTextureOffset(14, 27).addBox(-1.6632F, -3.2393F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

            Tail = new ModelRenderer(this);
            Tail.setRotationPoint(2.4567F, 12.3196F, 0.5F);
            setRotationAngle(Tail, 0.0F, 0.0F, 0.1745F);


            LowerTail = new ModelRenderer(this);
            LowerTail.setRotationPoint(0.0F, 7.0F, 0.0F);
            Tail.addChild(LowerTail);
            setRotationAngle(LowerTail, 0.0F, 0.0F, 0.0436F);


            Tail_r1 = new ModelRenderer(this);
            Tail_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
            LowerTail.addChild(Tail_r1);
            setRotationAngle(Tail_r1, 0.0F, 0.0F, 0.7854F);
            Tail_r1.setTextureOffset(16, 15).addBox(-1.6036F, -0.4926F, -2.0F, 3.0F, 8.0F, 4.0F, -0.002F, false);

            TailLeft = new ModelRenderer(this);
            TailLeft.setRotationPoint(-4.8475F, 5.0917F, 1.0F);
            LowerTail.addChild(TailLeft);
            setRotationAngle(TailLeft, 0.0F, 0.0F, 0.1745F);


            TailSplitLeft_r1 = new ModelRenderer(this);
            TailSplitLeft_r1.setRotationPoint(-3.75F, 1.5F, 0.0F);
            TailLeft.addChild(TailSplitLeft_r1);
            setRotationAngle(TailSplitLeft_r1, 0.1309F, 0.0F, 1.1781F);
            TailSplitLeft_r1.setTextureOffset(26, 30).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            EndLeft = new ModelRenderer(this);
            EndLeft.setRotationPoint(-6.75F, 3.0F, 1.0F);
            TailLeft.addChild(EndLeft);


            TailEndLeft_r1 = new ModelRenderer(this);
            TailEndLeft_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
            EndLeft.addChild(TailEndLeft_r1);
            setRotationAngle(TailEndLeft_r1, 0.0F, 0.0F, 0.0F);
            TailEndLeft_r1.setTextureOffset(26, 14).addBox(-8.25F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);

            TailRight = new ModelRenderer(this);
            TailRight.setRotationPoint(-4.8475F, 5.0917F, -1.0F);
            LowerTail.addChild(TailRight);
            setRotationAngle(TailRight, 0.0F, 0.0F, 0.1745F);


            TailSplitRight_r1 = new ModelRenderer(this);
            TailSplitRight_r1.setRotationPoint(-3.75F, 1.5F, 0.0F);
            TailRight.addChild(TailSplitRight_r1);
            setRotationAngle(TailSplitRight_r1, -0.1309F, 0.0F, 1.1781F);
            TailSplitRight_r1.setTextureOffset(0, 31).addBox(-1.0F, -4.0F, -1.5F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            EndRight = new ModelRenderer(this);
            EndRight.setRotationPoint(-6.75F, 3.0F, -1.0F);
            TailRight.addChild(EndRight);


            TailEndRight_r1 = new ModelRenderer(this);
            TailEndRight_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
            EndRight.addChild(TailEndRight_r1);
            setRotationAngle(TailEndRight_r1, 0.0F, 0.0F, 0.0F);
            TailEndRight_r1.setTextureOffset(19, 2).addBox(-8.25F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.h_head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.h_head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.Tail.rotateAngleX = ((float)Math.PI / 180F);
    }

        @Override
        public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
            h_head.render(matrixStack, buffer, packedLight, packedOverlay);
            Shoulder.render(matrixStack, buffer, packedLight, packedOverlay);
            Body.render(matrixStack, buffer, packedLight, packedOverlay);
            Tail.render(matrixStack, buffer, packedLight, packedOverlay);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }
    }
