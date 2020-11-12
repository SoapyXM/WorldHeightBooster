package dev.soapy.worldheightbooster.mixin.worldgen;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.data.worldgen.Features;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Features.class)
public class MixinFeatures {
    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 256))
    private static int correct256(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 128, ordinal = 9))
    private static int correctCoal(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 80, ordinal = 4))
    private static int correctGranite(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 80, ordinal = 5))
    private static int correctDiorite(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 80, ordinal = 6))
    private static int correctAndesite(int oldValue) {
        return WorldHeightBooster.WORLD_HEIGHT;
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 64, ordinal = 12))
    private static int correctIron(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.65);
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 64, ordinal = 13))
    private static int correctSilverfish(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.65);
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 64, ordinal = 14))
    private static int correctCopper(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.65);
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 32, ordinal=7))
    private static int correctGoldOre(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.58);
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 16, ordinal = 9))
    private static int correctRedstone(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.58);
    }

    @ModifyConstant(method = "<clinit>()V", constant = @Constant(intValue = 16, ordinal = 10))
    private static int correctDiamond(int oldValue) {
        return (int) (WorldHeightBooster.WORLD_HEIGHT * 0.58);
    }
}
