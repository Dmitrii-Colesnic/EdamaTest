package com.example.data.network.nutrition_analysis.model

import com.google.gson.annotations.SerializedName

data class NutritionAnalysisResponseDataModel(
    val totalNutrients: TotalNutrientsData,
    val totalDaily: TotalDailyData,
    val ingredients: List<IngredientData>,
    val totalNutrientsKCal: TotalNutrientsKCalData
)

data class TotalNutrientsData(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: NutrientData,
    @SerializedName("FAT") val FAT: NutrientData,
    @SerializedName("FASAT") val FASAT: NutrientData,
    @SerializedName("FATRN") val FATRN: NutrientData,
    @SerializedName("FAMS") val FAMS: NutrientData,
    @SerializedName("FAPU") val FAPU: NutrientData,
    @SerializedName("CHOCDF") val CHOCDF: NutrientData,
    @SerializedName("CHOCDF.net") val CHOCDFDotNet: NutrientData,
    @SerializedName("FIBTG") val FIBTG: NutrientData,
    @SerializedName("SUGAR") val SUGAR: NutrientData,
    @SerializedName("PROCNT") val PROCNT: NutrientData,
    @SerializedName("CHOLE") val CHOLE: NutrientData,
    @SerializedName("NA") val NA: NutrientData,
    @SerializedName("CA") val CA: NutrientData,
    @SerializedName("MG") val MG: NutrientData,
    @SerializedName("K") val K: NutrientData,
    @SerializedName("FE") val FE: NutrientData,
    @SerializedName("ZN") val ZN: NutrientData,
    @SerializedName("P") val P: NutrientData,
    @SerializedName("VITA_RAE") val VITA_RAE: NutrientData,
    @SerializedName("VITC") val VITC: NutrientData,
    @SerializedName("THIA") val THIA: NutrientData,
    @SerializedName("RIBF") val RIBF: NutrientData,
    @SerializedName("NIA") val NIA: NutrientData,
    @SerializedName("VITB6A") val VITB6A: NutrientData,
    @SerializedName("FOLDFE") val FOLDFE: NutrientData,
    @SerializedName("FOLFD") val FOLFD: NutrientData,
    @SerializedName("FOLAC") val FOLAC: NutrientData,
    @SerializedName("VITB12") val VITB12: NutrientData,
    @SerializedName("VITD") val VITD: NutrientData,
    @SerializedName("TOCPHA") val TOCPHA: NutrientData,
    @SerializedName("VITK1") val VITK1: NutrientData,
    @SerializedName("WATER") val WATER: NutrientData,
)

data class TotalDailyData(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: NutrientData,
    @SerializedName("FAT") val FAT: NutrientData,
    @SerializedName("FASAT") val FASAT: NutrientData,
    @SerializedName("CHOCDF") val CHOCDF: NutrientData,
    @SerializedName("FIBTG") val FIBTG: NutrientData,
    @SerializedName("PROCNT") val PROCNT: NutrientData,
    @SerializedName("CHOLE") val CHOLE: NutrientData,
    @SerializedName("NA") val NA: NutrientData,
    @SerializedName("CA") val CA: NutrientData,
    @SerializedName("MG") val MG: NutrientData,
    @SerializedName("K") val K: NutrientData,
    @SerializedName("FE") val FE: NutrientData,
    @SerializedName("ZN") val ZN: NutrientData,
    @SerializedName("P") val P: NutrientData,
    @SerializedName("VITA_RAE") val VITA_RAE: NutrientData,
    @SerializedName("VITC") val VITC: NutrientData,
    @SerializedName("THIA") val THIA: NutrientData,
    @SerializedName("RIBF") val RIBF: NutrientData,
    @SerializedName("NIA") val NIA: NutrientData,
    @SerializedName("VITB6A") val VITB6A: NutrientData,
    @SerializedName("FOLDFE") val FOLDFE: NutrientData,
    @SerializedName("VITB12") val VITB12: NutrientData,
    @SerializedName("VITD") val VITD: NutrientData,
    @SerializedName("TOCPHA") val TOCPHA: NutrientData,
    @SerializedName("VITK1") val VITK1: NutrientData,
)

data class IngredientData(
    val text: String,
    val parsed: List<ParsedData>
)

data class ParsedData(
    val quantity: Long,
    val measure: String,
    val foodMatch: String,
    val food: String,
    val foodId: String,
    val weight: Double,
    val retainedWeight: Double,
    val nutrients: IngredientNutrientsData,
    val measureURI: String,
    val status: String,
)

data class IngredientNutrientsData (
    @SerializedName("RIBF") val RIBF: NutrientData,
    @SerializedName("VITD") val VITD: NutrientData,
    @SerializedName("THIA") val THIA: NutrientData,
    @SerializedName("FAPU") val FAPU: NutrientData,
    @SerializedName("NIA") val NIA: NutrientData,
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: NutrientData,
    @SerializedName("FASAT") val FASAT: NutrientData,
    @SerializedName("VITC") val VITC: NutrientData,
    @SerializedName("PROCNT") val PROCNT: NutrientData,
    @SerializedName("CHOLE") val CHOLE: NutrientData,
    @SerializedName("FAMS") val FAMS: NutrientData,
    @SerializedName("CHOCDF") val CHOCDF: NutrientData,
    @SerializedName("FAT") val FAT: NutrientData,
    @SerializedName("VITB6A") val VITB6A: NutrientData,
    @SerializedName("VITB12") val VITB12: NutrientData,
    @SerializedName("WATER") val WATER: NutrientData,
    @SerializedName("K") val K: NutrientData,
    @SerializedName("P") val P: NutrientData,
    @SerializedName("NA") val NA: NutrientData,
    @SerializedName("ZN") val ZN: NutrientData,
    @SerializedName("CA") val CA: NutrientData,
    @SerializedName("MG") val MG: NutrientData,
    @SerializedName("FE") val FE: NutrientData,
    @SerializedName("FOLFD") val FOLFD: NutrientData,
    @SerializedName("FOLAC") val FOLAC: NutrientData,
    @SerializedName("FOLDFE") val FOLDFE: NutrientData
    )

data class TotalNutrientsKCalData(
    @SerializedName("ENERC_KCAL") val ENERC_KCAL: TotalNutrientData,
    @SerializedName("PROCNT_KCAL") val PROCNT_KCAL: TotalNutrientData,
    @SerializedName("FAT_KCAL") val FAT_KCAL: TotalNutrientData,
    @SerializedName("CHOCDF_KCAL") val CHOCDF_KCAL: TotalNutrientData,
)

data class NutrientData(
    val label: String,
    val quantity: Double,
    val unit: String,
)

data class TotalNutrientData(
    val label: String,
    val quantity: Long,
    val unit: String,
)
