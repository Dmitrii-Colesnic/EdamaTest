package com.example.edamatest.ui.nutrition_analysis.model

data class NutritionAnalysisModel(
    val totalNutrients: TotalNutrientsModel,
    val totalDaily: TotalDailyModel,
    val ENERC_KCAL: NutrientModel,
)

data class TotalNutrientsModel(
    //Calories
    val ENERC_KCAL: NutrientModel,

    //Total Fat
    val FAT: NutrientModel,
    //Saturated Fat
    val FASAT: NutrientModel,
    //Trans Fat
    val FATRN: NutrientModel,

    //Cholesterol
    val CHOLE: NutrientModel,

    //Sodium
    val NA: NutrientModel,

    //Total Carbohydrate
    val CHOCDF: NutrientModel,
    //Dietary Fiber
    val FIBTG: NutrientModel,
    //Total Sugars
    val SUGAR: NutrientModel,

    //Protein
    val PROCNT: NutrientModel,

    //Vitamin D
    val VITD: NutrientModel,
    //Calcium
    val CA: NutrientModel,
    //Iron
    val FE: NutrientModel,
    //Potassium
    val K: NutrientModel,
)

data class TotalDailyModel(
    //Total Fat
    val FAT: NutrientModel,
    //Saturated Fat
    val FASAT: NutrientModel,

    //Cholesterol
    val CHOLE: NutrientModel,

    //Sodium
    val NA: NutrientModel,

    //Total Carbohydrate
    val CHOCDF: NutrientModel,
    //Dietary Fiber
    val FIBTG: NutrientModel,

    //Protein
    val PROCNT: NutrientModel,

    //Vitamin D
    val VITD: NutrientModel,
    //Calcium
    val CA: NutrientModel,
    //Iron
    val FE: NutrientModel,
    //Potassium
    val K: NutrientModel,
)

data class IngredientModel(
    val text: String,
    val parsed: List<ParsedModel>
)

data class ParsedModel(
    val quantity: Long,
    val measure: String,
    val food: String,
    val weight: Int,
    val nutrients: IngredientNutrientsModel,
)

data class IngredientNutrientsModel(
    val ENERC_KCAL: NutrientModel,
    val FAT: NutrientModel,
    val PROCNT: NutrientModel,
    val CHOCDF: NutrientModel,
)

data class NutrientModel(
    val label: String,
    val quantity: Int,
    val unit: String,
)