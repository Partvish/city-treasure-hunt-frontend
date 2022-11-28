package hu.aut.bme.treasurehuntingfrontend.ui.messaging

import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogCreator(private val context: Context) {
    fun createDialog(title: String, message: String, context: Context = this.context){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        builder.setMessage(message)
            .setTitle(title)
            .create()
            .show()
    }
}