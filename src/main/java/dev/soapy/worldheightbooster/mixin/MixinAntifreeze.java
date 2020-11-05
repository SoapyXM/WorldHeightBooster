package dev.soapy.worldheightbooster.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Biome.class)
public abstract class MixinAntifreeze {
    @ModifyConstant(method = "getHeightAdjustedTemperature(Lnet/minecraft/core/BlockPos;)F", constant = @Constant(intValue = 64))
    public int heightAdjustedValue(int oldValue) {
        return 320;
    }

    @ModifyConstant(method = "getHeightAdjustedTemperature(Lnet/minecraft/core/BlockPos;)F", constant = @Constant(floatValue = 64.0f))
    public float heightAdjustedDoubleValue(float oldValue) {
        return 320.0f;
    }
}
