package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.AcceptUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.AnswerUserQuestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserQuestApiService {
    @POST("/accept")
    fun accept(@Header("Authorization") token: String, @Body acq: AcceptUserQuestDto): Call<Void>
    @POST("/abandon/{userQuestId}")
    fun abandon(@Header("Authorization") token: String, @Path("userQuestId") id: Long): Call<Void>
    @POST("/finish/{questId}")
    fun finish(@Header("Authorization") token: String, @Path("questId") id :Long, answerUserQuestDto: AnswerUserQuestDto): Call<String>
    @GET("/state/{questId}")
    fun getState(@Header("Authorization") token: String, @Path("questId") id :Long): Call<Int>
}