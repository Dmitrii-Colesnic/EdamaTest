package com.example.edamatest

import android.app.Application
import com.example.edamatest.di.RetrofitModule
import com.example.edamatest.di.apiModule
import com.example.edamatest.di.nutrition_analysis.appNutritionAnalysisModule
import com.example.edamatest.di.nutrition_analysis.dataNutritionAnalysisModule
import com.example.edamatest.di.nutrition_analysis.domainNutritionAnalysisModule
import com.example.edamatest.di.recipe_search.appRecipeSearchModule
import com.example.edamatest.di.recipe_search.dataRecipeSearchModule
import com.example.edamatest.di.recipe_search.domainRecipeSearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    apiModule, RetrofitModule,
                    appRecipeSearchModule, dataRecipeSearchModule, domainRecipeSearchModule,
                    appNutritionAnalysisModule, dataNutritionAnalysisModule, domainNutritionAnalysisModule,
                )
            )
        }
    }

}