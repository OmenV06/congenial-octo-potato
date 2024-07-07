package net.omen.eclipsemod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.client.gui.PlayerMenu;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.ExamplePacketC2S;
import net.omen.eclipsemod.util.KeyBindings;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {

            if (KeyBindings.PLAYER_MENU.consumeClick()) {

                Minecraft.getInstance().setScreen(new PlayerMenu(Component.literal("Player Menu")));

            }

            if (KeyBindings.TEMP_KEY.consumeClick()) {

                ModPackets.sendToServer(new ExamplePacketC2S());

            }


        }

    }



    @Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CLientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {

            event.register(KeyBindings.PLAYER_MENU);
            event.register(KeyBindings.TEMP_KEY);

        }

    }

}
