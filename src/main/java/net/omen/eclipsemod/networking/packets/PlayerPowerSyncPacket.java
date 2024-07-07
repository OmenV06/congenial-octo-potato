package net.omen.eclipsemod.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.omen.eclipsemod.client.gui.PlayerMenu;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.power.PowerHandler;

import java.util.function.Supplier;

public class PlayerPowerSyncPacket {
    private final boolean isRequest;
    private String powerName;
    private String powerDescription;

    public PlayerPowerSyncPacket() {
        this.isRequest = true;
        this.powerName = "";
        this.powerDescription = "";
    }

    public PlayerPowerSyncPacket(String powerName, String powerDescription) {
        this.isRequest = false;
        this.powerName = powerName;
        this.powerDescription = powerDescription;
    }

    public PlayerPowerSyncPacket(FriendlyByteBuf buffer) {
        this.isRequest = buffer.readBoolean();
        this.powerName = buffer.readUtf(256);
        this.powerDescription = buffer.readUtf(256);
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isRequest);
        buffer.writeUtf(powerName);
        buffer.writeUtf(powerDescription);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
                ServerPlayer player = context.getSender();
                if (player != null) {
                    String powerName = PowerHandler.getPlayerPowerName(player);
                    String powerDescription = PowerHandler.getPowerDescription(powerName);
                    ModPackets.sendToPlayer(new PlayerPowerSyncPacket(powerName, powerDescription), player);
                }
            } else {
                handleClient(this.powerName, this.powerDescription);
            }
        });
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    private void handleClient(String powerName, String powerDescription) {
        Minecraft.getInstance().execute(() -> {
            if (Minecraft.getInstance().screen instanceof PlayerMenu) {
                ((PlayerMenu) Minecraft.getInstance().screen).updatePowerInfo(powerName, powerDescription);
            }
        });
    }
}