package dev.soapy.worldheightbooster.mixin.worldgen;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.structure.DesertPyramidPiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DesertPyramidPiece.class)
public class MixinDesertTemple {
    @ModifyConstant(method = "<init>(Ljava/util/Random;II)V", constant = @Constant(intValue = 64))
    private static int correctSeaLevel(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.71);
    }
}
