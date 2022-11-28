package hu.aut.bme.treasurehuntingfrontend.network

import android.os.Handler
import retrofit2.Call



open class Interactor{
    companion object {
        var TOKEN: String = ""
        const val BASEURL: String = "http://192.168.1.70:5000/"
    }
    protected fun <T> runCallOnBackgroundThread(
            call: Call<T>,
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        val handler = Handler()
        Thread {
            try {
                val response = call.execute().body()!!
                handler.post { onSuccess(response) }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }.start()
    }

    protected fun <T> runCallOnBackgroundThreadWithStatusCode(
        call: Call<T>,
        onSuccess: (T, Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val handler = Handler()
        Thread {
            try {
                val response = call.execute()
                val body = response.body()!!
                handler.post { onSuccess(body, response.code()) }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }.start()
    }
}