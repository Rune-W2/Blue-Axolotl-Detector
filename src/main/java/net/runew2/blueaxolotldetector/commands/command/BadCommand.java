package net.runew2.blueaxolotldetector.commands.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class BadCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("bad")
                .then(argument("radius", DoubleArgumentType.doubleArg())
                        .executes(ctx -> badCommand(ctx.getSource(), DoubleArgumentType.getDouble(ctx, "radius")))
                )
                .executes(ctx -> {
                    ctx.getSource().sendFeedback(Text.of("Please specify a radius."));
                    return 1;
                })
        );
    }

    private static int badCommand(FabricClientCommandSource source, double radius) {
        double maxRadius = 128; // Set a fixed maximum radius
        if (radius > maxRadius) {
            source.sendFeedback(Text.of("The specified radius exceeds the maximum radius of " + maxRadius + ". Please specify a smaller radius."));
            return 1;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        Vec3d playerPos = client.player.getPos();
        boolean blueAxolotlFound = client.world.getEntitiesByClass(AxolotlEntity.class, new Box(playerPos, playerPos).expand(radius), axolotl -> axolotl.getVariant() == AxolotlEntity.Variant.BLUE).size() > 0;
        if (blueAxolotlFound) {
            source.sendFeedback(Text.of("Blue axolotl detected!"));
        } else {
            source.sendFeedback(Text.of("Blue axolotl not found."));
        }
        return 1;
    }
}