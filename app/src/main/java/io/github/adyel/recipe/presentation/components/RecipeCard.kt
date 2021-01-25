package io.github.adyel.recipe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.adyel.recipe.R
import io.github.adyel.recipe.domain.model.Recipe
import io.github.adyel.recipe.utils.DEFAULT_IMAGE_PLACEHOLDER
import io.github.adyel.recipe.utils.loadPicture

@Composable
fun RecipeCard(
        recipe: Recipe,
        onClick: () -> Unit,
) {
    Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(
                    bottom = 6.dp,
                    top = 6.dp,
                    start = 6.dp,
                    end = 6.dp
                )
                .fillMaxWidth()
                .clickable(onClick = onClick),
            elevation = 8.dp,
    ) {
        Column {
            recipe.featuredImage?.let {

                val image = loadPicture(url = it, defaultImage = DEFAULT_IMAGE_PLACEHOLDER).value

                image?.let {
                    Image(
                        bitmap = image.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(255.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }

                recipe.title?.let {
                    Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 10.dp,
                                    bottom = 10.dp,
                                    start = 10.dp,
                                    end = 10.dp,
                                )
                    ) {
                        Text(
                                text =  it,
                                modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .wrapContentWidth(Alignment.Start),
                                style = MaterialTheme.typography.h5
                        )
                        Text(
                                text = recipe.rating.toString(),
                                modifier = Modifier.fillMaxWidth()
                                        .wrapContentWidth(Alignment.End)
                                        .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.h6
                        )

                    }
                }
            }
        }
    }
}