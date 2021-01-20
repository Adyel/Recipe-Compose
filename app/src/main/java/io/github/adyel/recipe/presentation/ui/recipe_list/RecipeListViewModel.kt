package io.github.adyel.recipe.presentation.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.adyel.recipe.repository.RecipeRepository

class RecipeListViewModel

@ViewModelInject
constructor(
    private val repository : RecipeRepository,
    private val token: String
) : ViewModel(){
}