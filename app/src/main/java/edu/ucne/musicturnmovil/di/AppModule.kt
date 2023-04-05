package edu.ucne.parcial2_albert.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.musicturnmovil.data.remote.api_dao.CancionApi
import edu.ucne.musicturnmovil.data.remote.api_dao.ColaCancionApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesColaCancionApi(moshi: Moshi): ColaCancionApi {
        return Retrofit.Builder()
            .baseUrl("http://albert0462.somee.com/api/")//("http://10.0.2.2:5048/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ColaCancionApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCancionApi(moshi: Moshi): CancionApi {
        return Retrofit.Builder()
            .baseUrl("http://albert0462.somee.com/api/")//("http://10.0.2.2:5048/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CancionApi::class.java)
    }
}