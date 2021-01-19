package com.sparkxlol.testmodding.registry;

import com.sparkxlol.testmodding.TestModding;
import com.sparkxlol.testmodding.registry.materials.RubyArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {

    public static final ArmorMaterial rubyArmorMaterial = new RubyArmorMaterial();
    public static final Item RUBY_HELMET = new ArmorItem(rubyArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final Item RUBY_CHESTPLATE = new ArmorItem(rubyArmorMaterial, EquipmentSlot.CHEST, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final Item RUBY_LEGGINGS = new ArmorItem(rubyArmorMaterial, EquipmentSlot.LEGS, new Item.Settings().group(TestModding.ITEM_GROUP));
    public static final Item RUBY_BOOTS = new ArmorItem(rubyArmorMaterial, EquipmentSlot.FEET, new Item.Settings().group(TestModding.ITEM_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("testmodding", "ruby_helmet"), RUBY_HELMET);
        Registry.register(Registry.ITEM, new Identifier("testmodding", "ruby_chestplate"), RUBY_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("testmodding", "ruby_leggings"), RUBY_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("testmodding", "ruby_boots"), RUBY_BOOTS);
    }
}
