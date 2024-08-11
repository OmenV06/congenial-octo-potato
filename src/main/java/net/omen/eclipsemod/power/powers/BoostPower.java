package net.omen.eclipsemod.power.powers;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.omen.eclipsemod.power.PowerHandler;
import java.util.UUID;

public class BoostPower implements PowerHandler.Power {
    private static final UUID BOOST_HEALTH_MODIFIER_UUID = UUID.fromString("c4183a03-88a2-471e-9906-88be52d3411a");
    private static final UUID BOOST_ARMOR_MODIFIER_UUID = UUID.fromString("fa233e1c-4180-4865-b01b-abc1a4175a5c");
    private static final UUID BOOST_TOUGHNESS_MODIFIER_UUID = UUID.fromString("d845a72d-1825-445b-a38f-485a5f13c9d4");
    private static final UUID BOOST_STRENGTH_MODIFIER_UUID = UUID.fromString("52f530d8-68f4-47b8-87c6-8f7268edf927");
    private static final UUID BOOST_ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("6adf8fc8-a34d-4b23-bb50-48d7b5c8e029");
    private static final UUID BOOST_SPEED_MODIFIER_UUID = UUID.fromString("662a6b8d-0126-4b2b-8b60-5c84c7d7296f");

    @Override
    public void apply(Player player) {
        player.getPersistentData().putBoolean("hasBoostPower", true);
        abilitiesBoost(player);
    }

    @Override
    public void remove(Player player) {
        player.getPersistentData().remove("hasBoostPower");
        abilitiesBoost(player);
    }

    @Override
    public String getName() {
        return "Boost";
    }

    @Override
    public String getTier() {
        return "B";
    }

    @Override
    public String getDescription() {
        return "+20 Maximum Health, +10 Passive Armor, +2 Passive Toughness, +10 Attack Damage, +0.5 Attack Speed, +0.02 Movement Speed";
    }

    private void abilitiesBoost(Player player) {
        boolean hasBoostPower = player.getPersistentData().getBoolean("hasBoostPower");

        if (hasBoostPower) {
            double currentHealth = player.getHealth();
            double maxHealth = player.getMaxHealth();

            AttributeModifier boostHealth = new AttributeModifier(BOOST_HEALTH_MODIFIER_UUID, "BoostHealth", 20.0, AttributeModifier.Operation.ADDITION);
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(boostHealth);

            double newMaxHealth = player.getMaxHealth();
            player.setHealth((float) Math.min(currentHealth + 20.0, newMaxHealth));

            player.getAttribute(Attributes.ARMOR).addTransientModifier(new AttributeModifier(BOOST_ARMOR_MODIFIER_UUID,
                    "BoostArmor", 10, AttributeModifier.Operation.ADDITION));

            player.getAttribute(Attributes.ARMOR_TOUGHNESS).addTransientModifier(new AttributeModifier(BOOST_TOUGHNESS_MODIFIER_UUID,
                    "BoostToughness", 2, AttributeModifier.Operation.ADDITION));

            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(BOOST_STRENGTH_MODIFIER_UUID,
                    "BoostStrength", 10, AttributeModifier.Operation.ADDITION));

            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier(BOOST_ATTACK_SPEED_MODIFIER_UUID,
                    "BoostAttackSpeed", 0.5, AttributeModifier.Operation.ADDITION));

            player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier(BOOST_SPEED_MODIFIER_UUID,
                    "BoostSpeed", 0.02, AttributeModifier.Operation.ADDITION));
        } else {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(BOOST_HEALTH_MODIFIER_UUID);
            player.getAttribute(Attributes.ARMOR).removeModifier(BOOST_ARMOR_MODIFIER_UUID);
            player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(BOOST_TOUGHNESS_MODIFIER_UUID);
            player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(BOOST_STRENGTH_MODIFIER_UUID);
            player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(BOOST_ATTACK_SPEED_MODIFIER_UUID);
            player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(BOOST_SPEED_MODIFIER_UUID);

            float newMaxHealth = (float) player.getMaxHealth();
            if (player.getHealth() > newMaxHealth) {
                player.setHealth(newMaxHealth);
            }
        }
    }
}