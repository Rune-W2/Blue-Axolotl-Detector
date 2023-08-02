package net.runew2.blueaxolotldetector;

import static net.minecraft.server.command.CommandManager.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.util.math.Box;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class BlueAxolotlDetector implements ModInitializer {
	public static final String MOD_ID = "blueaxolotldetector";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(literal("bad").executes(context -> {
				MinecraftClient client = MinecraftClient.getInstance();
				Vec3d playerPos = client.player.getPos();
				boolean blueAxolotlFound = client.world.getEntitiesByClass(AxolotlEntity.class, new Box(playerPos, playerPos).expand(10), axolotl -> axolotl.getVariant() == AxolotlEntity.Variant.BLUE).size() > 0;
				if (blueAxolotlFound) {
					context.getSource().sendMessage(Text.of("Blue axolotl detected!"));
				} else {
					context.getSource().sendMessage(Text.of("Blue axolotl not found."));
				}
				return 1;
			}));
		});
	}
}
