package cutefox.betterenchanting.registry;

import cutefox.betterenchanting.BetterEnchanting;
import cutefox.betterenchanting.Util.Utils;
import cutefox.betterenchanting.item.ItemWithRemainderChance;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static void registerModItems(){
        BetterEnchanting.LOGGER.info("Registering mod iems for : "+ BetterEnchanting.MOD_ID);
    }


    //region ENCHANTMENT INGREDIENTS
    public static final List<Item> MOD_ITEM_LIST = new ArrayList<>();
    public static final List<Item> MOD_ITEM_LIST_BUMBLEZONE_COMPAT = new ArrayList<>();
    public static final Item INFUSED_LAPIS = registerItem("infused_lapis", new Item(new Item.Settings()));
    public static final Item MAGIC_SHARD_DULL = registerItem("magic_shard_dull", new Item(new Item.Settings()));
    // TODO Re-add enchantment glints 
    public static final Item MAGIC_SHARD_FULL = registerItem("magic_shard_full", new ItemWithRemainderChance(new Item.Settings().maxCount(16).recipeRemainder(ModItems.MAGIC_SHARD_DULL)));
    //endregion

    //region UPGRADE TEMPLATES
    public static final Item IRON_UPGRADE_SMITHING_TEMPLATE = registerItem("iron_upgrade_smithing_template", new Item(new Item.Settings()));
    public static final Item DIAMOND_UPGRADE_SMITHING_TEMPLATE = registerItem("diamond_upgrade_smithing_template", new Item(new Item.Settings()));
    public static final Item ENCHANTMENT_CATALYST = registerItem("enchantment_catalyst", new Item(new Item.Settings().maxCount(16)));
    //endregion

    //Armors
    public static final Item ESSENCE_OF_PROTECTION = registerItem("essence_of_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FIRE_PROTECTION = registerItem("essence_of_fire_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_BLAST_PROTECTION = registerItem("essence_of_blast_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_PROJECTILE_PROTECTION = registerItem("essence_of_projectile_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item SOUL_ESSENCE_1 = registerItem("soul_essence_1", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item SOUL_ESSENCE_2 = registerItem("soul_essence_2", new Item(new Item.Settings().maxCount(1).fireproof().recipeRemainder(ModItems.SOUL_ESSENCE_1)));
    public static final Item SOUL_ESSENCE_3 = registerItem("soul_essence_3", new Item(new Item.Settings().maxCount(1).fireproof().recipeRemainder(ModItems.SOUL_ESSENCE_2)));
    public static final Item ESSENCE_OF_SNEAK_1 = registerItem("essence_of_sneak_1", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SNEAK_2 = registerItem("essence_of_sneak_2", new Item(new Item.Settings().maxCount(1).fireproof().recipeRemainder(ModItems.ESSENCE_OF_SNEAK_1)));
    public static final Item ESSENCE_OF_SNEAK_3 = registerItem("essence_of_sneak_3", new Item(new Item.Settings().maxCount(1).fireproof().recipeRemainder(ModItems.ESSENCE_OF_SNEAK_2)));
    public static final Item ESSENCE_OF_FEATHER = registerItem("essence_of_feather", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_RESPIRATION = registerItem("essence_of_respiration", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SEA = registerItem("essence_of_sea", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_THORN = registerItem("essence_of_thorn", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_ICE = registerItem("essence_of_ice", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Swords
    public static final Item ESSENCE_OF_SWEEPING = registerItem("essence_of_sweeping", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SHARPNESS = registerItem("essence_of_sharpness", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SMITE = registerItem("essence_of_smite", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_ARTHROPODS = registerItem("essence_of_arthropods", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_KNOCKBACK = registerItem("essence_of_knockback", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FIRE = registerItem("essence_of_fire", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_LOOTING = registerItem("essence_of_looting", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Bows
    public static final Item ESSENCE_OF_ARROWS = registerItem("essence_of_arrows", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_POWER = registerItem("essence_of_power", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_PUNCH = registerItem("essence_of_punch", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Tools
    public static final Item ESSENCE_OF_EFFICIENCY = registerItem("essence_of_efficiency", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SILK_TOUCH = registerItem("essence_of_silk_touch", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FORTUNE = registerItem("essence_of_fortune", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Fishing tool
    public static final Item ESSENCE_OF_SEA_LUCK = registerItem("essence_of_sea_luck", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_LURE = registerItem("essence_of_lure", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Anything
    public static final Item ESSENCE_OF_EXPERIENCE = registerItem("essence_of_experience", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_MENDING = registerItem("essence_of_mending", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_UNBREAKING = registerItem("essence_of_unbreaking", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Trident
    public static final Item ESSENCE_OF_CHANNELING = registerItem("essence_of_channeling", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_IMPALING = registerItem("essence_of_impaling", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_LOYALTY = registerItem("essence_of_loyalty", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_RIPTIDE = registerItem("essence_of_riptide", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Crossbow
    public static final Item ESSENCE_OF_MULTISHOT = registerItem("essence_of_multishot", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_PIERCING = registerItem("essence_of_piercing", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_QUICK_CHARGE = registerItem("essence_of_quick_charge", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Mace
    public static final Item ESSENCE_OF_DENSITY = registerItem("essence_of_density", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_BREACH = registerItem("essence_of_breach", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_WIND = registerItem("essence_of_wind", new Item(new Item.Settings().maxCount(1).fireproof()));


    //Neo Enchant Plus
    public static final Item ESSENCE_OF_POISON = registerItem("essence_of_poison", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_POISON_PROTECTION = registerItem("essence_of_poison_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_MINING = registerItem("essence_of_mining", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SMELTING = registerItem("essence_of_smelting", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SIGHT = registerItem("essence_of_sight", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_LEVITATION = registerItem("essence_of_levitation", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FORAGING = registerItem("essence_of_foraging", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_STRIKE = registerItem("essence_of_strike", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_HEALTH = registerItem("essence_of_health", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_VAMPIRISM = registerItem("essence_of_vampirism", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_AGILITY = registerItem("essence_of_agility", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_COMBAT = registerItem("essence_of_combat", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_BUILDING = registerItem("essence_of_building", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FEAR = registerItem("essence_of_fear", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_REACH = registerItem("essence_of_reach", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_PULLING = registerItem("essence_of_pulling", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_FOOD = registerItem("essence_of_food", new Item(new Item.Settings().maxCount(1).fireproof()));

    //The Bumblezone
    public static final Item ESSENCE_OF_NEUROTOXIN = registerItemBulblezoneCompat("essence_of_neurotoxin", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_COMB_CUTTER = registerItemBulblezoneCompat("essence_of_comb_cutter", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Dungeons and Taverns
    public static final Item ESSENCE_OF_PHOTOSYNTHESIS = registerItem("essence_of_photosynthesis", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_GRAVITY = registerItem("essence_of_gravity", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_WINGS = registerItem("essence_of_wings", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Diversity
    public static final Item ESSENCE_OF_CAPACITY = registerItem("essence_of_capacity", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_REFILL = registerItem("essence_of_refill", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Toss Up
    public static final Item ESSENCE_OF_EXPLOSION = registerItem("essence_of_explosion", new Item(new Item.Settings().maxCount(1).fireproof()));

    //Spell engine
    public static final Item ESSENCE_OF_MULTI_ROLL = registerItem("essence_of_multi_roll", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_LONGFOOT = registerItem("essence_of_longfoot", new Item(new Item.Settings().maxCount(1).fireproof()));

    public static final Item ESSENCE_OF_CRITICAL_SPELL = registerItem("essence_of_critical_spell", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_ENERGY = registerItem("essence_of_energy", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_HASTE = registerItem("essence_of_haste", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_MAGIC_PROTECTION = registerItem("essence_of_magic_protection", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SOULFROST = registerItem("essence_of_soulfrost", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SPELL_POWER = registerItem("essence_of_spell_power", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SUNFIRE = registerItem("essence_of_sunfire", new Item(new Item.Settings().maxCount(1).fireproof()));
    public static final Item ESSENCE_OF_SPELL_INFINITY = registerItem("essence_of_spell_infinity", new Item(new Item.Settings().maxCount(1).fireproof()));


    //endregion

    private static Item registerItem(String id, Item item){
        Item i = Registry.register(Registries.ITEM, Utils.id(id), item);
        MOD_ITEM_LIST.add(i);
        return i;
    }

    private static Item registerItemBulblezoneCompat(String id, Item item){
        Item i = Registry.register(Registries.ITEM, Utils.id(id), item);
        MOD_ITEM_LIST_BUMBLEZONE_COMPAT.add(i);
        return i;
    }



}