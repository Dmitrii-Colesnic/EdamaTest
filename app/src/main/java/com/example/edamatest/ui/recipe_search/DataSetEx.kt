package com.example.edamatest.ui.recipe_search

fun macronutrientsList(): List<NutrientsModel> {
    return listOf(
        NutrientsModel(name = "Fat",                serverName = "nutrients[FAT]"),
        NutrientsModel(name = "Saturated",          serverName = "nutrients[FASAT]"),
        NutrientsModel(name = "Trans",              serverName = "nutrients[FATRN]"),
        NutrientsModel(name = "Monounsaturated",    serverName = "nutrients[FAMS]"),
        NutrientsModel(name = "Polyunsaturated",    serverName = "nutrients[FAPU]"),
        NutrientsModel(name = "Carbs",              serverName = "nutrients[CHOCDF]"),
        NutrientsModel(name = "Fiber",              serverName = "nutrients[FIBTG]"),
        NutrientsModel(name = "Sugar",              serverName = "nutrients[SUGAR]"),
        NutrientsModel(name = "Protein",            serverName = "nutrients[PROCNT]"),
    )
}

fun micronutrientsList(): List<NutrientsModel> {
    return listOf(
        NutrientsModel(name = "Cholesterol",            serverName = "nutrients[CHOLE]"),
        NutrientsModel(name = "Sodium",                 serverName = "nutrients[NA]"),
        NutrientsModel(name = "Calcium",                serverName = "nutrients[CA]"),
        NutrientsModel(name = "Magnesium",              serverName = "nutrients[MG]"),
        NutrientsModel(name = "Potassium",              serverName = "nutrients[K]"),
        NutrientsModel(name = "Iron",                   serverName = "nutrients[FE]"),
        NutrientsModel(name = "Phosphorus",             serverName = "nutrients[P]"),
        NutrientsModel(name = "Vitamin A",              serverName = "nutrients[VITA_RAE]"),
        NutrientsModel(name = "Vitamin C",              serverName = "nutrients[VITC]"),
        NutrientsModel(name = "Thiamin (B1)",           serverName = "nutrients[THIA]"),
        NutrientsModel(name = "Riboflavin (B2)",        serverName = "nutrients[RIBF]"),
        NutrientsModel(name = "Niacin (B3)",            serverName = "nutrients[NIA]"),
        NutrientsModel(name = "Vitamin B6",             serverName = "nutrients[VITB6A]"),
        NutrientsModel(name = "Folate (Equivalent)",    serverName = "nutrients[FOLDFE]"),
        NutrientsModel(name = "Vitamin B12",            serverName = "nutrients[VITB12]"),
        NutrientsModel(name = "Vitamin D",              serverName = "nutrients[VITD]"),
        NutrientsModel(name = "Vitamin E",              serverName = "nutrients[TOCPHA]"),
        NutrientsModel(name = "Vitamin K",              serverName = "nutrients[VITK1]"),
    )
}

fun dietList(): List<DietModel> {
    return listOf(
        DietModel(name = "Balanced"),
        DietModel(name = "High-Fiber"),
        DietModel(name = "High-Protein"),
        DietModel(name = "Low-Carb"),
        DietModel(name = "Low-Fat"),
        DietModel(name = "Low-Sodium"),
)
}

fun healthList(): List<DietModel> {
    return listOf(
        DietModel(name = "Alcohol-free"),
        DietModel(name = "Keto "),
        DietModel(name = "Kidney-friendly"),
        DietModel(name = "Kosher"),
        DietModel(name = "Low-potassium"),
        DietModel(name = "No-oil-added"),
        DietModel(name = "No-sugar"),
        DietModel(name = "Paleo"),
        DietModel(name = "Pescatarian"),
        DietModel(name = "Pork-free"),
        DietModel(name = "Red meat-free"),
        DietModel(name = "Sugar-conscious"),
        DietModel(name = "Vegan"),
        DietModel(name = "Vegetarian"),
        DietModel(name = "Celery-free"),
        DietModel(name = "Crustacean-free"),
        DietModel(name = "Dairy-free"),
        DietModel(name = "Egg-free"),
        DietModel(name = "Fish-free"),
        DietModel(name = "Gluten-free"),
        DietModel(name = "Lupine-free"),
        DietModel(name = "Mustard-free"),
        DietModel(name = "Peanut-free"),
        DietModel(name = "Sesame-free"),
        DietModel(name = "Shellfish-free"),
        DietModel(name = "Soy-free"),
        DietModel(name = "Tree-Nut-free"),
        DietModel(name = "Wheat-free"),
)
}