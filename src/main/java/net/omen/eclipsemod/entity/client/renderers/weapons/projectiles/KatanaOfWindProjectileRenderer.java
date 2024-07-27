package net.omen.eclipsemod.entity.client.renderers.weapons.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.KatanaOfWindProjectileEntity;
import net.omen.eclipsemod.init.ModItems;

public class KatanaOfWindProjectileRenderer extends EntityRenderer<KatanaOfWindProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public KatanaOfWindProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(KatanaOfWindProjectileEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.translate(0f, -0.25f, 0f);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-entity.getXRot()));

        ItemStack itemStack = new ItemStack(ModItems.KATANA_OF_WIND_PROJECTILE_ITEM.get());
        BakedModel model = this.itemRenderer.getModel(itemStack, entity.getLevel(), null, entity.getId());
        this.itemRenderer.render(itemStack, ItemTransforms.TransformType.GROUND, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(KatanaOfWindProjectileEntity entity) {
        return new ResourceLocation(EclipseMod.MOD_ID, "textures/item/katana_of_wind_projectile.png");
    }
}