package dev.soapy.worldheightbooster;

import net.fabricmc.api.ModInitializer;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WorldHeightBooster implements ModInitializer {
    // replace this with config when config mods exist again
    public static final int WORLD_HEIGHT = 512;
    public static final int MIN_Y = 0;

    public static final Logger LOGGER = Logger.getLogger("worldheightbooster");

    @Override
    public void onInitialize() {
        LOGGER.warning("This mod is experimental. Use it at your own risk.");
    }
}
