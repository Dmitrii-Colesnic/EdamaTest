package com.example.data.network.recipe_search

import com.example.data.network.ResponseError
import com.example.data.network.ResponseException
import com.example.data.network.ResponseSuccess
import com.example.data.network.recipe_search.model.RecipeResponseDataModel
import com.example.domain.recipe_search.*
import com.example.domain.recipe_search.models.*

class RecipeSearchRepoImpl(private val recipeApiRemoteSource: RecipeApiRemoteSource) : RecipeSearchRepo {
    override suspend fun getRecipe(
        appId: String,
        appKey: String,
        keyWord: String,
        calories: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): RecipeSearchResponse<RecipeResponseDomainModel> {
        return try {
            val response = recipeApiRemoteSource.invoke(
                appId = appId,
                appKey = appKey,
                keyWord = keyWord,
                calories = calories,
                diet = diet,
                health = health,
                cuisineType = cuisineType,
                nutrients = nutrients
            )

            response.let {
                when (it) {
                    is ResponseSuccess -> RecipeSearchResponseSuccess(
                        data = it.data.toDomainModel()
                    )
                    is ResponseError -> RecipeSearchResponseError(
                        code = it.code, message = it.message
                    )
                    is ResponseException -> RecipeSearchResponseException(
                        e = it.e
                    )
                }
            }
        } catch (e: Throwable) {
            RecipeSearchResponseException(e = e)
        }
    }

}

private fun RecipeResponseDataModel.toDomainModel() = RecipeResponseDomainModel(
    from = this.from,
    to = this.from,
    count = this.count,
    _links = Links(
        self = SubLink(
            href = this._links.self.href, title = this._links.self.title
        ), next = SubLink(
            href = this._links.next.href, title = this._links.next.title
        )
    ),
    hits = Hits(
        recipe = Recipe(
            uri = this.hits.recipe.uri,
            label = this.hits.recipe.label,
            image = this.hits.recipe.image,
            source = this.hits.recipe.source,
            url = this.hits.recipe.url,
            yield = this.hits.recipe.yield,
            dietLabels = this.hits.recipe.dietLabels,
            healthLabels = this.hits.recipe.healthLabels,
            cautions = this.hits.recipe.cautions,
            ingredientLines = this.hits.recipe.ingredientLines,
            cuisineType = this.hits.recipe.cuisineType,
            mealType = this.hits.recipe.mealType,
            dishType = this.hits.recipe.dishType,
            calories = this.hits.recipe.calories,
            totalWeight = this.hits.recipe.totalWeight,
            totalTime = this.hits.recipe.totalTime,
            totalNutrients = TotalNutrients(
                FAT = Nutrient(
                    label = this.hits.recipe.totalNutrients.FAT.label,
                    quantity = this.hits.recipe.totalNutrients.FAT.quantity,
                    unit = this.hits.recipe.totalNutrients.FAT.unit,
                ),
                SUGAR = Nutrient(
                    label = this.hits.recipe.totalNutrients.SUGAR.label,
                    quantity = this.hits.recipe.totalNutrients.SUGAR.quantity,
                    unit = this.hits.recipe.totalNutrients.SUGAR.unit,
                ),
                PROCNT = Nutrient(
                    label = this.hits.recipe.totalNutrients.PROCNT.label,
                    quantity = this.hits.recipe.totalNutrients.PROCNT.quantity,
                    unit = this.hits.recipe.totalNutrients.PROCNT.unit,
                ),
                CHOLE = Nutrient(
                    label = this.hits.recipe.totalNutrients.CHOLE.label,
                    quantity = this.hits.recipe.totalNutrients.CHOLE.quantity,
                    unit = this.hits.recipe.totalNutrients.CHOLE.unit,
                ),
                NA = Nutrient(
                    label = this.hits.recipe.totalNutrients.NA.label,
                    quantity = this.hits.recipe.totalNutrients.NA.quantity,
                    unit = this.hits.recipe.totalNutrients.NA.unit,
                ),
                CA = Nutrient(
                    label = this.hits.recipe.totalNutrients.CA.label,
                    quantity = this.hits.recipe.totalNutrients.CA.quantity,
                    unit = this.hits.recipe.totalNutrients.CA.unit,
                ),
                MG = Nutrient(
                    label = this.hits.recipe.totalNutrients.MG.label,
                    quantity = this.hits.recipe.totalNutrients.MG.quantity,
                    unit = this.hits.recipe.totalNutrients.MG.unit,
                ),
                K = Nutrient(
                    label = this.hits.recipe.totalNutrients.K.label,
                    quantity = this.hits.recipe.totalNutrients.K.quantity,
                    unit = this.hits.recipe.totalNutrients.K.unit,
                ),
                FE = Nutrient(
                    label = this.hits.recipe.totalNutrients.FE.label,
                    quantity = this.hits.recipe.totalNutrients.FE.quantity,
                    unit = this.hits.recipe.totalNutrients.FE.unit,
                ),
                ZN = Nutrient(
                    label = this.hits.recipe.totalNutrients.ZN.label,
                    quantity = this.hits.recipe.totalNutrients.ZN.quantity,
                    unit = this.hits.recipe.totalNutrients.ZN.unit,
                ),
            ),
        )
    )
)
