package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.AcceptUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.AnswerUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.FinishUserQuestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserQuestApiService {
    @POST("userquest/accept/")
    fun accept(@Header("Authorization") token: String, @Body acceptUserQuestDto: AcceptUserQuestDto): Call<Void>
    @POST("userquest/abandon/{questId}")
    fun abandon(@Header("Authorization") token: String, @Path("questId") id: Long): Call<Void>
    @POST("userquest/finish/{questId}")
    fun finish(@Header("Authorization") token: String, @Path("questId") id :Long, @Body answerUserQuestDto: AnswerUserQuestDto): Call<FinishUserQuestDto>
    @GET("userquest/state/{questId}")
    fun getState(@Header("Authorization") token: String, @Path("questId") id :Long): Call<Int>
}