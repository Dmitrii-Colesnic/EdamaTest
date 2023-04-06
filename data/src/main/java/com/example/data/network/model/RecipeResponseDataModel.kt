package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class RecipeResponseDataModel(
    @SerializedName("from") val from: Long,
    @SerializedName("to") val to: Long,
    @SerializedName("count") val count: Long,
    @SerializedName("_links") val _links: Links,
    @SerializedName("hits") val hits: Hits,
)

data class Links(
    @SerializedName("self") val self: SubLink,
    @SerializedName("next") val next: SubLink,
)

data class SubLink(
    @SerializedName("href") val href: String,
    @SerializedName("title") val title: String,
)

data class Hits(
    @SerializedName("recipe") val recipe: Recipe,
    @SerializedName("_links") val _links: Links,
)

data class Recipe(
    @SerializedName("uri") val uri: String,
    @SerializedName("label") val label: String,
    @SerializedName("image") val image: String,
    @SerializedName("source") val source: String,
    @SerializedName("url") val url: String,
    @SerializedName("yield") val yield: Float,
    @SerializedName("dietLabels") val dietLabels: List<String>,
    @SerializedName("healthLabels") val healthLabels: List<String>,
    @SerializedName("cautions") val cautions: List<String>,
    @SerializedName("ingredientLines") val ingredientLines: List<String>,
    @SerializedName("cuisineType") val cuisineType: List<String>,
    @SerializedName("mealType") val mealType: List<String>,
    @SerializedName("dishType") val dishType: List<String>,
    @SerializedName("calories") val calories: Double,
    @SerializedName("totalWeight") val totalWeight: Double,
    @SerializedName("totalTime") val totalTime: Double,
    @SerializedName("totalNutrients") val totalNutrients: TotalNutrients,
    )

data class TotalNutrients(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: Nutrient,
    @SerializedName("FAT") val FAT: Nutrient,
    @SerializedName("FASAT") val FASAT: Nutrient,
    @SerializedName("FATRN") val FATRN: Nutrient,
    @SerializedName("FAMS") val FAMS: Nutrient,
    @SerializedName("FAPU") val FAPU: Nutrient,
    @SerializedName("CHOCDF") val CHOCDF: Nutrient,
    @SerializedName("CHOCDF.net") val CHOCDFDotNet: Nutrient,
    @SerializedName("FIBTG") val FIBTG: Nutrient,
    @SerializedName("SUGAR") val SUGAR: Nutrient,
    @SerializedName("SUGAR.added") val SUGARDotadded: Nutrient,
    @SerializedName("PROCNT") val PROCNT: Nutrient,
    @SerializedName("CHOLE") val CHOLE: Nutrient,
    @SerializedName("NA") val NA: Nutrient,
    @SerializedName("CA") val CA: Nutrient,
    @SerializedName("MG") val MG: Nutrient,
    @SerializedName("K") val K: Nutrient,
    @SerializedName("FE") val FE: Nutrient,
    @SerializedName("ZN") val ZN: Nutrient,
    @SerializedName("P") val P: Nutrient,
    @SerializedName("VITA_RAE") val VITA_RAE: Nutrient,
    @SerializedName("VITC") val VITC: Nutrient,
    @SerializedName("THIA") val THIA: Nutrient,
    @SerializedName("RIBF") val RIBF: Nutrient,
    @SerializedName("NIA") val NIA: Nutrient,
    @SerializedName("VITB6A") val VITB6A: Nutrient,
    @SerializedName("FOLDFE") val FOLDFE: Nutrient,
    @SerializedName("FOLFD") val FOLFD: Nutrient,
    @SerializedName("FOLAC") val FOLAC: Nutrient,
    @SerializedName("VITB12") val VITB12: Nutrient,
    @SerializedName("VITD") val VITD: Nutrient,
    @SerializedName("VITD") val VITE: Nutrient,
    @SerializedName("VITK1") val VITK1: Nutrient,
    @SerializedName("Sugar.alcohol") val SugarDatAlcohol: Nutrient,
    @SerializedName("WATER") val WATER: Nutrient,
)

data class Nutrient(
    @SerializedName("label") val label: String,
    @SerializedName("quantity") val quantity: Double,
    @SerializedName("unit") val unit: Double,
)
