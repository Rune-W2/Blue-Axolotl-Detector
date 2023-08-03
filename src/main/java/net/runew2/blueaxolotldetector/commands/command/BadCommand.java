package net.runew2.blueaxolotldetector.commands.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Timer;
import java.util.TimerTask;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class BadCommand {

    private static boolean scanning = false;
    private static double scanRadius = 0;
    private static Timer scanTimer;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("bad")
                .then(argument("mode", StringArgumentType.string())
                        .executes(ctx -> badCommand(ctx.getSource(), StringArgumentType.getString(ctx, "mode")))
                        .then(argument("radius", DoubleArgumentType.doubleArg())
                                .executes(ctx -> badCommand(ctx.getSource(), StringArgumentType.getString(ctx, "mode"), DoubleArgumentType.getDouble(ctx, "radius")))
                        )
                )
                .executes(ctx -> {
                    ctx.getSource().sendFeedback(Text.of("Please specify a mode."));
                    return 1;
                })
        );
    }

    private static int badCommand(FabricClientCommandSource source, String mode) {
        return badCommand(source, mode, 15); // Set default radius to 15
    }

    private static int badCommand(FabricClientCommandSource source, String mode, double radius) {
        double maxRadius = 128; // Set a fixed maximum radius
        if (radius > maxRadius) {
            source.sendFeedback(Text.of("The specified radius exceeds the maximum radius of " + maxRadius + ". Please specify a smaller radius."));
            return 1;
        }

        if (mode.equals("start")) {
            if (scanning) {
                source.sendFeedback(Text.of("The scanning process is already running."));
            } else {
                startScanning(source, radius);
            }
        } else if (mode.equals("stop")) {
            if (scanning) {
                stopScanning(source);
            } else {
                source.sendFeedback(Text.of("The scanning process is not running."));
            }
        } else {
            source.sendFeedback(Text.of("Invalid mode. Please use 'start' or 'stop'."));
        }

        return 1;
    }

    private static void startScanning(FabricClientCommandSource source, double radius) {
        scanning = true;
        scanRadius = radius;
        source.sendFeedback(Text.of("Scanning for blue axolotls in radius " + radius + " every 3 seconds. \n Use '/bad stop' to stop scanning.\n You will be notified when one is found"));

        scanTimer = new Timer();
        scanTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (scanning) {
                    scanForBlueAxolotls(MinecraftClient.getInstance(), scanRadius);
                }
            }
        }, 0, 3000); // 3000 milliseconds = 3 seconds
    }

    private static void stopScanning(FabricClientCommandSource source) {
        scanning = false;
        source.sendFeedback(Text.of("Scanning stopped."));
        if (scanTimer != null) {
            scanTimer.cancel();
            scanTimer = null;
        }
    }

    private static void scanForBlueAxolotls(MinecraftClient client, double radius) {
        Vec3d playerPos = client.player.getPos();
        boolean blueAxolotlFound = client.world.getEntitiesByClass(AxolotlEntity.class, new Box(playerPos, playerPos).expand(radius), axolotl -> axolotl.getVariant() == AxolotlEntity.Variant.BLUE).size() > 0;
        if (blueAxolotlFound) {
            client.player.sendMessage(Text.of("Blue axolotl detected!"), false);
        } else {
            // client.player.sendMessage(Text.of("No blue axolotl detected."), false);
        }
    }
}