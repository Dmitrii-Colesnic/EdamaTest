package com.example.data.network.recipe_search

import android.util.Log
import com.example.data.network.ResponseError
import com.example.data.network.ResponseException
import com.example.data.network.ResponseSuccess
import com.example.data.network.recipe_search.model.Nutrient
import com.example.data.network.recipe_search.model.RecipeResponseDataModel
import com.example.domain.ResponseDomain
import com.example.domain.ResponseErrorDomain
import com.example.domain.ResponseExceptionDomain
import com.example.domain.ResponseSuccessDomain
import com.example.domain.recipe_search.*
import com.example.domain.recipe_search.models.*

class RecipeSearchRepoImpl(
    private val recipeApiRemoteSource: RecipeApiRemoteSource
) : RecipeSearchRepo {
    override suspend fun getRecipe(
        type: String,
        appId: String,
        appKey: String,
        keyWord: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): ResponseDomain<RecipeResponseDomainModel> {
        return try {
            val response = recipeApiRemoteSource.invokeSearchQuery(
                type = type,
                appId = appId,
                appKey = appKey,
                keyWord = keyWord,
                diet = diet,
                health = health,
                cuisineType = cuisineType,
                nutrients = nutrients
            )

            response.let {
                when (it) {
                    is ResponseSuccess -> ResponseSuccessDomain(
                        data = it.data.toDomainModel()
                    )
                    is ResponseError -> ResponseErrorDomain(
                        code = it.code, message = it.message
                    )
                    is ResponseException -> {
                        ResponseExceptionDomain(
                            e = it.e
                        )
                    }
                }
            }
        } catch (e: Throwable) {
            Log.d("okhttp", "DomainLayer Mapping Exception - ${e.message}")
            ResponseExceptionDomain(e = e)
        }
    }

    override suspend fun getRecipeNext(
        url: String
    ): ResponseDomain<RecipeResponseDomainModel> {
        return try {
            val response = recipeApiRemoteSource.invokeSearchQueryNext(
                url = url
            )

            response.let {
                when (it) {
                    is ResponseSuccess -> ResponseSuccessDomain(
                        data = it.data.toDomainModel()
                    )
                    is ResponseError -> ResponseErrorDomain(
                        code = it.code, message = it.message
                    )
                    is ResponseException -> {
                        ResponseExceptionDomain(
                            e = it.e
                        )
                    }
                }
            }
        } catch (e: Throwable) {
            Log.d("okhttp", "DomainLayer Mapping Exception - ${e.message}")
            ResponseExceptionDomain(e = e)
        }
    }

}

private fun RecipeResponseDataModel.toDomainModel() = RecipeResponseDomainModel(
    from = from,
    to = from,
    count = count,
    _links = _links.toDomain(),
    hits = hits.map { it.toDomain() } as ArrayList<com.example.domain.recipe_search.models.Hits>
)

fun com.example.data.network.recipe_search.model.Links.toDomain() = Links(
    self = self.toDomain(),
    next = next.toDomain()
)

fun com.example.data.network.recipe_search.model.SubLink?.toDomain(): SubLink? {
    return if (this == null) {
        null
    } else {
        SubLink(
            href = href,
            title = title
        )
    }
}

fun com.example.data.network.recipe_search.model.Hits.toDomain() = Hits(
    recipe = Recipe(
        uri = recipe.uri,
        label = recipe.label,
        image = recipe.image,
        source = recipe.source,
        url = recipe.url,
        yield = recipe.yield,
        dietLabels = recipe.dietLabels,
        healthLabels = recipe.healthLabels,
        cautions = recipe.cautions,
        ingredientLines = recipe.ingredientLines,
        cuisineType = recipe.cuisineType,
        mealType = recipe.mealType,
        dishType = recipe.dishType,
        calories = recipe.calories,
        totalWeight = recipe.totalWeight,
        totalTime = recipe.totalTime,
        totalNutrients = recipe.totalNutrients.toDomain()
    )
)

fun com.example.data.network.recipe_search.model.TotalNutrients.toDomain() =
    com.example.domain.recipe_search.models.TotalNutrients(
        FAT = FAT.toDomain(),
        SUGAR = SUGAR.toDomain(),
        CHOCDF = CHOCDF.toDomain(),
        PROCNT = PROCNT.toDomain(),
        CHOLE = CHOLE.toDomain(),
        NA = NA.toDomain(),
        CA = CA.toDomain(),
        MG = MG.toDomain(),
        K = K.toDomain(),
        FE = FE.toDomain(),
    )

fun Nutrient.toDomain() = com.example.domain.recipe_search.models.Nutrient(
    label = label,
    quantity = quantity,
    unit = unit
)

