package hu.aut.bme.treasurehuntingfrontend.network

import hu.aut.bme.treasurehuntingfrontend.domain.AcceptUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.AnswerUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.Suggestion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserQuestApiInteractor: Interactor() {
    private val api:UserQuestApiService
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Interactor.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api=retrofit.create(UserQuestApiService::class.java)
    }

    fun accept(
        acq: AcceptUserQuestDto,
        onSuccess: (Void, Int)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThreadWithStatusCode(api.accept("Basic ${Interactor.TOKEN}", acq),  onSuccess,  onError)
    }

    fun abandon(
        userQuestId: Long,
        onSuccess: (Void, Int)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThreadWithStatusCode(api.abandon("Basic ${Interactor.TOKEN}", userQuestId),  onSuccess,  onError)
    }

    fun finish(
        questId: Long,
        answer: AnswerUserQuestDto,
        onSuccess: (String, Int)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThreadWithStatusCode(api.finish("Basic ${Interactor.TOKEN}", questId, answer),  onSuccess,  onError)
    }

    fun getState(
        questId: Long,
        onSuccess: (Int, Int)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThreadWithStatusCode(api.getState("Basic ${Interactor.TOKEN}", questId),  onSuccess,  onError)
    }
}