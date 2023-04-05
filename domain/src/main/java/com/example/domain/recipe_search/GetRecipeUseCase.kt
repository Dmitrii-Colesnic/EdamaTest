package com.example.domain.recipe_search

import com.example.domain.recipe_search.models.RecipeResponseDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val APPLICATION_ID = "0bcdf660"
private const val APPLICATION_KEYS = "618dae8b1a1226242ff8db8677dd5d50"

class GetRecipeUseCase(private val repo: RecipeSearchRepo) {
    fun execute(
        keyWord: String,
        calories: String = "",
        diet: List<String> = emptyList(),
        health: List<String> = emptyList(),
        cuisineType: List<String> = emptyList(),
        nutrients_fat: String = "",
        fasat_nutrients: String = "",
        fatrn_nutrients: String = "",
        fams_nutrients: String = "",
        fapu_nutrients: String = "",
        chocdf_nutrients: String = "",
        fibtg_nutrients: String = "",
        sugar_nutrients: String = "",
        procnt_nutrients: String = "",
        chole_nutrients: String = "",
        na_nutrients: String = "",
        ca_nutrients: String = "",
        mg_nutrients: String = "",
        k_nutrients: String = "",
        fe_nutrients: String = "",
        p_nutrients: String = "",
        vita_rae_nutrients: String = "",
        vitc_nutrients: String = "",
        thia_nutrients: String = "",
        ribf_nutrients: String = "",
        nia_nutrients: String = "",
        vitb6a_nutrients: String = "",
        foldfe_nutrients: String = "",
        vitb12_nutrients: String = "",
        vitd_nutrients: String = "",
        tocpha_nutrients: String = "",
        vitkq_nutrients: String = ""
    ): Flow<RecipeResponseDomainModel?> {
        return flow {
            repo.getRecipe(
                appId = APPLICATION_ID,
                appKey = APPLICATION_KEYS,
                keyWord = keyWord,
                calories = calories,
                diet = diet,
                health = health,
                cuisineType = cuisineType,
                nutrients_fat = nutrients_fat,
                fasat_nutrients = fasat_nutrients,
                fatrn_nutrients = fatrn_nutrients,
                fams_nutrients = fams_nutrients,
                fapu_nutrients = fapu_nutrients,
                chocdf_nutrients = chocdf_nutrients,
                fibtg_nutrients = fibtg_nutrients,
                sugar_nutrients = sugar_nutrients,
                procnt_nutrients = procnt_nutrients,
                chole_nutrients = chole_nutrients,
                na_nutrients = na_nutrients,
                ca_nutrients = ca_nutrients,
                mg_nutrients = mg_nutrients,
                k_nutrients = k_nutrients,
                fe_nutrients = fe_nutrients,
                p_nutrients = p_nutrients,
                vita_rae_nutrients = vita_rae_nutrients,
                vitc_nutrients = vitc_nutrients,
                thia_nutrients = thia_nutrients,
                ribf_nutrients = ribf_nutrients,
                nia_nutrients = nia_nutrients,
                vitb6a_nutrients = vitb6a_nutrients,
                foldfe_nutrients = foldfe_nutrients,
                vitb12_nutrients = vitb12_nutrients,
                vitd_nutrients = vitd_nutrients,
                tocpha_nutrients = tocpha_nutrients,
                vitkq_nutrients = vitkq_nutrients
            )
        }
    }

}