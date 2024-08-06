package net.omen.eclipsemod.power;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class AbilityBar {
    private static boolean isActive = false;

    public static void toggle() {
        isActive = !isActive;
        if (isActive) {
            displayMessage("Ability Bar Enabled");
        } else {
            displayMessage("Ability Bar Disabled");
        }



    }

    public static boolean isActive() {
        return isActive;
    }

    private static void displayMessage(String message) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal(message), true);
    }



}
