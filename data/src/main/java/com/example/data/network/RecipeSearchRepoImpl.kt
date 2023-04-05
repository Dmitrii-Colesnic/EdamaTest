package com.example.data.network

import com.example.domain.recipe_search.RecipeSearchRepo
import com.example.domain.recipe_search.models.Recipe
import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import com.example.domain.recipe_search.models.SubLink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeSearchRepoImpl(private val service: EdamamService) : RecipeSearchRepo {
    override suspend fun getRecipe(
        appId: String,
        appKey: String,
        keyWord: String,
        calories: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients_fat: String,
        fasat_nutrients: String,
        fatrn_nutrients: String,
        fams_nutrients: String,
        fapu_nutrients: String,
        chocdf_nutrients: String,
        fibtg_nutrients: String,
        sugar_nutrients: String,
        procnt_nutrients: String,
        chole_nutrients: String,
        na_nutrients: String,
        ca_nutrients: String,
        mg_nutrients: String,
        k_nutrients: String,
        fe_nutrients: String,
        p_nutrients: String,
        vita_rae_nutrients: String,
        vitc_nutrients: String,
        thia_nutrients: String,
        ribf_nutrients: String,
        nia_nutrients: String,
        vitb6a_nutrients: String,
        foldfe_nutrients: String,
        vitb12_nutrients: String,
        vitd_nutrients: String,
        tocpha_nutrients: String,
        vitkq_nutrients: String
    ): Flow<RecipeResponseDomainModel?> {
        return flow {
            emit(
                service.recipeSearchQuery(
                    appId,
                    appKey,
                    keyWord,
                    calories,
                    diet,
                    health,
                    cuisineType,
                    nutrients_fat,
                    fasat_nutrients,
                    fatrn_nutrients,
                    fams_nutrients,
                    fapu_nutrients,
                    chocdf_nutrients,
                    fibtg_nutrients,
                    sugar_nutrients,
                    procnt_nutrients,
                    chole_nutrients,
                    na_nutrients,
                    ca_nutrients,
                    mg_nutrients,
                    k_nutrients,
                    fe_nutrients,
                    p_nutrients,
                    vita_rae_nutrients,
                    vitc_nutrients,
                    thia_nutrients,
                    ribf_nutrients,
                    nia_nutrients,
                    vitb6a_nutrients,
                    foldfe_nutrients,
                    vitb12_nutrients,
                    vitd_nutrients,
                    tocpha_nutrients,
                    vitkq_nutrients
                ).body()?.toDomainModel()
            )
        }
    }
}

private fun RecipeResponseDataModel.toDomainModel() = RecipeResponseDomainModel(
    from = this.from,
    to = this.from,
    count = this.count,
    _links = com.example.domain.recipe_search.models.Links(
        self = SubLink(
            href = this._links.self.href, title = this._links.self.title
        ), next = SubLink(
            href = this._links.next.href, title = this._links.next.title
        )
    ),
    hits = com.example.domain.recipe_search.models.Hits(
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
            totalNutrients = com.example.domain.recipe_search.models.TotalNutrients(
                FAT = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.FAT.label,
                    quantity = this.hits.recipe.totalNutrients.FAT.quantity,
                    unit = this.hits.recipe.totalNutrients.FAT.unit,
                ),
                SUGAR = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.SUGAR.label,
                    quantity = this.hits.recipe.totalNutrients.SUGAR.quantity,
                    unit = this.hits.recipe.totalNutrients.SUGAR.unit,
                ),
                PROCNT = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.PROCNT.label,
                    quantity = this.hits.recipe.totalNutrients.PROCNT.quantity,
                    unit = this.hits.recipe.totalNutrients.PROCNT.unit,
                ),
                CHOLE = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.CHOLE.label,
                    quantity = this.hits.recipe.totalNutrients.CHOLE.quantity,
                    unit = this.hits.recipe.totalNutrients.CHOLE.unit,
                ),
                NA = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.NA.label,
                    quantity = this.hits.recipe.totalNutrients.NA.quantity,
                    unit = this.hits.recipe.totalNutrients.NA.unit,
                ),
                CA = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.CA.label,
                    quantity = this.hits.recipe.totalNutrients.CA.quantity,
                    unit = this.hits.recipe.totalNutrients.CA.unit,
                ),
                MG = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.MG.label,
                    quantity = this.hits.recipe.totalNutrients.MG.quantity,
                    unit = this.hits.recipe.totalNutrients.MG.unit,
                ),
                K = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.K.label,
                    quantity = this.hits.recipe.totalNutrients.K.quantity,
                    unit = this.hits.recipe.totalNutrients.K.unit,
                ),
                FE = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.FE.label,
                    quantity = this.hits.recipe.totalNutrients.FE.quantity,
                    unit = this.hits.recipe.totalNutrients.FE.unit,
                ),
                ZN = com.example.domain.recipe_search.models.Nutrient(
                    label = this.hits.recipe.totalNutrients.ZN.label,
                    quantity = this.hits.recipe.totalNutrients.ZN.quantity,
                    unit = this.hits.recipe.totalNutrients.ZN.unit,
                ),
            ),
        )
    )
)