package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Quest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface QuestApiService {
    @GET("/quest/all")
    fun getQuests(@Header("Authorization") token: String): Call<List<Quest>>

    @GET("/quest/{Id}")
    fun getQuest(@Header("Authorization") token: String, @Path("Id") id: Long): Call<Quest>
}