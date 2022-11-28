package hu.aut.bme.treasurehuntingfrontend.network.auth

import hu.aut.bme.treasurehuntingfrontend.domain.User
import retrofit2.Call
import retrofit2.http.*

interface AuthApiService {
    @POST("/login")
    fun postLogin(@Header("Authorization") credentials: String): Call<User>

    @POST("/register")
    fun postRegister(@Body user: User): Call<User>

    @GET("/user")
    fun getUser(@Header("Authorization") token: String): Call<User>

    @PUT("/modify")
    fun modifyProfile(@Header("Authorization") token: String, @Body user: User): Call<Void>
}