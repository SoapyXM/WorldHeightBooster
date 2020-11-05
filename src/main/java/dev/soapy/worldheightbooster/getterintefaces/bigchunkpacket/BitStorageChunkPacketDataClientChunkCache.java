package dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.BitStorage;
import net.minecraft.world.level.chunk.ChunkBiomeContainer;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

public interface BitStorageChunkPacketDataClientChunkCache {

    @Nullable
    LevelChunk replaceWithBitStoragePacketData(int i, int j, @Nullable ChunkBiomeContainer chunkBiomeContainer, FriendlyByteBuf friendlyByteBuf, CompoundTag compoundTag, BitStorage bitStorage);
}
