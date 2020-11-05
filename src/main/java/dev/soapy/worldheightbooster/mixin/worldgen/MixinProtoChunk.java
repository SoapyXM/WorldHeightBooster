package dev.soapy.worldheightbooster.mixin.worldgen;

import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.BitSet;
import java.util.Map;

@Mixin(ProtoChunk.class)
public class MixinProtoChunk {

    @Shadow @Final private Map<GenerationStep.Carving, BitSet> carvingMasks;

    /**
     * @author SoapyXM
     */
    @Overwrite
    public BitSet getOrCreateCarvingMask(GenerationStep.Carving carving) {
        return (BitSet)this.carvingMasks.computeIfAbsent(carving, (carvingx) -> {
            return new BitSet(65536 * 2);
        });
    }
}
