package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(CaveWorldCarver.class)
public class MixinCaveWorldCarver {
    /**
     * @author SoapyXM
     */
    @Overwrite
    public int getCaveY(Random random) {
        return random.nextInt(random.nextInt(WorldHeightBooster.WORLD_HEIGHT - 12) + 8);
    }
}
