package net.omen.eclipsemod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.networking.packets.ExamplePacketC2S;
import net.omen.eclipsemod.networking.packets.PlayerPowerSyncPacket;

public class ModPackets {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {

        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(EclipseMod.MOD_ID, "packets"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExamplePacketC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExamplePacketC2S::new)
                .encoder(ExamplePacketC2S::toBytes)
                .consumerMainThread(ExamplePacketC2S::handle)
                .add();

        net.messageBuilder(PlayerPowerSyncPacket.class, id())
                .decoder(PlayerPowerSyncPacket::new)
                .encoder(PlayerPowerSyncPacket::toBytes)
                .consumerMainThread(PlayerPowerSyncPacket::handle)
                .add();


    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
