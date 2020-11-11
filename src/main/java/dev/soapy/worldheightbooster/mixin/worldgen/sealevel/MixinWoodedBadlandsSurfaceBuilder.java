package dev.soapy.worldheightbooster.mixin.worldgen.sealevel;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.WoodedBadlandsSurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(WoodedBadlandsSurfaceBuilder.class)
public class MixinWoodedBadlandsSurfaceBuilder {
    //TODO: find a way to do this more elegantly
    @ModifyConstant(method = "apply(Ljava/util/Random;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/biome/Biome;IIIDLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;IJLnet/minecraft/world/level/levelgen/surfacebuilders/SurfaceBuilderBaseConfiguration;)V", constant = @Constant(intValue = 64))
    public int properSeaLevel(int oldValue, Random random, ChunkAccess chunkAccess, Biome biome, int i, int j, int k, double d, BlockState blockState, BlockState blockState2, int l, long m, SurfaceBuilderBaseConfiguration surfaceBuilderBaseConfiguration) {
        return l;
    }
}
