package com.sparkxlol.testmodding.registry;

import com.mojang.serialization.Codec;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class OreCaveFeature extends Feature<DefaultFeatureConfig> {
    public OreCaveFeature(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        topPos = new BlockPos(topPos.getX(), topPos.getY() - 20, topPos.getZ());
        BlockPos botPos = new BlockPos(topPos.getX(), 15, topPos.getZ());

        int randY = (int)(Math.random() * (topPos.getY() - botPos.getY() + 1) + botPos.getY());
        int height = (int)(Math.random() * (5 - 3 + 1) + 1);

        BlockPos currentPos = new BlockPos(topPos.getX(), randY, topPos.getZ());

        int newHeight = height;
        BlockPos savedPos = currentPos;

        for (int y = 0; y < height; y++) {

            spawnRing(currentPos, newHeight, world);
            newHeight -= 2;
            currentPos = new BlockPos(currentPos.getX() + 1, currentPos.getY() + 1, currentPos.getZ() + 1);
        }

        System.out.println(currentPos);
        newHeight = height;

        for (int y = 0; y < height; y++) {

            spawnRing(savedPos, newHeight, world);
            newHeight -= 2;
            savedPos = new BlockPos(savedPos.getX() + 1, savedPos.getY() - 1, savedPos.getZ() + 1);
        }

        //Need to work on spawning last piece at newHeight = 1 :)

        return true;
    }

    private void spawnRing(BlockPos currentPos, int height, StructureWorldAccess world)
    {
        int direction = 0;

        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < height; i++) {
                world.setBlockState(currentPos, ModBlocks.RUBY_ORE.getDefaultState(), 3);
                if (direction == 0)
                    currentPos = new BlockPos(currentPos.getX() + 1, currentPos.getY(), currentPos.getZ());
                else if (direction == 1)
                    currentPos = new BlockPos(currentPos.getX(), currentPos.getY(), currentPos.getZ() + 1);
                else if (direction == 2)
                    currentPos = new BlockPos(currentPos.getX() - 1, currentPos.getY(), currentPos.getZ());
                else if (direction == 3)
                    currentPos = new BlockPos(currentPos.getX(), currentPos.getY(), currentPos.getZ() - 1);
            }
            direction++;
        }
    }
}
