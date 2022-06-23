package com.techgv.themovielibrary.di

import com.techgv.themovielibrary.BuildConfig
import com.techgv.themovielibrary.data.remote.MoviesDetailTmDbApi
import com.techgv.themovielibrary.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    private const val baseUrl = BuildConfig.BASE_URL

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
        val httpUrl = chain.request().url
        val newUrl = httpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        request.url(newUrl)
        chain.proceed(request.build())
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): MoviesDetailTmDbApi {
        return retrofit.create(MoviesDetailTmDbApi::class.java)

    }

    @Provides
    @Singleton
    fun provideRepository(retrofit: MoviesDetailTmDbApi): Repository = Repository(retrofit)


}