package io.github.adyel.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
        catagory: String,
        isSelected: Boolean = false,
        onSelectedCategoryChanged: (String) -> Unit,
        onExecuteSearch: () -> Unit,
) {
    Surface(
            modifier = Modifier.padding(end = 8.dp),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium,
            color = if (isSelected) {
                Color.LightGray
            } else {
                MaterialTheme.colors.primary
            },
    ) {
        Row(
                modifier = Modifier.toggleable(
                        value = isSelected,
                        onValueChange = {
                            onSelectedCategoryChanged(catagory)
                            onExecuteSearch()
                        }
                )) {
            Text(
                    text = catagory,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(8.dp)
            )
        }
    }
}