package dev.soapy.worldheightbooster.mixin.worldgen;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.levelgen.Decoratable;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Decoratable.class)
public interface MixinFeature<R> {
    @Shadow R decorated(ConfiguredDecorator<?> configuredDecorator);

    /**
     * @author SoapyXM
     */
    @Overwrite
    default R range(int i) {
        return this.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(0, 0, WorldHeightBooster.WORLD_HEIGHT - 1)));
    }
}
