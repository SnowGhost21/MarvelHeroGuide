package com.diegocunha.marvelheroguide.dependency

import com.diegocunha.marvelheroguide.BuildConfig
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import com.diegocunha.marvelheroguide.model.repository.retrofit.MarvelApi
import com.diegocunha.marvelheroguide.model.repository.retrofit.MarvelRetrofitRepository
import com.diegocunha.marvelheroguide.view.character.CharacterDetailViewModel
import com.diegocunha.marvelheroguide.view.home.HomeViewModel
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory {

        val logging = HttpLoggingInterceptor()
        logging.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
    }

    factory { GsonBuilder().create() }

    factory {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
    }

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(MarvelApi::class.java)
    }

    factory { CharacterDetailViewModel.Factory(get()) }

    single { MarvelRetrofitRepository(get()) as MarvelRepository }

    viewModel { HomeViewModel(get()) }




}