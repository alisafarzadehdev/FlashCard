package ir.safarzadehali.myapp.retro

import com.google.gson.GsonBuilder
import ir.safarzadehali.myapp.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //private const val BASE_URL = "https://androidhelp.ir/crudword/"
    private const val BASE_URL = "http://10.139.48.177/flash/card/api/"


    val gson = GsonBuilder()
        .setLenient()
        .create()


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}
