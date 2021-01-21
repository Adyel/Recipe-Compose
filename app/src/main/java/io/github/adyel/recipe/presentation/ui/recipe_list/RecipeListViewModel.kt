package io.github.adyel.recipe.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.adyel.recipe.domain.model.Recipe
import io.github.adyel.recipe.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel

@ViewModelInject
constructor(
    private val repository : RecipeRepository,
    private val token: String
) : ViewModel(){

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            val result = repository.search(
                    token = token,
                    page = 1,
                    query = "chicken",
            )
            recipes.value = result
        }
    }
}