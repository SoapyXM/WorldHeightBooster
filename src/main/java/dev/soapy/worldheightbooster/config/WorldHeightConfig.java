package dev.soapy.worldheightbooster.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.soapy.worldheightbooster.WorldHeightBooster;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorldHeightConfig {

    public static void handleConfig(Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();

        final File CONFIG_FILE = new File(String.valueOf(path));

        if (!CONFIG_FILE.exists()) {
            createConfig(path);
        }
        try (Reader reader = new FileReader(path.toString())) {
            ConfigHolder configHolder = gson.fromJson(reader, ConfigHolder.class);
            if (configHolder != null)
                WorldHeightBooster.WORLD_HEIGHT = configHolder.getWorldHeight();
            else
                WorldHeightBooster.LOGGER.severe("Config reading Failed");

        } catch (IOException e) {
            WorldHeightBooster.LOGGER.severe("Config reading Failed");

        }
    }

    private static void createConfig(Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();

        String jsonString = gson.toJson(new ConfigHolder(WorldHeightBooster.WORLD_HEIGHT));

        try {
            Files.write(path, jsonString.getBytes());
        } catch (IOException e) {
            WorldHeightBooster.LOGGER.severe("Config creation Failed");
        }
    }
}
