package net.omen.eclipsemod.power.powers;

import net.minecraft.world.entity.player.Player;
import net.omen.eclipsemod.power.PowerHandler;

public class NonePower implements PowerHandler.Power {

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public String getName() {
        return "None";
    }

    @Override
    public String getTier() {
        return "None";
    }

    @Override
    public String getDescription() {
        return "";
    }

}
