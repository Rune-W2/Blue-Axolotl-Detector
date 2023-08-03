package net.runew2.blueaxolotldetector.commands.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class BadCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("bad")
                .executes(ctx -> badCommand(ctx.getSource())));
    }

    private static int badCommand(FabricClientCommandSource source) {
        MinecraftClient client = MinecraftClient.getInstance();
        Vec3d playerPos = client.player.getPos();
        boolean blueAxolotlFound = client.world.getEntitiesByClass(AxolotlEntity.class, new Box(playerPos, playerPos).expand(10), axolotl -> axolotl.getVariant() == AxolotlEntity.Variant.BLUE).size() > 0;
        if (blueAxolotlFound) {
            source.sendFeedback(Text.of("Blue axolotl detected!"));
        } else {
            source.sendFeedback(Text.of("Blue axolotl not found."));
        }
        return Command.SINGLE_SUCCESS;
    }
}