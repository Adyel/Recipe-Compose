package io.github.adyel.recipe.network.response

import com.google.gson.annotations.SerializedName
import io.github.adyel.recipe.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipies: List<RecipeDto>
)