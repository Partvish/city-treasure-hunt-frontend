package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Suggestion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionApiInteractor: Interactor() {
    private val api:SuggestionApiService
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Interactor.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api=retrofit.create(SuggestionApiService::class.java)
    }

    fun postSuggestion(
        suggestion: Suggestion,
        onSuccess: (Int)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThreadWithStatusCode(api.postSuggestion("Basic ${Interactor.TOKEN}", suggestion),  onSuccess,  onError)
    }
}