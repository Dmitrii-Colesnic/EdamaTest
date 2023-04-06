package com.example.data.network

import com.example.data.network.model.RecipeResponseDataModel
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EdamamService {

    @GET("/api/recipes/v2")
    suspend fun recipeSearchQuery(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("q") keyWord: String,
        @Query("calories") calories: String,
        @Query("diet") diet: List<String>,
        @Query("health") health: List<String>,
        @Query("cuisineType") cuisineType: List<String>,
        @QueryMap nutrients: Map<String, String>
    ): Response<String>

}

class EdamamServiceRemoteSource(private val service: EdamamService) {
    suspend fun getRecipe(
        appId: String,
        appKey: String,
        keyWord: String,
        calories: String,
        diet: List<String>,
        health: List<String>,
        cuisineType: List<String>,
        nutrients: Map<String, String>
    ): Responses = try {
        val response = service.recipeSearchQuery(
            appId = appId,
            appKey = appKey,
            keyWord = keyWord,
            calories = calories,
            diet = diet,
            health = health,
            cuisineType = cuisineType,
            nutrients = nutrients
        )

        when (response.code()) {
            200 -> {
                val body = response.body()!!
                val parseResult = body
                Responses.Success(parseResult)
            }
            401, 402 -> {
                Responses.NetworkError(" ")
            }
        }

    } catch (e: Exception) {
        Responses.UnknownError
    }
}

sealed class Responses {
    data class Success(val a: String) : Responses()
    data class NetworkError(val a: String) : Responses()
    object UnknownError : Responses()
}


//@Query("nutrients[FAT]") nutrients_fat: String,
//@Query("nutrients[FASAT]") fasat_nutrients: String,
//@Query("nutrients[FATRN]") fatrn_nutrients: String,
//@Query("nutrients[FAMS]") fams_nutrients: String,
//@Query("nutrients[FAPU]") fapu_nutrients: String,
//@Query("nutrients[CHOCDF]") chocdf_nutrients: String,
//@Query("nutrients[FIBTG]") fibtg_nutrients: String,
//@Query("nutrients[SUGAR]") sugar_nutrients: String,
//@Query("nutrients[PROCNT]") procnt_nutrients: String,
//@Query("nutrients[CHOLE]") chole_nutrients: String,
//@Query("nutrients[NA]") na_nutrients: String,
//@Query("nutrients[CA]") ca_nutrients: String,
//@Query("nutrients[MG]") mg_nutrients: String,
//@Query("nutrients[K]") k_nutrients: String,
//@Query("nutrients[FE]") fe_nutrients: String,
//@Query("nutrients[P]") p_nutrients: String,
//@Query("nutrients[VITA_RAE]") vita_rae_nutrients: String,
//@Query("nutrients[VITC]") vitc_nutrients: String,
//@Query("nutrients[THIA]") thia_nutrients: String,
//@Query("nutrients[RIBF]") ribf_nutrients: String,
//@Query("nutrients[NIA]") nia_nutrients: String,
//@Query("nutrients[VITB6A]") vitb6a_nutrients: String,
//@Query("nutrients[FOLDFE]") foldfe_nutrients: String,
//@Query("nutrients[VITB12]") vitb12_nutrients: String,
//@Query("nutrients[VITD]") vitd_nutrients: String,
//@Query("nutrients[TOCPHA]") tocpha_nutrients: String,
//@Query("nutrients[VITK1]") vitkq_nutrients: String