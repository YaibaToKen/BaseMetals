package com.mcmoddev.lib.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mcmoddev.basemetals.BaseMetals;
import com.mcmoddev.lib.registry.CrusherRecipeRegistry;
import com.mcmoddev.lib.registry.recipe.ArbitraryCrusherRecipe;
import com.mcmoddev.lib.registry.recipe.ICrusherRecipe;
import com.mcmoddev.lib.registry.recipe.OreDictionaryCrusherRecipe;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

/**
 *
 * @author Jasmine Iwanek
 * @author Daniel Hazelton
 * 
 */
@JEIPlugin
public class BaseMetalsJEIPlugin extends BlankModPlugin {

	public static final String JEIUID = BaseMetals.MODID;
	public static final String recipeUID = JEIUID + ".crackhammer";

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

		registry.addRecipeCategories(new ICrusherRecipeCategory(guiHelper));
	}


	@Override
	public void register(IModRegistry registry) {
		registry.addRecipes(CrusherRecipeRegistry.getInstance().getAllRecipes().stream().map((ICrusherRecipe in) -> new ICrusherRecipeWrapper(in)).collect(Collectors.toList()), recipeUID);

		registry.handleRecipes(ICrusherRecipe.class, new IRecipeWrapperFactory<ICrusherRecipe>() {
			@Override
			public IRecipeWrapper getRecipeWrapper(ICrusherRecipe recipe) {
				return new ICrusherRecipeWrapper(recipe);
			}
		}, recipeUID);		
	}
}
