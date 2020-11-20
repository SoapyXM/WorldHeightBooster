package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import dev.soapy.worldheightbooster.config.WorldHeightConfig;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class FabricApiWorkaroundPlugin implements IMixinConfigPlugin {
    public boolean shouldUseBigChunkPacket = false;

    @Override
    public void onLoad(String mixinPackage) {
        WorldHeightConfig.handleConfig(WorldHeightBooster.CONFIG_PATH.resolve(WorldHeightBooster.MOD_ID + ".json"));
        if(WorldHeightBooster.WORLD_HEIGHT > 512) {
            shouldUseBigChunkPacket = true;
            WorldHeightBooster.LOGGER.warning("WorldHeightBooster is running in experimental mode, due to a default world height set above 512. This is incompatible with Fabric API.");
            WorldHeightBooster.LOGGER.warning("Do not load worlds created in higher world heights on lower world heights.");
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return (!targetClassName.startsWith("bigchunkpacket")) || shouldUseBigChunkPacket;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
