package net.omen.eclipsemod.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.omen.eclipsemod.power.PowerHandler;

import java.util.function.Supplier;

public class ExecuteAbilityPacket {
    private final int abilityIndex;

    public ExecuteAbilityPacket(int abilityIndex) {
        this.abilityIndex = abilityIndex;
    }

    public ExecuteAbilityPacket(FriendlyByteBuf buf) {
        this.abilityIndex = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(abilityIndex);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                PowerHandler.executeAbility(player, abilityIndex);
            }
        });
        context.setPacketHandled(true);
    }
}
