package hr.tvz.android.zavrsniprojekt.api

import hr.tvz.android.zavrsniprojekt.login.LoginUser
import hr.tvz.android.zavrsniprojekt.register.RegisterUser
import hr.tvz.android.zavrsniprojekt.models.Character
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface SpringBootApi {

    @GET("/gta")
    fun getAllCharacters(): Call<List<Character>>

    @GET("/login")
    fun getAllUsers(): Call<List<LoginUser>>

    @POST("/login")
    fun registerUser(@Body loginUser: RegisterUser): Call<RegisterUser>
}