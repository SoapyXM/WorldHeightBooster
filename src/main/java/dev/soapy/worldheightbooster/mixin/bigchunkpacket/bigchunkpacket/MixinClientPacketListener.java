package dev.soapy.worldheightbooster.mixin.bigchunkpacket.bigchunkpacket;

import dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket.BitStorageChunkPacketDataClientChunkCache;
import dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket.BitStorageGetter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacket;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkBiomeContainer;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPacketListener.class)
public abstract class MixinClientPacketListener {

    @Shadow @Final private Minecraft minecraft;

    @Shadow private RegistryAccess registryAccess;

    @Shadow private ClientLevel level;

    /**
     * @author CorgiTaco
     */
    @Overwrite
    public void handleLevelChunk(ClientboundLevelChunkPacket clientboundLevelChunkPacket) {
        PacketUtils.ensureRunningOnSameThread(clientboundLevelChunkPacket, ((ClientPacketListener)(Object)this), (BlockableEventLoop)this.minecraft);
        int i = clientboundLevelChunkPacket.getX();
        int j = clientboundLevelChunkPacket.getZ();
        ChunkBiomeContainer chunkBiomeContainer = clientboundLevelChunkPacket.getBiomes() == null ? null : new ChunkBiomeContainer(this.registryAccess.registryOrThrow(Registry.BIOME_REGISTRY), clientboundLevelChunkPacket.getBiomes());
        LevelChunk levelChunk = ((BitStorageChunkPacketDataClientChunkCache) this.level.getChunkSource()).replaceWithBitStoragePacketData(i, j, chunkBiomeContainer, clientboundLevelChunkPacket.getReadBuffer(), clientboundLevelChunkPacket.getHeightmaps(), ((BitStorageGetter)clientboundLevelChunkPacket).getBitStorage());

        for(int k = this.level.getMinSection(); k < this.level.getMaxSection(); ++k) {
            this.level.setSectionDirtyWithNeighbors(i, k, j);
        }

        if (levelChunk != null) {
            for (CompoundTag compoundTag : clientboundLevelChunkPacket.getBlockEntitiesTags()) {
                BlockPos blockPos = new BlockPos(compoundTag.getInt("x"), compoundTag.getInt("y"), compoundTag.getInt("z"));
                BlockEntity blockEntity = levelChunk.getBlockEntity(blockPos, LevelChunk.EntityCreationType.IMMEDIATE);
                if (blockEntity != null) {
                    blockEntity.load(compoundTag);
                }
            }
        }
    }
}
