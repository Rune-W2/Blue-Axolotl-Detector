package net.runew2.blueaxolotldetector;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.runew2.blueaxolotldetector.commands.command.BadCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlueAxolotlDetector implements ModInitializer {
	public static final String MOD_ID = "blueaxolotldetector";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ClientCommandRegistrationCallback.EVENT.register(BlueAxolotlDetector::registerCommands);
	}

	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, net.minecraft.command.CommandRegistryAccess registryAccess) {
		BadCommand.register(dispatcher);
	}
}
