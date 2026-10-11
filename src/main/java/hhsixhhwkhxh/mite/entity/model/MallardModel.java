package hhsixhhwkhxh.mite.entity.model;

import hhsixhhwkhxh.mite.entity.renderer.MallardRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

//@OnlyIn(Dist.CLIENT)
public class MallardModel extends EntityModel<MallardRenderState> {
    //public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 5.0F, 2.0F, 2.0F, 1.99F, 24.0F, Set.of("head", "beak", "red_thing"));
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public MallardModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightLeg = this.body.getChild("right_leg");
        this.leftLeg = this.body.getChild("left_leg");
        this.rightWing = this.body.getChild("right_wing");
        this.leftWing = this.body.getChild("left_wing");
    }

    public static MeshDefinition createMeshDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(10, 22).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-1.5F, -2.0F, -4.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -3.0F));

        PartDefinition frontal_feathers_r1 = head.addOrReplaceChild("frontal_feathers_r1", CubeListBuilder.create().texOffs(17, 17).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 0.55F, -0.48F, 0.0F, 0.3927F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(10, 11).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -2.0F));

        PartDefinition tail_r1 = body.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(16, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 8.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(8, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, 2.0F));

        PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(8, 13).mirror().addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 1.0F, 2.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(19, 0).addBox(-1.5F, 1.9289F, -2.2993F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 3.0F));

        PartDefinition leg_r1 = left_leg.addOrReplaceChild("leg_r1", CubeListBuilder.create().texOffs(26, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 1.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(19, 0).addBox(-0.5F, 1.9289F, -2.2993F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, 3.0F));

        PartDefinition leg_r2 = right_leg.addOrReplaceChild("leg_r2", CubeListBuilder.create().texOffs(26, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 1.0F, -0.0873F, 0.0F, 0.0F));

        return meshdefinition;
    }

    public void setupAnim(@NotNull MallardRenderState renderState) {
        super.setupAnim(renderState);
        float f = (Mth.sin(renderState.flap) + 1.0F) * renderState.flapSpeed;
        this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        float f1 = renderState.walkAnimationSpeed;
        float f2 = renderState.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(f2 * 0.6662F) * 1.4F * f1;
        this.leftLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.rightWing.zRot = f;
        this.leftWing.zRot = -f;
    }
}
