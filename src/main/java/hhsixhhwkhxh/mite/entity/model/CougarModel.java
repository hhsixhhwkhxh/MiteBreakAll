// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package hhsixhhwkhxh.mite.entity.model;

import hhsixhhwkhxh.mite.entity.animation.ElephantAnimation;
import hhsixhhwkhxh.mite.entity.renderer.CougarRenderState;
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

public class CougarModel extends EntityModel<CougarRenderState>{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true,10,2,2,1.5F,12,Set.of("head"));
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;


//	private final KeyframeAnimation earFlapAnimation;
//	private final KeyframeAnimation tailFlickAnimation;
//	private final KeyframeAnimation trunkCurlAnimation;

	public CougarModel(ModelPart root) {
        super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.leftFrontLeg = this.body.getChild("left_front_leg");
		this.leftHindLeg = this.body.getChild("left_hind_leg");
		this.rightFrontLeg = this.body.getChild("right_front_leg");
		this.rightHindLeg = this.body.getChild("right_hind_leg");

//		earFlapAnimation = ElephantAnimation.ELEPHANT_EAR_FLAP.bake(root);
//		tailFlickAnimation = ElephantAnimation.ELEPHANT_TAIL_FLICK.bake(root);
//		trunkCurlAnimation = ElephantAnimation.ELEPHANT_TRUNK_CURL.bake(root);

	}

	public static MeshDefinition createMeshDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 6).addBox(-2.5F, -2.0F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 5).addBox(-1.5F, 2.0F, -7.75F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(23, 9).addBox(-2.0F, 0.0F, -8.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -10.0F));

		PartDefinition neck_r1 = head.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(40, 12).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition left_ear_r1 = head.addOrReplaceChild("left_ear_r1", CubeListBuilder.create().texOffs(17, 4).mirror().addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.25F, -1.0F, -0.2618F, 0.0F, 0.3054F));

		PartDefinition right_ear_r1 = head.addOrReplaceChild("right_ear_r1", CubeListBuilder.create().texOffs(17, 4).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.25F, -1.0F, -0.2618F, 0.0F, -0.3054F));

		PartDefinition nose_r1 = head.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(4, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -5.25F, 0.1309F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 47).addBox(-3.5F, -2.0F, 3.0F, 7.0F, 8.0F, 9.0F, new CubeDeformation(0.1F))
				.texOffs(33, 31).addBox(-3.0F, -1.5F, 12.0F, 6.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -10.0F));

		PartDefinition neck2_r1 = body.addOrReplaceChild("neck2_r1", CubeListBuilder.create().texOffs(38, 21).addBox(-5.0F, -6.0F, -1.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.0F, 4.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 21.0F));

		PartDefinition tip_r1 = tail.addOrReplaceChild("tip_r1", CubeListBuilder.create().texOffs(13, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 6.1101F, 2.9587F, 0.8727F, 0.0F, 0.0F));

		PartDefinition middle_r1 = tail.addOrReplaceChild("middle_r1", CubeListBuilder.create().texOffs(13, 23).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.675F, 0.65F, 0.48F, 0.0F, 0.0F));

		PartDefinition root_r1 = tail.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(11, 28).addBox(-1.0F, 0.0181F, -2.018F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.5254F, -0.0012F, 0.829F, 0.0F, 0.0F));

		PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 53).addBox(-1.0F, -2.1F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(-0.01F))
				.texOffs(1, 33).addBox(-1.0F, 13.0F, -0.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, 5.0F));

		PartDefinition ankle_r1 = left_front_leg.addOrReplaceChild("ankle_r1", CubeListBuilder.create().texOffs(2, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 11.5F, 2.25F, -0.2182F, 0.0F, 0.0F));

		PartDefinition calf_r1 = left_front_leg.addOrReplaceChild("calf_r1", CubeListBuilder.create().texOffs(1, 43).addBox(-0.95F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, 4.6F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition left_hind_leg = body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 53).addBox(-1.0F, -2.1F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(-0.01F))
				.texOffs(1, 33).addBox(-1.0F, 13.0F, -0.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, 17.0F));

		PartDefinition ankle_r2 = left_hind_leg.addOrReplaceChild("ankle_r2", CubeListBuilder.create().texOffs(2, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 11.5F, 2.25F, -0.2182F, 0.0F, 0.0F));

		PartDefinition calf_r2 = left_hind_leg.addOrReplaceChild("calf_r2", CubeListBuilder.create().texOffs(1, 43).addBox(-0.95F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, 4.6F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-1.0F, -2.1F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(1, 33).mirror().addBox(-1.0F, 13.0F, -0.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 0.0F, 5.0F));

		PartDefinition ankle_r3 = right_front_leg.addOrReplaceChild("ankle_r3", CubeListBuilder.create().texOffs(2, 38).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.5F, 2.25F, -0.2182F, 0.0F, 0.0F));

		PartDefinition calf_r3 = right_front_leg.addOrReplaceChild("calf_r3", CubeListBuilder.create().texOffs(1, 43).mirror().addBox(-1.05F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.05F, 4.6F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition right_hind_leg = body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-1.0F, -2.1F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(1, 33).mirror().addBox(-1.0F, 13.0F, -0.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 0.0F, 17.0F));

		PartDefinition ankle_r4 = right_hind_leg.addOrReplaceChild("ankle_r4", CubeListBuilder.create().texOffs(2, 38).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.5F, 2.25F, -0.2182F, 0.0F, 0.0F));

		PartDefinition calf_r4 = right_hind_leg.addOrReplaceChild("calf_r4", CubeListBuilder.create().texOffs(1, 43).mirror().addBox(-1.05F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.05F, 4.6F, 1.0F, 0.1745F, 0.0F, 0.0F));

		return meshdefinition;
	}

	@Override
	public void setupAnim(@NotNull CougarRenderState renderState) {
		super.setupAnim(renderState);

		float walkAnimationPos = renderState.walkAnimationPos;
		float walkAnimationSpeed = renderState.walkAnimationSpeed;

		this.rightHindLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F) * 1.4F * walkAnimationSpeed;
		this.leftHindLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * walkAnimationSpeed;
		this.rightFrontLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * walkAnimationSpeed;
		this.leftFrontLeg.xRot = Mth.cos(walkAnimationPos * 0.6662F) * 1.4F * walkAnimationSpeed;

		this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);

//		earFlapAnimation.apply(renderState.earFlapAnimationState, renderState.ageInTicks);
//		tailFlickAnimation.apply(renderState.tailFlickAnimationState, renderState.ageInTicks);
//		trunkCurlAnimation.apply(renderState.trunkCurlAnimationState, renderState.ageInTicks);


	}

}