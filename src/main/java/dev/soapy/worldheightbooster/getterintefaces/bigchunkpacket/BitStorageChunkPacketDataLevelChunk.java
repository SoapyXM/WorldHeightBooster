package dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.BitStorage;
import net.minecraft.world.level.chunk.ChunkBiomeContainer;
import org.jetbrains.annotations.Nullable;

public interface BitStorageChunkPacketDataLevelChunk {

    @Environment(EnvType.CLIENT)
    void replaceWithBitStoragePacketData(@Nullable ChunkBiomeContainer chunkBiomeContainer, FriendlyByteBuf friendlyByteBuf, CompoundTag compoundTag, BitStorage bitStorage);
}
