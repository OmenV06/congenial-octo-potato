package net.omen.eclipsemod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.PlayerPowerSyncPacket;

public class PlayerMenu extends Screen {
    private static final ResourceLocation MENU_BACKGROUND_TEXTURE = new ResourceLocation("eclipsemod", "textures/gui/player_menu.png");
    private int menuWidth = 256;
    private int menuHeight = 256;
    private String playerPowerName = "None";
    private String powerDescription = "";

    public PlayerMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        ModPackets.sendToServer(new PlayerPowerSyncPacket());
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderTexture(0, MENU_BACKGROUND_TEXTURE);
        int x = (this.width - this.menuWidth) / 2;
        int y = (this.height - this.menuHeight) / 2;
        blit(poseStack, x, y, 0, 0, this.menuWidth, this.menuHeight);

        String username = Minecraft.getInstance().player.getName().getString();
        drawString(poseStack, this.font, username, x + 118, y + 16, 0xFFFFFF);
        drawString(poseStack, this.font, playerPowerName, x + 118, y + 36, 0xFFFFFF);
        drawString(poseStack, this.font, powerDescription, x + 118, y + 56, 0xFFFFFF);

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    @OnlyIn(Dist.CLIENT)
    public void updatePowerInfo(String powerName, String description) {
        this.playerPowerName = powerName;
        this.powerDescription = description;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }
}