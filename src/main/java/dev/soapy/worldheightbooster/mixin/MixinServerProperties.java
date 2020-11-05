package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Properties;
import java.util.function.UnaryOperator;

@Mixin(DedicatedServerProperties.class)
public abstract class MixinServerProperties extends Settings {
    @Mutable
    @Shadow public int maxBuildHeight;

    public MixinServerProperties(Properties properties) {
        super(properties);
    }

    @Environment(EnvType.SERVER)
    @Inject(at = @At("TAIL"), method = "<init>(Ljava/util/Properties;Lnet/minecraft/core/RegistryAccess;)V")
    private void onConstructed(CallbackInfo ci) {
        this.maxBuildHeight = this.get("max-build-height", (integer) -> {
            return Mth.clamp(((int)integer + 8) / (WorldHeightBooster.WORLD_HEIGHT), 64, WorldHeightBooster.WORLD_HEIGHT);
        }, WorldHeightBooster.WORLD_HEIGHT);
    }
}
