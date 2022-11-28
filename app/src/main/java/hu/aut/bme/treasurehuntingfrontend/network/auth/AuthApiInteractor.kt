package hu.aut.bme.treasurehuntingfrontend.network.auth

import hu.aut.bme.treasurehuntingfrontend.domain.User
import hu.aut.bme.treasurehuntingfrontend.network.Interactor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiInteractor: Interactor() {
    private val api: AuthApiService
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api=retrofit.create(AuthApiService::class.java)
    }

    fun login(
        token: String,
        onSuccess: (User)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThread(api.postLogin("Basic $token"),  onSuccess,  onError)
    }
    fun register(
        user: User,
        onSuccess: (User)->Unit,
        onError: (Throwable)-> Unit)
    {
        runCallOnBackgroundThread(api.postRegister(user),  onSuccess,  onError)
    }

    fun getUser(
        onSuccess: (User)->Unit,
        onError: (Throwable)-> Unit
    )
    {
        runCallOnBackgroundThread(api.getUser("Basic $TOKEN"),  onSuccess,  onError)
    }

    fun modifyProfile(
        user: User,
        onSuccess: (Void, Int)->Unit,
        onError: (Throwable)-> Unit
    ){
        runCallOnBackgroundThreadWithStatusCode(api.modifyProfile("Basic $TOKEN", user),  onSuccess,  onError)
    }
}