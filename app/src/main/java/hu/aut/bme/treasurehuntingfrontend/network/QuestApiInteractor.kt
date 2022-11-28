package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.Quest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestApiInteractor: Interactor() {
    private val api:QuestApiService
    init{
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api=retrofit.create(QuestApiService::class.java)
    }

    fun getQuests(
            onSuccess: (List<Quest>)->Unit,
            onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThread(api.getQuests("Basic $TOKEN"),  onSuccess,  onError)
    }

    fun getQuest(
        id: Long,
        onSuccess: (Quest) -> Unit,
        onError: (Throwable) -> Unit
    )
    {
        runCallOnBackgroundThread(api.getQuest("Basic $TOKEN", id), onSuccess, onError)
    }
}