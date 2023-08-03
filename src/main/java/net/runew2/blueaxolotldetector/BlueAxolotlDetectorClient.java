package net.runew2.blueaxolotldetector;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.util.math.Vec3d;
import net.runew2.blueaxolotldetector.commands.command.BadCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.runew2.blueaxolotldetector.commands.command.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class BlueAxolotlDetectorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(BlueAxolotlDetectorClient::registerCommands);
    }
    public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        BadCommand.register(dispatcher);
    }
}
