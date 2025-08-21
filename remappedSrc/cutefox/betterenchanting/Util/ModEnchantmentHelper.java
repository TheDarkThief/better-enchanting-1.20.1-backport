package cutefox.betterenchanting.Util;

import com.google.common.collect.Lists;
import cutefox.betterenchanting.BetterEnchanting;
import cutefox.betterenchanting.datagen.ModEnchantIngredientMap;
import cutefox.betterenchanting.registry.ModEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.hyper_pigeon.horseshoes.Horseshoes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;

import java.util.*;
import java.util.stream.Stream;

public class ModEnchantmentHelper {

    public static int getEnchantmentLevelCost(Enchantment enchantment, int enchantLevel, ItemStack stack, World world){
        float tempCost = (8f/enchantment.getWeight())+enchantLevel;
        tempCost *= (1+(0.1*stack.getEnchantments().getSize())); //price increase by 10% for each different enchantments.
        for(Object2IntMap.Entry<RegistryEntry<Enchantment>> e : stack.getEnchantments().getEnchantmentEntries()){
            tempCost += stack.getEnchantments().getLevel(e.getKey());
        }
        if(enchantIsTreasure(enchantment, world))
            tempCost *= 2;

        int encahntability = stack.getItem().getEnchantability();

        tempCost = tempCost * (1-(encahntability/100));

        return Math.round(tempCost);
    }

    private static boolean enchantIsTreasure(Enchantment enchantment,World world){
        Optional<RegistryEntryList.Named<Enchantment>> treasureList = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntryList(ModEnchantmentTags.BENEFICIAL_TREASURE);
        if(treasureList != null && !treasureList.isEmpty()){
            return  (treasureList.get().stream().filter(e -> {
                return e.value() == enchantment;
            }).count() > 0);
        }
        return false;
    }

    public static int getEnchantmentLeveRequierment(Enchantment enchantment, int enchantLevel){
        int tempLevelReq = 10-enchantment.getWeight()+(enchantLevel*enchantLevel);
        return tempLevelReq>30?30:tempLevelReq; //Cap level requirement at 30 to keep it vanilla like
    }

    public static int getBookshelfCountRequierment(Enchantment enchantment, int enchantLevel){
        int tempReq = (int)Math.floor(getEnchantmentLeveRequierment(enchantment, enchantLevel)/2);
        return tempReq<=3?0:tempReq;
    }

    public static Item getEnchantIngredient(Enchantment enchantment, int enchantLevel){
        return ModEnchantIngredientMap.getIngredientOfLevel(enchantment,enchantLevel);
    }

    public static List<Item> getIngredientsOfEnchantment(Enchantment enchantment){
        return ModEnchantIngredientMap.getIngredientsOfEnchantment(enchantment);
    }

    public static int getEnchantmentIngredientCost(Enchantment value, int displayedEnchantLevel, Item ingredient) {
        int tempValue = (int)Math.floor(value.getWeight()/2);
        if (value.getMaxLevel() == displayedEnchantLevel)
            tempValue = 1;
        tempValue = tempValue==0?1:tempValue;
        if(ingredient == null)
            return tempValue;
        else
            return tempValue>ingredient.getMaxCount()?ingredient.getMaxCount():tempValue;
    }

    public static List<EnchantmentLevelEntry> getPossibleEntries(int bookshelfCount, ItemStack itemToEnchant, DynamicRegistryManager registryManager){

        if(itemToEnchant.isOf(Items.BOOK))
            return List.of();

        Optional<RegistryEntryList.Named<Enchantment>> enchantingTableList = registryManager.get(RegistryKeys.ENCHANTMENT).getEntryList(EnchantmentTags.IN_ENCHANTING_TABLE);
        Optional<RegistryEntryList.Named<Enchantment>> treasureList = registryManager.get(RegistryKeys.ENCHANTMENT).getEntryList(EnchantmentTags.TREASURE);

        Stream <RegistryEntry<Enchantment>> concatEnchantList;
        List<EnchantmentLevelEntry> list = Lists.newArrayList();

        if(enchantingTableList.isEmpty())
            return list;

        if(!treasureList.isEmpty())
            concatEnchantList = Stream.concat(enchantingTableList.get().stream(), treasureList.get().stream());
        else
            concatEnchantList = enchantingTableList.get().stream();

        List<RegistryKey<Enchantment>> swordEnchants = new ArrayList<>();

        swordEnchants.add(Enchantments.FIRE_ASPECT);
        swordEnchants.add(Enchantments.LOOTING);
        swordEnchants.add(Enchantments.KNOCKBACK);
        //TODO: ADD MODDED SWORD ENCHANTS
        //swordEnchants.add(RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.tryParse("mod_identifier:moded_enchant")));

        concatEnchantList
                .filter(enchant -> {
                        boolean validEnchant;
                        if(enchant.isIn(EnchantmentTags.CURSE))
                            return false;
                        validEnchant = enchant.value().isAcceptableItem(itemToEnchant);
                        if(validEnchant == false && itemToEnchant.isIn(ItemTags.AXES) && swordEnchants.contains(enchant.getKey().get()))
                            validEnchant = true;
                        return validEnchant;
                })
                .forEach(enchant -> {

                    Enchantment enchantmentValue = enchant.value();

                    if(isCompatible(itemToEnchant.getEnchantments().getEnchantments(), enchant)){
                        for(int j = enchantmentValue.getMaxLevel(); j >= enchantmentValue.getMinLevel(); --j) {
                            if (getBookshelfCountRequierment(enchantmentValue, j) <= bookshelfCount) {
                                list.add(new EnchantmentLevelEntry(enchant, j));
                                break;
                            }
                        }
                    }
                });

        return list;

    }

    public static boolean itemHasPreviousLevelOfEnchant(ItemStack stack, RegistryEntry<Enchantment> enchant, int targetLevel){

        int currentEnchantLevel = EnchantmentHelper.getLevel(enchant.value(),stack);
        return currentEnchantLevel == targetLevel;

    }

    public static boolean canBeCombined(RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second) {
        return !first.value().exclusiveSet().contains(second) && !second.value().exclusiveSet().contains(first);
    }

    public static boolean isCompatible(Collection<RegistryEntry<Enchantment>> existing, RegistryEntry<Enchantment> candidate) {
        for (RegistryEntry<Enchantment> registryEntry : existing) {
            if (!canBeCombined(registryEntry, candidate)){
                if(!registryEntry.equals(candidate))
                    return false;
            }
        }
        return true;
    }

    public static boolean itemIsEnchantable(ItemStack itemStacks){

        List<Item> enchantableModdedItems = new ArrayList<>();

        if(BetterEnchanting.HORSESHOES_PRESENT)
            enchantableModdedItems.addAll(List.of(
                    Horseshoes.DIAMOND_HORSESHOES_ITEM,
                    Horseshoes.IRON_HORSESHOES_ITEM,
                    Horseshoes.GOLD_HORSESHOES_ITEM));

        return enchantableModdedItems.contains(itemStacks.getItem()) || itemStacks.getItem().isEnchantable(itemStacks);
    }
}
