package io.github.adyel.recipe.repository

import io.github.adyel.recipe.domain.model.Recipe
import io.github.adyel.recipe.network.model.RecipeDtoMapper
import io.github.adyel.recipe.network.service.RecipeService

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val recipeMapper: RecipeDtoMapper
) : RecipeRepository{
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return recipeMapper.toDomainList(
            recipeService.search(token, page, query).recipies
        )
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return recipeMapper.mapToDomainModel(
            recipeService.get(token, id)
        )
    }

}