package com.example.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

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
        @Query("nutrients[FAT]") nutrients_fat: String,
        @Query("nutrients[FASAT]") fasat_nutrients: String,
        @Query("nutrients[FATRN]") fatrn_nutrients: String,
        @Query("nutrients[FAMS]") fams_nutrients: String,
        @Query("nutrients[FAPU]") fapu_nutrients: String,
        @Query("nutrients[CHOCDF]") chocdf_nutrients: String,
        @Query("nutrients[FIBTG]") fibtg_nutrients: String,
        @Query("nutrients[SUGAR]") sugar_nutrients: String,
        @Query("nutrients[PROCNT]") procnt_nutrients: String,
        @Query("nutrients[CHOLE]") chole_nutrients: String,
        @Query("nutrients[NA]") na_nutrients: String,
        @Query("nutrients[CA]") ca_nutrients: String,
        @Query("nutrients[MG]") mg_nutrients: String,
        @Query("nutrients[K]") k_nutrients: String,
        @Query("nutrients[FE]") fe_nutrients: String,
        @Query("nutrients[P]") p_nutrients: String,
        @Query("nutrients[VITA_RAE]") vita_rae_nutrients: String,
        @Query("nutrients[VITC]") vitc_nutrients: String,
        @Query("nutrients[THIA]") thia_nutrients: String,
        @Query("nutrients[RIBF]") ribf_nutrients: String,
        @Query("nutrients[NIA]") nia_nutrients: String,
        @Query("nutrients[VITB6A]") vitb6a_nutrients: String,
        @Query("nutrients[FOLDFE]") foldfe_nutrients: String,
        @Query("nutrients[VITB12]") vitb12_nutrients: String,
        @Query("nutrients[VITD]") vitd_nutrients: String,
        @Query("nutrients[TOCPHA]") tocpha_nutrients: String,
        @Query("nutrients[VITK1]") vitkq_nutrients: String
    ): Response<RecipeResponseDataModel>

}