package com.example.domain.nutrition_analysis.model

data class NutritionAnalysisResponseDomainModel(
    val totalNutrients: TotalNutrientsDomain,
    val totalDaily: TotalDailyDomain,
    val ingredients: List<IngredientDomain>,
    val nutrientsKCal: TotalNutrientsKCalDomain
)

data class TotalNutrientsDomain(
    //Calories
    val ENERC_KCAL: NutrientDomain,

    //Total Fat
    val FAT: NutrientDomain,
    //Saturated Fat
    val FASAT: NutrientDomain,
    //Trans Fat
    val FATRN: NutrientDomain,

    //Cholesterol
    val CHOLE: NutrientDomain,

    //Sodium
    val NA: NutrientDomain,

    //Total Carbohydrate
    val CHOCDF: NutrientDomain,
    //Dietary Fiber
    val FIBTG: NutrientDomain,
    //Total Sugars
    val SUGAR: NutrientDomain,

    //Protein
    val PROCNT: NutrientDomain,

    //Vitamin D
    val VITD: NutrientDomain,
    //Calcium
    val CA: NutrientDomain,
    //Iron
    val FE: NutrientDomain,
    //Potassium
    val K: NutrientDomain,
)

data class TotalDailyDomain(
    //Total Fat
    val FAT: NutrientDomain,
    //Saturated Fat
    val FASAT: NutrientDomain,

    //Cholesterol
    val CHOLE: NutrientDomain,

    //Sodium
    val NA: NutrientDomain,

    //Total Carbohydrate
    val CHOCDF: NutrientDomain,
    //Dietary Fiber
    val FIBTG: NutrientDomain,

    //Protein
    val PROCNT: NutrientDomain,

    //Vitamin D
    val VITD: NutrientDomain,
    //Calcium
    val CA: NutrientDomain,
    //Iron
    val FE: NutrientDomain,
    //Potassium
    val K: NutrientDomain,
)

data class IngredientDomain(
    val text: String,
    val parsed: List<ParsedDomain>
)

data class ParsedDomain(
    val quantity: Long,
    val measure: String,
    val food: String,
    val foodId: String,
    val weight: Int,
    val nutrients: IngredientNutrientsDomain,
)

data class IngredientNutrientsDomain(
    val ENERC_KCAL: NutrientDomain,
    val FAT: NutrientDomain,
    val PROCNT: NutrientDomain,
    val CHOCDF: NutrientDomain,
)

data class TotalNutrientsKCalDomain(
    val ENERC_KCAL: NutrientDomain,
)

data class NutrientDomain(
    val label: String,
    val quantity: Int,
    val unit: String,
)
