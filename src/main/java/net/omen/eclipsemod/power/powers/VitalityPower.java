package net.omen.eclipsemod.power.powers;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.omen.eclipsemod.power.PowerHandler;

import java.util.UUID;

public class VitalityPower implements PowerHandler.Power {
    private static final UUID VITALITY_HEALTH_MODIFIER_UUID = UUID.fromString("c4183a03-88a2-471e-9906-88be52d34169");

    @Override
    public void apply(Player player) {
        player.getPersistentData().putBoolean("hasVitalityPower", true);
        abilitiesVitality(player);
    }

    @Override
    public void remove(Player player) {
        player.getPersistentData().remove("hasVitalityPower");
        abilitiesVitality(player);
    }

    @Override
    public String getName() {
        return "Vitality";
    }

    @Override
    public String getTier() {
        return "C";
    }

    @Override
    public String getDescription() {
        return "+60 Maximum Health";
    }

    private void abilitiesVitality(Player player) {
        boolean hasVitalityPower = player.getPersistentData().getBoolean("hasVitalityPower");

        if (hasVitalityPower) {
            double currentHealth = player.getHealth();  // Store current health before adding the modifier
            double maxHealth = player.getMaxHealth();   // Store current maximum health before modification

            // Add health boost
            AttributeModifier vitalityHealth = new AttributeModifier(VITALITY_HEALTH_MODIFIER_UUID, "VitalityHealth", 60.0, AttributeModifier.Operation.ADDITION);
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(vitalityHealth);

            // Set health to new max or to the sum of current health and boost, whichever is lower
            double newMaxHealth = player.getMaxHealth(); // Get new max health after modification
            player.setHealth((float) Math.min(currentHealth + 60.0, newMaxHealth)); // Prevent healing beyond added health
        } else {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(VITALITY_HEALTH_MODIFIER_UUID);

            float newMaxHealth = (float) player.getMaxHealth();
            if (player.getHealth() > newMaxHealth) {
                player.setHealth(newMaxHealth);
            }
        }
    }
}