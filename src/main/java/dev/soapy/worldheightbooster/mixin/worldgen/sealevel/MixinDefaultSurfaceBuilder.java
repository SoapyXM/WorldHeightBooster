package dev.soapy.worldheightbooster.mixin.worldgen.sealevel;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.surfacebuilders.DefaultSurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DefaultSurfaceBuilder.class)
public class MixinDefaultSurfaceBuilder {
    //TODO: find a way to do this more elegantly
    @ModifyConstant(method = "apply(Ljava/util/Random;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/biome/Biome;IIIDLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V", constant = @Constant(intValue = 63))
    public int properSeaLevel(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.63);
    }
}
