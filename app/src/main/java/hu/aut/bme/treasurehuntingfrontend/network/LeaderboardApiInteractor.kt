package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Score
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeaderboardApiInteractor: Interactor(){
    private val api: LeaderboardApiService
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api=retrofit.create(LeaderboardApiService::class.java)
    }

    fun getScores(
        onSuccess: (List<Score>)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThread(api.getScores(),  onSuccess,  onError)
    }
}