package dev.soapy.worldheightbooster.mixin.worldgen;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Biome.class)
public abstract class MixinAntifreeze {
    @ModifyConstant(method = "getHeightAdjustedTemperature(Lnet/minecraft/core/BlockPos;)F", constant = @Constant(intValue = 64))
    public int heightAdjustedValue(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT - 200;
    }

    @ModifyConstant(method = "getHeightAdjustedTemperature(Lnet/minecraft/core/BlockPos;)F", constant = @Constant(floatValue = 64.0f))
    public float heightAdjustedDoubleValue(float oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT - 200;
    }
}
