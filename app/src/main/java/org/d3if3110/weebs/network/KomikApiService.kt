package org.d3if3110.weebs.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3110.weebs.model.Komik
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "TaufiqAshariRamadhan/Assessment1MobPro/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface KomikApiService {
    @GET("static-api.json")
    suspend fun getKomik(): List<Komik>
    }
object KomikApi {
    val service: KomikApiService by lazy {
        retrofit.create(KomikApiService::class.java)
    }

    fun getHewanUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }