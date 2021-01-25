package io.github.adyel.recipe.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.adyel.recipe.domain.model.Recipe
import io.github.adyel.recipe.presentation.components.FoodCategoryChip
import io.github.adyel.recipe.presentation.components.RecipeCard

@AndroidEntryPoint
class RecipeListFragment : Fragment(){


    private val viewModel: RecipeListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
                setContent {

                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value

                    Column {

                        Surface(
                            elevation = 8.dp,
                            modifier = Modifier.fillMaxWidth(),
//                            color = MaterialTheme.colors.primary,
                        color = Color.White
                        ) {
                            
                            Column {
                                Row(
                                        modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                            modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(8.dp),
                                            value = query,
                                            onValueChange = {
                                                viewModel.onQueryChange(it)
                                            },
                                            label = {
                                                Text(text = "Search")
                                            },
                                            keyboardOptions = KeyboardOptions(
                                                    keyboardType = KeyboardType.Text,
                                                    imeAction = ImeAction.Search,
                                            ),
                                            leadingIcon = {
                                                Icon(Icons.Filled.Search)
                                            },
                                            onImeActionPerformed = { imeAction, softwareKeyboardController ->
                                                if (imeAction == ImeAction.Search) {
                                                    viewModel.newSearch()
                                                    softwareKeyboardController?.hideSoftwareKeyboard()
                                                }
                                            },
                                            textStyle = TextStyle(
                                                    color = MaterialTheme.colors.onSurface,
                                            ),
                                            backgroundColor = MaterialTheme.colors.surface,
                                    )
                                }

                                ScrollableRow(
                                        modifier = Modifier.fillMaxWidth()
                                                .padding(
                                                        start = 8.dp,
                                                        end = 8.dp,
                                                        bottom = 8.dp
                                                )
                                ) {
                                    for (category in getAllFoodCategories()){
                                        FoodCategoryChip(
                                                catagory = category.value,
                                                isSelected = selectedCategory == category,
                                                onSelectedCategoryChanged = {
                                                    viewModel.onSelectedCategoryChanged(it)
                                                },
                                                onExecuteSearch = {
                                                    viewModel::newSearch
                                                })

                                    }
                                }
                            }
                        }
                        LazyColumn(content = {
                            itemsIndexed(
                                items = recipes
                            ) { index: Int, recipe: Recipe ->
                                RecipeCard(recipe = recipe, onClick = {})
                            }
                        })
                    }

                }
            }
    }
}