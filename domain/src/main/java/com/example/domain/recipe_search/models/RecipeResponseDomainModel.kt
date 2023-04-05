package com.example.domain.recipe_search.models

data class RecipeResponseDomainModel(
    val from: Long,
    val to: Long,
    val count: Long,
    val _links: Links,
    val hits: Hits,
)

data class Links(
    val self: SubLink,
    val next: SubLink,
)

data class SubLink(
    val href: String,
    val title: String,
)

data class Hits(
    val recipe: Recipe,
//    val _links: Links,
)

data class Recipe(
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val yield: Float,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val cautions: List<String>,
    val ingredientLines: List<String>,
    val cuisineType: List<String>,
    val mealType: List<String>,
    val dishType: List<String>,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
    val totalNutrients: TotalNutrients,
)

data class TotalNutrients(
//    @SerializedName("ENERC_KCAL") val ENERC_KCAL: Nutrient,
    val FAT: Nutrient,
//    @SerializedName("FASAT") val FASAT: Nutrient,
//    @SerializedName("FATRN") val FATRN: Nutrient,
//    @SerializedName("FAMS") val FAMS: Nutrient,
//    @SerializedName("FAPU") val FAPU: Nutrient,
//    @SerializedName("CHOCDF") val CHOCDF: Nutrient,
//    @SerializedName("CHOCDF.net") val CHOCDFDotNet: Nutrient,
//    @SerializedName("FIBTG") val FIBTG: Nutrient,
    val SUGAR: Nutrient,
//    @SerializedName("SUGAR.added") val SUGARDotadded: Nutrient,
    val PROCNT: Nutrient,
    val CHOLE: Nutrient,
    val NA: Nutrient,
    val CA: Nutrient,
    val MG: Nutrient,
    val K: Nutrient,
    val FE: Nutrient,
    val ZN: Nutrient,
//    @SerializedName("P") val P: Nutrient,
//    @SerializedName("VITA_RAE") val VITA_RAE: Nutrient,
//    @SerializedName("VITC") val VITC: Nutrient,
//    @SerializedName("THIA") val THIA: Nutrient,
//    @SerializedName("RIBF") val RIBF: Nutrient,
//    @SerializedName("NIA") val NIA: Nutrient,
//    @SerializedName("VITB6A") val VITB6A: Nutrient,
//    @SerializedName("FOLDFE") val FOLDFE: Nutrient,
//    @SerializedName("FOLFD") val FOLFD: Nutrient,
//    @SerializedName("FOLAC") val FOLAC: Nutrient,
//    @SerializedName("VITB12") val VITB12: Nutrient,
//    @SerializedName("VITD") val VITD: Nutrient,
//    @SerializedName("VITD") val VITE: Nutrient,
//    @SerializedName("VITK1") val VITK1: Nutrient,
//    @SerializedName("Sugar.alcohol") val SugarDatAlcohol: Nutrient,
//    @SerializedName("WATER") val WATER: Nutrient,
)

data class Nutrient(
    val label: String,
    val quantity: Double,
    val unit: Double,
)
