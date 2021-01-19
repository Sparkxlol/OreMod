package com.sparkxlol.testmodding.registry;

import com.sparkxlol.testmodding.TestModding;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    //Items
    public static final Item RUBY = new Item(new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final Item SAPPHIRE = new Item(new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final Item AMETHYST = new Item(new Item.Settings().group(TestModding.ITEM_GROUP));

    //Block Items
    public static final BlockItem RUBY_BLOCK = new BlockItem(ModBlocks.RUBY_BLOCK, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final BlockItem SAPPHIRE_BLOCK = new BlockItem(ModBlocks.SAPPHIRE_BLOCK, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final BlockItem AMETHYST_BLOCK = new BlockItem(ModBlocks.AMETHYST_BLOCK, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final BlockItem RUBY_ORE = new BlockItem(ModBlocks.RUBY_ORE, new Item.Settings().group(TestModding.ITEM_GROUP));

    public static void registerItems()
    {
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "ruby_block"), RUBY_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "ruby_ore"), RUBY_ORE);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "sapphire"), SAPPHIRE);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "sapphire_block"), SAPPHIRE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "amethyst"), AMETHYST);
        Registry.register(Registry.ITEM, new Identifier(TestModding.MOD_ID, "amethyst_block"), AMETHYST_BLOCK);
    }
}
