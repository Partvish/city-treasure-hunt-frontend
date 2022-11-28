package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Score
import retrofit2.Call
import retrofit2.http.GET

interface LeaderboardApiService {
    @GET("/scores")
    fun getScores(): Call<List<Score>>
}