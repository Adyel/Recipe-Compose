package io.github.adyel.recipe.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
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
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)


    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            val result = repository.search(
                    token = token,
                    page = 1,
                    query = query.value
            )
            recipes.value = result
        }
    }

    fun onQueryChange(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChange(category)
    }
}