package com.sparkxlol.testmodding;

import com.sparkxlol.testmodding.registry.ModBlocks;
import com.sparkxlol.testmodding.registry.ModItems;
import com.sparkxlol.testmodding.registry.RegisterItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.impl.biome.modification.BiomeModificationContextImpl;
import net.fabricmc.fabric.impl.biome.modification.BiomeSelectionContextImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class TestModding implements ModInitializer {

    public static final String MOD_ID = "testmodding";


    // Item Groups
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.RUBY));

    public static final ItemGroup GEMS_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "gems"))
            .icon(() -> new ItemStack(ModItems.RUBY_ORE))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(ModItems.RUBY));
                stacks.add(new ItemStack(ModItems.RUBY_BLOCK));
                stacks.add(new ItemStack(ModItems.RUBY_ORE));
                stacks.add(new ItemStack(ModItems.SAPPHIRE));
                stacks.add(new ItemStack(ModItems.SAPPHIRE_BLOCK));
                stacks.add(new ItemStack(ModItems.AMETHYST));
                stacks.add(new ItemStack(ModItems.AMETHYST_BLOCK));
            })
            .build();


    // Edit Biomes
    private static ConfiguredFeature<?, ?> ORE_RUBY_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.RUBY_ORE.getDefaultState(),
                    4))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0,
                    0,
                    18)))
            .spreadHorizontally()
            .repeat(10);


    // Loot Tables
    private static final Identifier EMERALD_ORE_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/emerald_ore");


    // Initialize
    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        modifyLootTables();
        RegisterItems.register();

        // Spawn Ores
        registerFeatures();
    }

    private void modifyLootTables()
    {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if (EMERALD_ORE_LOOT_TABLE_ID.equals(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .with(ItemEntry.builder(Items.EMERALD))
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(0.0f, 3.0f)).build());
                supplier.withPool(poolBuilder.build());
            }
        }));
    }

    private void registerFeatures()
    {
        RegistryKey<ConfiguredFeature<?, ?>> oreRubyOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("testmodding", "ore_ruby_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreRubyOverworld.getValue(), ORE_RUBY_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreRubyOverworld);
    }
}
