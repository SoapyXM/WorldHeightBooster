package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldCarver.class)
public class MixinCarver {
    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 256))
    private static int actualCaveHeight(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }
}
