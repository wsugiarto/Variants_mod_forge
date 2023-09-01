package net.sharkron.variants_mod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.LlamaSpitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.entity.custom.CharcoalStaffBolt;

import com.mojang.math.Axis;

public class CharcoalStaffBoltRenderer extends EntityRenderer<CharcoalStaffBolt>{
    // Copies from Llama Spit Renderer

   private static final ResourceLocation CHARCOAL_STAFF_BOLT_LOCATION = new ResourceLocation(VariantsMod.MODID, "textures/entity/charcoal_staff_bolt.png");
   private final LlamaSpitModel<CharcoalStaffBolt> model;

   public CharcoalStaffBoltRenderer(EntityRendererProvider.Context p_174296_) {
      super(p_174296_);
      this.model = new LlamaSpitModel<>(p_174296_.bakeLayer(ModelLayers.LLAMA_SPIT));
   }

   public void render(CharcoalStaffBolt p_115373_, float p_115374_, float p_115375_, PoseStack p_115376_, MultiBufferSource p_115377_, int p_115378_) {
      p_115376_.pushPose();
      p_115376_.translate(0.0F, 0.15F, 0.0F);
      p_115376_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_115375_, p_115373_.yRotO, p_115373_.getYRot()) - 90.0F));
      p_115376_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_115375_, p_115373_.xRotO, p_115373_.getXRot())));
      this.model.setupAnim(p_115373_, p_115375_, 0.0F, -0.1F, 0.0F, 0.0F);
      VertexConsumer vertexconsumer = p_115377_.getBuffer(this.model.renderType(CHARCOAL_STAFF_BOLT_LOCATION));
      this.model.renderToBuffer(p_115376_, vertexconsumer, p_115378_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      p_115376_.popPose();
      super.render(p_115373_, p_115374_, p_115375_, p_115376_, p_115377_, p_115378_);
   }

   public ResourceLocation getTextureLocation(CharcoalStaffBolt p_115371_) {
      return CHARCOAL_STAFF_BOLT_LOCATION;
   }
    
}
