package dev.soapy.worldheightbooster.mixin.bigchunkpacket.bigchunkpacket;

import dev.soapy.worldheightbooster.getterintefaces.bigchunkpacket.BitStorageChunkPacketDataLevelChunk;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.BitStorage;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkBiomeContainer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(LevelChunk.class)
public abstract class MixinLevelChunkLevelChunk implements BitStorageChunkPacketDataLevelChunk {


    @Shadow @Final private Map<BlockPos, BlockEntity> blockEntities;

    @Shadow protected abstract void onBlockEntityRemove(BlockEntity blockEntity);

    @Shadow @Final private LevelChunkSection[] sections;

    @Shadow @Final @Nullable public static LevelChunkSection EMPTY_SECTION;

    @Shadow public abstract void setHeightmap(Heightmap.Types types, long[] ls);

    @Shadow private ChunkBiomeContainer biomes;

    @Override
    public void replaceWithBitStoragePacketData(@Nullable ChunkBiomeContainer chunkBiomeContainer, FriendlyByteBuf friendlyByteBuf, CompoundTag compoundTag, BitStorage bitStorage) {
        boolean bl = chunkBiomeContainer != null;
        if (bl) {
            this.blockEntities.values().forEach(this::onBlockEntityRemove);
            this.blockEntities.clear();
        } else {
            this.blockEntities.values().removeIf((blockEntity) -> {
                if (bitStorage.get(((LevelChunk)(Object)this).getSectionIndex(blockEntity.getBlockPos().getY())) != 0) {
                    blockEntity.setRemoved();
                    return true;
                } else {
                    return false;
                }
            });
        }

        for(int j = 0; j < this.sections.length; ++j) {
            LevelChunkSection levelChunkSection = this.sections[j];
            if ((bitStorage.get(j)) == 0) {
                if (bl && levelChunkSection != EMPTY_SECTION) {
                    this.sections[j] = EMPTY_SECTION;
                }
            } else {
                if (levelChunkSection == EMPTY_SECTION) {
                    levelChunkSection = new LevelChunkSection(((LevelChunk)(Object)this).getSectionYFromSectionIndex(j));
                    this.sections[j] = levelChunkSection;
                }

                levelChunkSection.read(friendlyByteBuf);
            }
        }

        if (chunkBiomeContainer != null) {
            this.biomes = chunkBiomeContainer;
        }

        Heightmap.Types[] var11 = Heightmap.Types.values();
        int var12 = var11.length;

        for (Heightmap.Types types : var11) {
            String string = types.getSerializationKey();
            if (compoundTag.contains(string, 12)) {
                this.setHeightmap(types, compoundTag.getLongArray(string));
            }
        }
    }
}