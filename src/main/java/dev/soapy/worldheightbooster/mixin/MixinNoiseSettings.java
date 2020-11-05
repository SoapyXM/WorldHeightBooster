package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NoiseSettings.class)
public class MixinNoiseSettings {
    @ModifyConstant(method = "lambda$static$0(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;", constant = @Constant(intValue = 256))
    private static int actualNoiseHeight(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }
}
