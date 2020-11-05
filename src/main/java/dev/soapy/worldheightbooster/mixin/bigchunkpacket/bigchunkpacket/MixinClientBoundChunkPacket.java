package dev.soapy.worldheightbooster.mixin.bigchunkpacket.bigchunkpacket;

import dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket.BitStorageGetter;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacket;
import net.minecraft.util.BitStorage;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientboundLevelChunkPacket.class)
public abstract class MixinClientBoundChunkPacket implements BitStorageGetter {

    @Shadow protected abstract ByteBuf getWriteBuffer();

    private BitStorage extendedSectionBitStorage;

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/level/chunk/LevelChunk;)V")
    private void rewriteChunKData(LevelChunk levelChunk, CallbackInfo ci) {
        this.extendedSectionBitStorage = this.extractExtendedSectionChunkData(new FriendlyByteBuf(this.getWriteBuffer()), levelChunk);
    }

    private BitStorage extractExtendedSectionChunkData(FriendlyByteBuf friendlyByteBuf, LevelChunk levelChunk) {
        LevelChunkSection[] levelChunkSections = levelChunk.getSections();
        int chunkSectionIDX = 0;
        BitStorage sectionBitStorage = new BitStorage(1, levelChunkSections.length, null);

        for(int sectionsLength = levelChunkSections.length; chunkSectionIDX < sectionsLength; ++chunkSectionIDX) {
            LevelChunkSection levelChunkSection = levelChunkSections[chunkSectionIDX];
            if (levelChunkSection != LevelChunk.EMPTY_SECTION && !levelChunkSection.isEmpty()) {
                sectionBitStorage.set(chunkSectionIDX, 1);
                levelChunkSection.write(friendlyByteBuf);
            }
        }
        return sectionBitStorage;
    }


    @Inject(at = @At("RETURN"), method = "write(Lnet/minecraft/network/FriendlyByteBuf;)V")
    private void writeExtendedSectionStorage(FriendlyByteBuf friendlyByteBuf, CallbackInfo ci) {
        friendlyByteBuf.writeLongArray(this.extendedSectionBitStorage.getRaw());
    }

    @Inject(at = @At("RETURN"), method = "read(Lnet/minecraft/network/FriendlyByteBuf;)V")
    private void readExtendedSectionStorage(FriendlyByteBuf friendlyByteBuf, CallbackInfo ci) {
        friendlyByteBuf.readLongArray(this.extendedSectionBitStorage.getRaw());
    }

    @Override
    public BitStorage getBitStorage() {
        return this.extendedSectionBitStorage;
    }
}