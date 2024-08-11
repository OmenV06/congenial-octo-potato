package net.omen.eclipsemod.util.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.omen.eclipsemod.power.PowerHandler;

import java.util.Collection;
import java.util.stream.Collectors;

public class SetPowerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("Eclipse")
                .then(Commands.literal("Power")
                        .then(Commands.literal("Set")
                                .then(Commands.argument("powerName", StringArgumentType.word())
                                        .suggests(suggestPowers())
                                        .then(Commands.argument("playerName", StringArgumentType.string())
                                                .suggests(suggestPlayerNames())
                                                .executes(context -> {
                                                    String powerName = StringArgumentType.getString(context, "powerName");
                                                    String playerName = StringArgumentType.getString(context, "playerName");
                                                    ServerPlayer player = context.getSource().getServer().getPlayerList().getPlayerByName(playerName);

                                                    if (player != null) {
                                                        PowerHandler.removeCurrentPower(player);
                                                        PowerHandler.savePlayerPower(player, powerName);
                                                        PowerHandler.reapplySavedPower(player, powerName);
                                                        context.getSource().sendSuccess(
                                                                Component.translatable(playerName + "'s Power set to " + powerName), true);
                                                    } else {
                                                        context.getSource().sendFailure(Component.translatable("Invalid Command"));
                                                    }
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );
    }

    private static SuggestionProvider<CommandSourceStack> suggestPowers() {
        return (context, builder) -> {
            PowerHandler.REGISTERED_POWERS.forEach(p -> {
                try {
                    builder.suggest(p.newInstance().getName());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            builder.suggest("None");
            return builder.buildFuture();
        };
    }

    private static SuggestionProvider<CommandSourceStack> suggestPlayerNames() {
        return (context, builder) -> {
            Collection<ServerPlayer> players = context.getSource().getServer().getPlayerList().getPlayers();
            return net.minecraft.commands.SharedSuggestionProvider.suggest(players.stream()
                    .map(ServerPlayer::getGameProfile)
                    .map(profile -> profile.getName())
                    .collect(Collectors.toList()), builder);
        };
    }
}
