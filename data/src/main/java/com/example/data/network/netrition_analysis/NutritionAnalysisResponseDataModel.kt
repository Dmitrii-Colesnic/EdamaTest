package com.example.data.network.netrition_analysis

import com.google.gson.annotations.SerializedName

data class NutritionAnalysisResponseDataModel(
    val totalNutrients: TotalNutrients,
    val totalDaily: TotalDaily,
    val ingredients: List<Ingredient>
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
    @SerializedName("TOCPHA") val TOCPHA: Nutrient,
    @SerializedName("VITK1") val VITK1: Nutrient,
    @SerializedName("WATER") val WATER: Nutrient,
)

data class TotalDaily(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: Nutrient,
    @SerializedName("FAT") val FAT: Nutrient,
    @SerializedName("FASAT") val FASAT: Nutrient,
    @SerializedName("CHOCDF") val CHOCDF: Nutrient,
    @SerializedName("FIBTG") val FIBTG: Nutrient,
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
    @SerializedName("VITB12") val VITB12: Nutrient,
    @SerializedName("VITD") val VITD: Nutrient,
    @SerializedName("TOCPHA") val TOCPHA: Nutrient,
    @SerializedName("VITK1") val VITK1: Nutrient,
)

data class Ingredient(
    val text: String,
    val parsed: List<Parsed>
)

data class Parsed(
    val quantity: Long,
    val measure: String,
    val foodMatch: String,
    val food: String,
    val weight: Int,
    val retainedWeight: Int,
    val nutrients: IngredientNutrients,
    val measureURI: String,
    val status: String,
)

data class IngredientNutrients (
    @SerializedName("RIBF") val RIBF: Nutrient,
    @SerializedName("VITD") val VITD: Nutrient,
    @SerializedName("THIA") val THIA: Nutrient,
    @SerializedName("FAPU") val FAPU: Nutrient,
    @SerializedName("NIA") val NIA: Nutrient,
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: Nutrient,
    @SerializedName("FASAT") val FASAT: Nutrient,
    @SerializedName("VITC") val VITC: Nutrient,
    @SerializedName("PROCNT") val PROCNT: Nutrient,
    @SerializedName("CHOLE") val CHOLE: Nutrient,
    @SerializedName("FAMS") val FAMS: Nutrient,
    @SerializedName("CHOCDF") val CHOCDF: Nutrient,
    @SerializedName("FAT") val FAT: Nutrient,
    @SerializedName("VITB6A") val VITB6A: Nutrient,
    @SerializedName("VITB12") val VITB12: Nutrient,
    @SerializedName("WATER") val WATER: Nutrient,
    @SerializedName("K") val K: Nutrient,
    @SerializedName("P") val P: Nutrient,
    @SerializedName("NA") val NA: Nutrient,
    @SerializedName("ZN") val ZN: Nutrient,
    @SerializedName("CA") val CA: Nutrient,
    @SerializedName("MG") val MG: Nutrient,
    @SerializedName("FE") val FE: Nutrient,
    @SerializedName("FOLFD") val FOLFD: Nutrient,
    @SerializedName("FOLAC") val FOLAC: Nutrient,
    @SerializedName("FOLDFE") val FOLDFE: Nutrient

        )

data class TotalNutrientsKCal(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: Nutrient,
    @SerializedName("PROCNT_KCAL") val PROCNT_KCAL: Nutrient,
    @SerializedName("FAT_KCAL") val FAT_KCAL: Nutrient,
    @SerializedName("CHOCDF_KCAL") val CHOCDF_KCAL: Nutrient,
)

data class Nutrient(
    val label: String,
    val quantity: Double,
    val unit: String,
)
