package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(IntegratedServer.class)
public abstract class MixinDefaultHeightLimit {
    @Environment(EnvType.CLIENT)
    @Inject(at = @At("TAIL"), method = "<init>(Ljava/lang/Thread;Lnet/minecraft/client/Minecraft;Lnet/minecraft/core/RegistryAccess$RegistryHolder;Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lnet/minecraft/server/packs/repository/PackRepository;Lnet/minecraft/server/ServerResources;Lnet/minecraft/world/level/storage/WorldData;Lcom/mojang/authlib/minecraft/MinecraftSessionService;Lcom/mojang/authlib/GameProfileRepository;Lnet/minecraft/server/players/GameProfileCache;Lnet/minecraft/server/level/progress/ChunkProgressListenerFactory;)V")
    private void onConstructed(CallbackInfo ci) {
        ((IntegratedServer)(Object)this).setMaxBuildHeight(WorldHeightBooster.WORLD_HEIGHT);
    }
}
