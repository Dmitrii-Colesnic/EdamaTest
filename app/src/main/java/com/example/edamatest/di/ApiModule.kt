package com.example.edamatest.di

import com.example.data.network.recipe_search.RecipeApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single(createdAtStart = false) {
        get<Retrofit>().create(RecipeApiService::class.java)
    }

}