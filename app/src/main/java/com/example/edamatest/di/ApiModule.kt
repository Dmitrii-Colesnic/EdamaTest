package com.example.edamatest.di

import com.example.data.network.EdamamService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(EdamamService::class.java) }
}