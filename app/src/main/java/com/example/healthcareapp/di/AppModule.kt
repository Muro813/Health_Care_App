package com.example.healthcareapp.di

import com.example.healthcareapp.BuildConfig
import com.example.healthcareapp.core.utils.BigDecimalAdapter
import com.example.healthcareapp.data.remote.TokenRefreshAuthenticator
import com.example.healthcareapp.data.remote.AuthorizationInterceptor
import com.example.healthcareapp.data.remote.dto.ApiErrorDto
import com.example.healthcareapp.data.remote.services.ApiService
import com.example.healthcareapp.data.repository.HealthCareRepositoryImpl
import com.example.healthcareapp.domain.repository.HealthCareRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: AuthorizationInterceptor,
        authenticator: TokenRefreshAuthenticator
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(interceptor)
            .authenticator(authenticator)

        if (BuildConfig.DEBUG)
            okHttpClient.addInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            )

        return okHttpClient.build()
    }

    val moshi: Moshi = Moshi.Builder()
        .add(BigDecimalAdapter)
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideHealthCareRepository(
        api: ApiService,
        errorAdapter: JsonAdapter<ApiErrorDto>
    ): HealthCareRepository = HealthCareRepositoryImpl(api,errorAdapter)

    @Singleton
    @Provides
    fun provideErrorAdapter(): JsonAdapter<ApiErrorDto> {
        return Moshi.Builder()
            .build()
            .adapter(ApiErrorDto::class.java).lenient()
    }

}