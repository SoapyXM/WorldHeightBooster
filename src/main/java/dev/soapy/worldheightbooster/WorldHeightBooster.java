package dev.soapy.worldheightbooster;

import dev.soapy.worldheightbooster.config.WorldHeightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.logging.Logger;

public class WorldHeightBooster implements ModInitializer {
    public static int WORLD_HEIGHT = 768;
    public static final int MIN_Y = 0;
    public static final String MOD_ID = "worldheightbooster";

    public static final Logger LOGGER = Logger.getLogger("worldheightbooster");

    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDirectory().toPath();


    @Override
    public void onInitialize() {
        LOGGER.warning("This mod is experimental. Use it at your own risk.");
        WorldHeightConfig.handleConfig(CONFIG_PATH.resolve(MOD_ID + ".json"));
    }
}
