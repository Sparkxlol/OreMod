package com.sparkxlol.testmodding.registry;

import com.sparkxlol.testmodding.TestModding;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block RUBY_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(5f, 30f)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SAPPHIRE_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(5f, 30f)
            .sounds(BlockSoundGroup.METAL));

    public static final Block AMETHYST_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(5f, 30f)
            .sounds(BlockSoundGroup.METAL));

    public static final Block RUBY_ORE = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3f, 15f)
            .sounds(BlockSoundGroup.METAL));

    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(TestModding.MOD_ID, "ruby_block"), RUBY_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(TestModding.MOD_ID, "sapphire_block"), SAPPHIRE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(TestModding.MOD_ID, "amethyst_block"), AMETHYST_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(TestModding.MOD_ID, "ruby_ore"), RUBY_ORE);
    }
}
