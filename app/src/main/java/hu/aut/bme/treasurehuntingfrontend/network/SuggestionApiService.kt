package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Quest
import hu.aut.bme.treasurehuntingfrontend.domain.Suggestion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SuggestionApiService {
    @POST("/suggestion")
    fun postSuggestion(@Header("Authorization") token: String, @Body suggestion: Suggestion): Call<Void>
}