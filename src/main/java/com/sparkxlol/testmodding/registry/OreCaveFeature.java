package com.sparkxlol.testmodding.registry;

import com.mojang.serialization.Codec;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
        int height = (int)(Math.random() * (7 - 3 + 1) + 3);
        Block[] ores = { ModBlocks.RUBY_ORE, ModBlocks.SAPPHIRE_ORE, ModBlocks.AMETHYST_ORE };

        BlockPos currentPos = new BlockPos(topPos.getX(), randY, topPos.getZ());

        int newHeight = height;
        BlockPos savedPos = currentPos;

        if (world.getBlockState(currentPos) == Blocks.STONE.getDefaultState()) {

            while (newHeight >= 0) {
                if (newHeight == 0)
                    world.setBlockState(currentPos, ores[(int)(Math.random() * (2 + 1) + 0)].getDefaultState(), 3);
                else {
                    spawnRing(currentPos, newHeight, world, ores);
                    currentPos = new BlockPos(currentPos.getX() + 1, currentPos.getY() + 1, currentPos.getZ() + 1);
                }
                newHeight -= 2;
            }

            System.out.println(currentPos);
            newHeight = height;

            while (newHeight >= 0) {
                if (newHeight == 0)
                    world.setBlockState(savedPos, ores[(int)(Math.random() * (2 + 1) + 0)].getDefaultState(), 3);
                else {
                    spawnRing(savedPos, newHeight, world, ores);
                    savedPos = new BlockPos(savedPos.getX() + 1, savedPos.getY() - 1, savedPos.getZ() + 1);
                }
                newHeight -= 2;
            }
            return true;
        }
        else
            return false;
        //Need to work on spawning last piece at newHeight = 1 :)
    }

    private void spawnRing(BlockPos currentPos, int height, StructureWorldAccess world, Block[] ores)
    {
        int direction = 0;

        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < height; i++) {
                world.setBlockState(currentPos, ores[(int)(Math.random() * (2 + 1) + 0)].getDefaultState(), 3);

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

        while (height >= 0) {
            height -= 2;
            direction = 0;
            currentPos = new BlockPos(currentPos.getX() + 1, currentPos.getY(), currentPos.getZ() + 1);

            if (height == 0)
                world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 3);
            else {
                for (int x = 0; x < 4; x++) {
                    for (int i = 0; i < height; i++) {
                        world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 3);

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
    }
}
