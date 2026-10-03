// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package hhsixhhwkhxh.mite.entity.model;

import hhsixhhwkhxh.mite.entity.animation.ElephantAnimation;
import hhsixhhwkhxh.mite.entity.renderer.ElephantRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ElephantModel extends EntityModel<ElephantRenderState>{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true,10,2,2,1.5F,12,Set.of("head"));
	private final ModelPart head;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart trunk;
	private final ModelPart part1;
	private final ModelPart part2;
	private final ModelPart part3;
	private final ModelPart part4;
	private final ModelPart part5;
	private final ModelPart Ivory;
	private final ModelPart right;
	private final ModelPart left;
	private final ModelPart body;
	private final ModelPart limb;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart tail;


	private final KeyframeAnimation earFlapAnimation;
	private final KeyframeAnimation tailFlickAnimation;
	private final KeyframeAnimation trunkCurlAnimation;

	public ElephantModel(ModelPart root) {
        super(root);
		this.head = root.getChild("head");
		this.left_ear = this.head.getChild("left_ear");
		this.right_ear = this.head.getChild("right_ear");
		this.trunk = this.head.getChild("trunk");
		this.part1 = this.trunk.getChild("part1");
		this.part2 = this.part1.getChild("part2");
		this.part3 = this.part2.getChild("part3");
		this.part4 = this.part3.getChild("part4");
		this.part5 = this.part4.getChild("part5");
		this.Ivory = this.head.getChild("Ivory");
		this.right = this.Ivory.getChild("right");
		this.left = this.Ivory.getChild("left");
		this.body = root.getChild("body");
		this.limb = this.body.getChild("limb");
		this.leftFrontLeg = this.limb.getChild("left_front");
		this.leftHindLeg = this.limb.getChild("left_hind");
		this.rightFrontLeg = this.limb.getChild("right_front");
		this.rightHindLeg = this.limb.getChild("right_hind");
		this.tail = this.body.getChild("tail");

		earFlapAnimation = ElephantAnimation.ELEPHANT_EAR_FLAP.bake(root);
		tailFlickAnimation = ElephantAnimation.ELEPHANT_TAIL_FLICK.bake(root);
		trunkCurlAnimation = ElephantAnimation.ELEPHANT_TRUNK_CURL.bake(root);

	}

	public static MeshDefinition createMeshDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(60, 0).addBox(-5.5F, -7.4749F, -10.7918F, 11.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -9.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(3.0F, -1.0F, -6.0F));

		PartDefinition cube_r1 = left_ear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(102, 0).mirror().addBox(-0.3137F, -0.151F, -1.1284F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.4749F, 0.2082F, 0.175F, -0.1888F, -0.8621F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(-3.0F, -1.4749F, -5.7918F));

		PartDefinition cube_r2 = right_ear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(102, 0).addBox(-7.6863F, -0.151F, -1.1284F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.175F, 0.1888F, 0.8621F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.3055F, -10.1391F, 0.1745F, 0.0F, 0.0F));

		PartDefinition part1 = trunk.addOrReplaceChild("part1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0833F, -0.2606F));

		PartDefinition cube1_r1 = part1.addOrReplaceChild("cube1_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-4.0F, -7.0F, -9.5F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition part2 = part1.addOrReplaceChild("part2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 9.5F, 0.75F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube2_r1 = part2.addOrReplaceChild("cube2_r1", CubeListBuilder.create().texOffs(0, 93).addBox(-3.0F, -0.5412F, -6.5876F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.75F, 1.5708F, 0.0F, 0.0F));

		PartDefinition part3 = part2.addOrReplaceChild("part3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube3_r1 = part3.addOrReplaceChild("cube3_r1", CubeListBuilder.create().texOffs(0, 105).addBox(-2.5F, -0.2312F, -5.1053F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -0.25F, 1.5708F, 0.0F, 0.0F));

		PartDefinition part4 = part3.addOrReplaceChild("part4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		PartDefinition cube4_r1 = part4.addOrReplaceChild("cube4_r1", CubeListBuilder.create().texOffs(0, 114).addBox(-2.0F, 0.1224F, -5.1047F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0549F, -0.3574F, 1.5708F, 0.0F, 0.0F));

		PartDefinition part5 = part4.addOrReplaceChild("part5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.125F, 0.3142F, 0.0F, 0.0F));

		PartDefinition cube5_r1 = part5.addOrReplaceChild("cube5_r1", CubeListBuilder.create().texOffs(0, 122).addBox(-1.5F, 0.0326F, -3.9953F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2151F, -0.002F, 1.5708F, 0.0F, 0.0F));

		PartDefinition Ivory = head.addOrReplaceChild("Ivory", CubeListBuilder.create(), PartPose.offset(0.0F, 11.5251F, -19.2918F));

		PartDefinition right = Ivory.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, 12.0F));

		PartDefinition tip_r1 = right.addOrReplaceChild("tip_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7175F, -2.0024F, -6.7481F, 2.0F, 2.0F, 7.0F, new CubeDeformation(-0.0001F)), PartPose.offsetAndRotation(-1.4052F, 9.1532F, -2.9489F, 0.6074F, 0.13F, 0.1634F));

		PartDefinition root_r1 = right.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(2, 60).addBox(-1.2336F, -0.7313F, -9.4221F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1484F, 0.13F, 0.1634F));

		PartDefinition left = Ivory.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(4.0F, -4.0F, 12.0F));

		PartDefinition tip_r2 = left.addOrReplaceChild("tip_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.2825F, -2.0024F, -6.7481F, 2.0F, 2.0F, 7.0F, new CubeDeformation(-0.0001F)).mirror(false), PartPose.offsetAndRotation(1.4052F, 9.1532F, -2.9489F, 0.6074F, -0.13F, -0.1634F));

		PartDefinition root_r2 = left.addOrReplaceChild("root_r2", CubeListBuilder.create().texOffs(2, 60).mirror().addBox(-0.7664F, -0.7313F, -9.4221F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1484F, -0.13F, -0.1634F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.875F, 5.5F, 16.0F, 20.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(0, 48).addBox(-5.0F, -3.875F, 5.5F, 10.0F, 2.0F, 26.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -13.0F, -14.0F));

		PartDefinition neck_r1 = body.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(46, 48).addBox(-5.0F, -18.0F, 14.0F, 10.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.225F, -13.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition limb = body.addOrReplaceChild("limb", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_front = limb.addOrReplaceChild("left_front", CubeListBuilder.create().texOffs(100, 109).addBox(-7.0F, 0.125F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(100, 73).addBox(-7.0F, 12.105F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offset(8.0F, 15.0F, 9.0F));

		PartDefinition left_hind = limb.addOrReplaceChild("left_hind", CubeListBuilder.create().texOffs(100, 109).addBox(-7.0F, 0.125F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(100, 73).addBox(-7.0F, 12.105F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offset(8.0F, 15.0F, 30.0F));

		PartDefinition right_front = limb.addOrReplaceChild("right_front", CubeListBuilder.create().texOffs(100, 109).addBox(-7.0F, 0.125F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(100, 73).addBox(-7.0F, 12.105F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 15.0F, 9.0F));

		PartDefinition right_hind = limb.addOrReplaceChild("right_hind", CubeListBuilder.create().texOffs(100, 109).addBox(-7.0F, 0.125F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F))
				.texOffs(100, 73).addBox(-7.0F, 12.105F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.0F, 15.0F, 30.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 105).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 32.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition tip_r3 = tail.addOrReplaceChild("tip_r3", CubeListBuilder.create().texOffs(26, 76).addBox(-2.0F, 6.0F, -1.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 7.8042F, -0.4872F, -0.1309F, 0.0F, 0.0F));

		PartDefinition middle_r1 = tail.addOrReplaceChild("middle_r1", CubeListBuilder.create().texOffs(20, 117).addBox(-1.5F, 0.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.5F, 9.8042F, -0.4872F, -0.1309F, 0.0F, 0.0F));

		return meshdefinition;
	}

	@Override
	public void setupAnim(@NotNull ElephantRenderState renderState) {
		super.setupAnim(renderState);

		float walkAnimationPos = renderState.walkAnimationPos;
		float walkAnimationSpeed = renderState.walkAnimationSpeed;

		this.rightHindLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F) * 1.4F * walkAnimationSpeed;
		this.leftHindLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * walkAnimationSpeed;
		this.rightFrontLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * walkAnimationSpeed;
		this.leftFrontLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F) * 1.4F * walkAnimationSpeed;

		this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);

		earFlapAnimation.apply(renderState.earFlapAnimationState, renderState.ageInTicks);
		tailFlickAnimation.apply(renderState.tailFlickAnimationState, renderState.ageInTicks);
		trunkCurlAnimation.apply(renderState.trunkCurlAnimationState, renderState.ageInTicks);


	}

}