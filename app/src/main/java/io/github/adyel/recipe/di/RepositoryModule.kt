package io.github.adyel.recipe.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.adyel.recipe.network.model.RecipeDtoMapper
import io.github.adyel.recipe.network.service.RecipeService
import io.github.adyel.recipe.repository.RecipeRepository
import io.github.adyel.recipe.repository.RecipeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository{
        return RecipeRepositoryImpl(
            recipeMapper = recipeDtoMapper,
            recipeService = recipeService
        )
    }
}