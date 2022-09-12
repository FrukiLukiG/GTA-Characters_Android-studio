package hr.tvz.android.zavrsniprojekt.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        fun getApi() : SpringBootApi{
            return Retrofit.Builder()
                .baseUrl("http://192.168.186.1:8080")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(SpringBootApi::class.java)
        }
    }
}