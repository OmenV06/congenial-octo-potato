package net.omen.eclipsemod.power.powers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.power.PowerHandler;

@Mod.EventBusSubscriber
public class AirWalkPower implements PowerHandler.Power {

    @Override
    public void apply(Player player) {
        player.getPersistentData().putBoolean("hasAirWalkPower", true);
        abilitiesAirWalk(player);
    }

    @Override
    public void remove(Player player) {
        player.getPersistentData().remove("hasAirWalkPower");
        abilitiesAirWalk(player);
    }

    @Override
    public String getName() {
        return "AirWalk";
    }

    @Override
    public String getTier() {
        return "B";
    }

    @Override
    public String getDescription() {
        return "Allows Flight and Prevents Fall Damage";
    }

    private void abilitiesAirWalk(Player player) {
        boolean hasAirWalkPower = player.getPersistentData().getBoolean("hasAirWalkPower");

        if (player instanceof ServerPlayer serverPlayer) {
            if (hasAirWalkPower) {
                if (serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                    serverPlayer.getAbilities().mayfly = true;
                    serverPlayer.onUpdateAbilities();
                }
            } else {
                if (serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                    serverPlayer.getAbilities().mayfly = false;
                    serverPlayer.getAbilities().flying = false; // Ensure player stops flying
                    serverPlayer.onUpdateAbilities();
                }
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerChangeGameMode(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            boolean hasAirWalkPower = serverPlayer.getPersistentData().getBoolean("hasAirWalkPower");
            if (hasAirWalkPower) {
                // Schedule a delayed task to reapply the abilities after the game mode change
                serverPlayer.getServer().execute(() -> {
                    if (serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                        serverPlayer.getAbilities().mayfly = true;
                        serverPlayer.onUpdateAbilities();
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            boolean hasAirWalkPower = serverPlayer.getPersistentData().getBoolean("hasAirWalkPower");
            if (hasAirWalkPower) {
                new AirWalkPower().abilitiesAirWalk(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (player instanceof ServerPlayer serverPlayer) {
                boolean hasAirWalkPower = player.getPersistentData().getBoolean("hasAirWalkPower");

                if (hasAirWalkPower && serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL) {
                    if (serverPlayer.getFoodData().getFoodLevel() <= 6) {
                        serverPlayer.getAbilities().mayfly = false;
                        serverPlayer.onUpdateAbilities();
                    } else if (serverPlayer.getFoodData().getFoodLevel() > 6) {
                        serverPlayer.getAbilities().mayfly = true;
                        serverPlayer.onUpdateAbilities();
                        if (serverPlayer.getAbilities().flying) {
                            spawnAirWalkFlightParticles(serverPlayer);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            boolean hasAirWalkPower = serverPlayer.getPersistentData().getBoolean("hasAirWalkPower");
            if (hasAirWalkPower) {
                event.setDamageMultiplier(0.0F);
                if (event.getDistance() > 3) {
                    spawnAirWalkFallParticles(serverPlayer);
                }
            }
        }
    }

    //Make fall particles spawn when mayfly is true

    private static void spawnAirWalkFlightParticles(ServerPlayer player) {

        double particleY = player.getY() + 0.1D;

        ((ServerLevel)player.level).sendParticles(ParticleTypes.CLOUD,
                player.getX(), particleY, player.getZ(),
                2, 0.25D, 0.25D, 0.25D, 0.0D);

    }

    private static void spawnAirWalkFallParticles(ServerPlayer player) {

        double particleY = player.getY() + 0.2D;

        ((ServerLevel)player.level).sendParticles(ParticleTypes.POOF,
                player.getX(), particleY, player.getZ(),
                20, 0.5D, 0.1D, 0.5D, 0.0D);

    }

}
