package com.kashdeya.trolloresreborn.entity.render;

import com.kashdeya.trolloresreborn.entity.EntitySmallWither;
import com.kashdeya.trolloresreborn.entity.model.ModelSmallWither;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSmallWitherAura implements LayerRenderer<EntitySmallWither> {
	private static final ResourceLocation WITHER_ARMOR = new ResourceLocation("textures/entity/wither/wither_armor.png");
	private final RenderSmallWither witherRenderer;
	private final ModelSmallWither witherModel = new ModelSmallWither(0.5F);

	public LayerSmallWitherAura(RenderSmallWither renderSmallWither) {
		this.witherRenderer = renderSmallWither;
	}

	@Override
	public void doRenderLayer(EntitySmallWither entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.isArmored()) {
			GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
			this.witherRenderer.bindTexture(WITHER_ARMOR);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			float f = (float) entitylivingbaseIn.ticksExisted + partialTicks;
			float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
			float f2 = f * 0.01F;
			GlStateManager.translate(f1, f2, 0.0F);
			GlStateManager.matrixMode(5888);
			GlStateManager.enableBlend();
			float f3 = 0.5F;
			GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
			GlStateManager.disableLighting();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			this.witherModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.witherModel.setModelAttributes(this.witherRenderer.getMainModel());
			this.witherModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
					scale);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(5888);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
