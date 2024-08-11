package net.omen.eclipsemod.power.powers;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.omen.eclipsemod.power.PowerHandler;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.ExamplePacketC2S;

import java.util.ArrayList;
import java.util.List;

public class TestingPower implements PowerHandler.Power {

    @Override
    public void apply(Player player) {
        player.getPersistentData().putBoolean("hasTestingPower", true);
        abilitiesTesting(player);
    }

    @Override
    public void remove(Player player) {
        player.getPersistentData().remove("hasTestingPower");
        abilitiesTesting(player);
    }

    @Override
    public String getName() {
        return "Testing";
    }

    @Override
    public String getTier() {
        return "D";
    }

    @Override
    public String getDescription() {
        return "For Testing";
    }

    @Override
    public List<PowerHandler.Ability> getAbilities() {
        List<PowerHandler.Ability> abilities = new ArrayList<>();
        abilities.add(new TestingAbility());
        abilities.add(new TestingAbilityTwo());
        return abilities;
    }

    private void abilitiesTesting(Player player) {
        boolean hasTestingPower = player.getPersistentData().getBoolean("hasTestingPower");

        if (hasTestingPower) {
            // Add any special logic for the TestingPower here
        }
    }

    // Define the TestingAbility
    public static class TestingAbility implements PowerHandler.Ability {
        @Override
        public void execute(ServerPlayer player) {
            ModPackets.sendToServer(new ExamplePacketC2S()); // This spawns a cow on the server
        }

        @Override
        public String getName() {
            return "Spawn Cow";
        }
    }

    // Define the TestingAbilityTwo (Ability 2)
    public static class TestingAbilityTwo implements PowerHandler.Ability {
        @Override
        public void execute(ServerPlayer player) {
            // You can add a different packet or logic here
            player.sendSystemMessage(Component.literal("Ability Two Activated"));
        }

        @Override
        public String getName() {
            return "Send message";
        }
    }

}
