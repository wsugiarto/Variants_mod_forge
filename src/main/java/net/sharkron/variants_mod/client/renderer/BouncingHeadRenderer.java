package net.sharkron.variants_mod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sharkron.variants_mod.entity.custom.BouncingHead;

@OnlyIn(Dist.CLIENT)
public class BouncingHeadRenderer extends EntityRenderer<BouncingHead> {
   private static final ResourceLocation BOUNCING_INVULNERABLE_LOCATION = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation BOUNCING_LOCATION = new ResourceLocation("textures/entity/wither/wither.png");
   private final SkullModel model;

   public BouncingHeadRenderer(EntityRendererProvider.Context p_174449_) {
      super(p_174449_);
      this.model = new SkullModel(p_174449_.bakeLayer(ModelLayers.WITHER_SKULL));
   }

   public static LayerDefinition createSkullLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
      return LayerDefinition.create(meshdefinition, 64, 64);
   }

   protected int getBlockLightLevel(WitherSkull p_116491_, BlockPos p_116492_) {
      return 15;
   }

   public void render(WitherSkull p_116484_, float p_116485_, float p_116486_, PoseStack p_116487_, MultiBufferSource p_116488_, int p_116489_) {
      p_116487_.pushPose();
      p_116487_.scale(-1.0F, -1.0F, 1.0F);
      float f = Mth.rotLerp(p_116486_, p_116484_.yRotO, p_116484_.getYRot());
      float f1 = Mth.lerp(p_116486_, p_116484_.xRotO, p_116484_.getXRot());
      VertexConsumer vertexconsumer = p_116488_.getBuffer(this.model.renderType(this.getTextureLocation(p_116484_)));
      this.model.setupAnim(0.0F, f, f1);
      this.model.renderToBuffer(p_116487_, vertexconsumer, p_116489_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      p_116487_.popPose();
      super.render((BouncingHead) p_116484_, p_116485_, p_116486_, p_116487_, p_116488_, p_116489_);
   }

   public ResourceLocation getTextureLocation(WitherSkull p_116482_) {
      return p_116482_.isDangerous() ? BOUNCING_INVULNERABLE_LOCATION : BOUNCING_LOCATION;
   }

@Override
public ResourceLocation getTextureLocation(BouncingHead p_114482_) {
    
    throw new UnsupportedOperationException("Unimplemented method 'getTextureLocation'");
}
}