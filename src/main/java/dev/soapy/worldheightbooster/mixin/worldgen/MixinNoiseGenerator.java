package dev.soapy.worldheightbooster.mixin.worldgen;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NoiseGeneratorSettings.class)
public class MixinNoiseGenerator {
    @ModifyConstant(method = "lambda$static$0(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;", constant = @Constant(intValue = 255))
    private static int actualNoiseHeight(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }
}
