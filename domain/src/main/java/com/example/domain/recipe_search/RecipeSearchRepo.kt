package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel

interface RecipeSearchRepo {
    suspend fun getRecipe(
        type: String,
        appId: String,
        appKey: String,
        keyWord: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): RecipeSearchResponse<RecipeResponseDomainModel>

    suspend fun getRecipeNext(
        url: String
    ): RecipeSearchResponse<RecipeResponseDomainModel>
}

sealed interface RecipeSearchResponse<T : Any>

class RecipeSearchResponseSuccess<T : Any>(val data: T) : RecipeSearchResponse<T>
class RecipeSearchResponseError<T : Any>(val code: Int, val message: String?) : RecipeSearchResponse<T>
class RecipeSearchResponseException<T : Any>(val e: Throwable) : RecipeSearchResponse<T>

//nutrients_fat: String,
//fasat_nutrients: String,
//fatrn_nutrients: String,
//fams_nutrients: String,
//fapu_nutrients: String,
//chocdf_nutrients: String,
//fibtg_nutrients: String,
//sugar_nutrients: String,
//procnt_nutrients: String,
//chole_nutrients: String,
//na_nutrients: String,
//ca_nutrients: String,
//mg_nutrients: String,
//k_nutrients: String,
//fe_nutrients: String,
//p_nutrients: String,
//vita_rae_nutrients: String,
//vitc_nutrients: String,
//thia_nutrients: String,
//ribf_nutrients: String,
//nia_nutrients: String,
//vitb6a_nutrients: String,
//foldfe_nutrients: String,
//vitb12_nutrients: String,
//vitd_nutrients: String,
//tocpha_nutrients: String,
//vitkq_nutrients: String