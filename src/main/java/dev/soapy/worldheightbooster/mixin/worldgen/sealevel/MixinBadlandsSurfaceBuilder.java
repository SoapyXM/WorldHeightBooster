package dev.soapy.worldheightbooster.mixin.worldgen.sealevel;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.surfacebuilders.BadlandsSurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BadlandsSurfaceBuilder.class)
public class MixinBadlandsSurfaceBuilder {
    //TODO: find a way to do this more elegantly
    @ModifyConstant(method = "apply(Ljava/util/Random;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/biome/Biome;IIIDLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;IJLnet/minecraft/world/level/levelgen/surfacebuilders/SurfaceBuilderBaseConfiguration;)V", constant = @Constant(intValue = 64))
    public int properSeaLevel(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.63);
    }
}
