package dev.soapy.worldheightbooster.mixin;

import dev.soapy.worldheightbooster.WorldHeightBooster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Level.class)
public class MixinWorldHeight {
    /**
     * @author SoapyXM
     */
    @Overwrite
    public int getSectionsCount() {
        return WorldHeightBooster.WORLD_HEIGHT / 16;
    }

    /**
     * @author SoapyXM
     */
    @Overwrite
    public int getMinSection() {
        return WorldHeightBooster.MIN_Y / 16;
    }
}
