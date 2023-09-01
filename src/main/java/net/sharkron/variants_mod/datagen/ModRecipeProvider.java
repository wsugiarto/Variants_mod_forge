package net.sharkron.variants_mod.datagen;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.sharkron.variants_mod.VariantsMod;
import net.sharkron.variants_mod.block.ModBlocks;
import net.sharkron.variants_mod.item.ModItems;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{

    private static final List<ItemLike> TOPAZ_SMELTABLES = List.of(ModBlocks.TOPAZ_ORE.get());

    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ.get(), 0.25F, 100, "topaz");
        oreSmelting(pWriter, TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ.get(), 0.25F, 200, "topaz");
    
        // Templates
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TOPAZ_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', ModItems.TOPAZ.get())
            .unlockedBy(getHasName(ModItems.TOPAZ.get()), has(ModItems.TOPAZ.get()))
            .save(pWriter);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOPAZ.get(), 9)
                .requires(ModBlocks.TOPAZ_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TOPAZ_BLOCK.get()), has(ModBlocks.TOPAZ_BLOCK.get()))
                .save(pWriter);
        
        // Staves
        staffCrafting(RecipeCategory.COMBAT, ModItems.CHARCOAL_STAFF.get(), Items.CHARCOAL, ItemTags.PLANKS, ItemTags.LOGS, pWriter);
        staffCrafting(RecipeCategory.COMBAT, ModItems.COPPER_STAFF.get(), Items.COPPER_BLOCK, Items.STONE, ItemTags.STONE_TOOL_MATERIALS, pWriter);
        staffCrafting(RecipeCategory.COMBAT, ModItems.SPORE_STAFF.get(), Items.SPORE_BLOSSOM, Items.IRON_BLOCK, Items.MOSS_BLOCK, pWriter);
        staffCrafting(RecipeCategory.COMBAT, ModItems.TOPAZ_STAFF.get(), ModBlocks.TOPAZ_BLOCK.get(), Items.GOLD_INGOT, Items.GOLD_BLOCK, pWriter);
        staffCrafting(RecipeCategory.COMBAT, ModItems.DIAMOND_STAFF.get(), Items.DIAMOND_BLOCK, Items.ENDER_PEARL, Items.ENDER_PEARL, pWriter);
        netheriteSmithing(pWriter, Items.TRIDENT, RecipeCategory.COMBAT, ModItems.NETHERITE_FORK.get());

        // Spellbooks
        spellbookCrafting(RecipeCategory.COMBAT, ModItems.TORCH_TOMB.get(), Items.TORCH, Items.COAL_BLOCK, ItemTags.LOGS, pWriter);
        spellbookCrafting(RecipeCategory.COMBAT, ModItems.REDSTONE_SPELLBOOK.get(), Items.REDSTONE_BLOCK, Items.IRON_BLOCK, Items.IRON_INGOT, pWriter);
        spellbookCrafting(RecipeCategory.COMBAT, ModItems.AMETHYST_SPELLBOOK.get(), Items.AMETHYST_SHARD, Items.GOLD_BLOCK, Items.GOLD_INGOT, pWriter);
                
        // Daggers
        daggerCrafting(RecipeCategory.COMBAT, ModItems.WOODEN_STAKE.get(), ItemTags.LOGS, pWriter);
        daggerCrafting(RecipeCategory.COMBAT, ModItems.STONE_DAGGER.get(), ItemTags.STONE_TOOL_MATERIALS, pWriter);
        daggerCrafting(RecipeCategory.COMBAT, ModItems.IRON_DAGGER.get(), Items.IRON_INGOT, pWriter);
        daggerCrafting(RecipeCategory.COMBAT, ModItems.GOLD_DAGGER.get(), Items.GOLD_INGOT, pWriter);
        daggerCrafting(RecipeCategory.COMBAT, ModItems.DIAMOND_DAGGER.get(), Items.DIAMOND, pWriter);
        netheriteSmithing(pWriter, ModItems.DIAMOND_DAGGER.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_DAGGER.get());

        // Guns
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOULETS.get(), 8)
                .requires(Items.IRON_NUGGET, 8)
                .requires(Items.GUNPOWDER)
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLOWPIPE.get())
            .pattern("   ")
            .pattern("SSS")
            .pattern("   ")
            .define('S', Items.STICK)
            .unlockedBy("has_log", has(Items.STICK))
            .save(pWriter);
        
        gunCrafting(RecipeCategory.COMBAT, ModItems.STONE_LAUNCHER.get(), Items.FURNACE, Items.SMOOTH_STONE, pWriter);
        gunCrafting(RecipeCategory.COMBAT, ModItems.HANDGUN.get(), Items.BLAST_FURNACE, Items.IRON_INGOT, pWriter);
        gunCrafting(RecipeCategory.COMBAT, ModItems.TNT_GUN.get(), Items.TNT, Items.NETHERITE_INGOT, pWriter);
    }
    
    protected static void daggerCrafting(RecipeCategory category, ItemLike result, ItemLike material, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern(" S ")
            .pattern("SIS")
            .pattern("   ")
            .define('S', material)
            .define('I', Items.STICK)
            .unlockedBy(getHasName(material), has(material))
            .save(pWriter);
    }

    protected static void daggerCrafting(RecipeCategory category, ItemLike result, TagKey<Item> material, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern(" S ")
            .pattern("SIS")
            .pattern("   ")
            .define('S', material)
            .define('I', Items.STICK)
            .unlockedBy("has_log", has(material))
            .save(pWriter);
    }

    protected static void staffCrafting(RecipeCategory category, ItemLike result, ItemLike crystal, ItemLike stick, ItemLike holder, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("#B ")
            .pattern("BS ")
            .pattern("  S")
            .define('B', holder)
            .define('S', stick)
            .define('#', crystal)
            .unlockedBy(getHasName(crystal), has(crystal))
            .save(pWriter);
    }

    protected static void staffCrafting(RecipeCategory category, ItemLike result, ItemLike crystal, ItemLike stick, TagKey<Item> holder, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("#B ")
            .pattern("BS ")
            .pattern("  S")
            .define('B', holder)
            .define('S', stick)
            .define('#', crystal)
            .unlockedBy(getHasName(crystal), has(crystal))
            .save(pWriter);
    }

    protected static void staffCrafting(RecipeCategory category, ItemLike result, ItemLike crystal, TagKey<Item> stick, TagKey<Item> holder, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("#B ")
            .pattern("BS ")
            .pattern("  S")
            .define('B', holder)
            .define('S', stick)
            .define('#', crystal)
            .unlockedBy(getHasName(crystal), has(crystal))
            .save(pWriter);
    }

    protected static void spellbookCrafting(RecipeCategory category, ItemLike result, ItemLike specialTop, ItemLike specialBottom, ItemLike border, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("TST")
            .pattern("TBT")
            .pattern("T#T")
            .define('B', Items.BOOK)
            .define('S', specialTop)
            .define('#', specialBottom)
            .define('T', border)
            .unlockedBy(getHasName(specialTop), has(specialTop))
            .save(pWriter);
    }

    protected static void spellbookCrafting(RecipeCategory category, ItemLike result, ItemLike specialTop, ItemLike specialBottom, TagKey<Item> border, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("TST")
            .pattern("TBT")
            .pattern("T#T")
            .define('B', Items.BOOK)
            .define('S', specialTop)
            .define('#', specialBottom)
            .define('T', border)
            .unlockedBy(getHasName(specialTop), has(specialTop))
            .save(pWriter);
    }

    protected static void gunCrafting(RecipeCategory category, ItemLike result, ItemLike special, ItemLike shafts, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("   ")
            .pattern("GST")
            .pattern("T  ")
            .define('G', Items.GUNPOWDER)
            .define('S', special)
            .define('T', shafts)
            .unlockedBy(getHasName(special), has(special))
            .save(pWriter);
    }

    protected static void gunCrafting(RecipeCategory category, ItemLike result, ItemLike special, TagKey<Item> shafts, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(category, result)
            .pattern("   ")
            .pattern("GST")
            .pattern("T  ")
            .define('G', Items.GUNPOWDER)
            .define('S', special)
            .define('T', shafts)
            .unlockedBy(getHasName(special), has(special))
            .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike),
            p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
            .group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike))
            .save(p_250791_, VariantsMod.MODID + ":" + getItemName(p_250066_) + p_249236_ + "_" + getItemName(itemlike));
        }

    }

}
