package net.omen.eclipsemod.power;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.power.powers.AirWalkPower;
import net.omen.eclipsemod.power.powers.BoostPower;
import net.omen.eclipsemod.power.powers.VitalityPower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class PowerHandler {

    public interface Power {
        void apply(Player player);
        void remove(Player player);
        String getName();
        String getTier();
        String getDescription();
    }

    private static final String PLAYER_POWER_KEY = "PlayerPower";
    private static final Random RANDOM_GENERATOR = new Random();
    public static final List<Class<? extends Power>> REGISTERED_POWERS = new ArrayList<>();

    static {
        REGISTERED_POWERS.add(BoostPower.class);
        REGISTERED_POWERS.add(VitalityPower.class);
        REGISTERED_POWERS.add(AirWalkPower.class);
    }

    public static void assignRandomPower(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        if (!persistentData.contains(PLAYER_POWER_KEY)) {
            int randomIndex = RANDOM_GENERATOR.nextInt(REGISTERED_POWERS.size());
            try {
                Power power = REGISTERED_POWERS.get(randomIndex).newInstance();
                power.apply(player);
                savePlayerPower(player, power.getName());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPlayerPowerName(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag playerData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
        return playerData.getString(PLAYER_POWER_KEY);
    }

    public static String getPowerDescription(String powerName) {
        for (Class<? extends Power> powerClass : REGISTERED_POWERS) {
            try {
                Power power = powerClass.newInstance();
                if (power.getName().equals(powerName)) {
                    return power.getDescription();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void savePlayerPower(ServerPlayer player, String powerName) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag playerData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
        playerData.putString(PLAYER_POWER_KEY, powerName);
        persistentData.put(Player.PERSISTED_NBT_TAG, playerData);
    }

    public static void removeCurrentPower(ServerPlayer player) {
        String powerName = getPlayerPowerName(player);
        for (Class<? extends Power> powerClass : REGISTERED_POWERS) {
            try {
                Power power = powerClass.newInstance();
                if (power.getName().equals(powerName)) {
                    power.remove(player);
                    break;
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reapplySavedPower(ServerPlayer player, String powerName) {
        for (Class<? extends Power> powerClass : REGISTERED_POWERS) {
            try {
                Power power = powerClass.newInstance();
                if (power.getName().equals(powerName)) {
                    power.apply(player);
                    break;
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            String powerName = getPlayerPowerName(player);
            if (powerName.isEmpty()) {
                assignRandomPower(player);
            } else {
                reapplySavedPower(player, powerName);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            ServerPlayer originalPlayer = (ServerPlayer) event.getOriginal();
            ServerPlayer newPlayer = (ServerPlayer) event.getEntity();

            String powerName = getPlayerPowerName(originalPlayer);
            savePlayerPower(newPlayer, powerName);

            reapplySavedPower(newPlayer, powerName);
        }
    }

}