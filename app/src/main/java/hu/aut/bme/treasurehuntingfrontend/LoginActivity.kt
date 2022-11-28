package hu.aut.bme.treasurehuntingfrontend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import hu.aut.bme.treasurehuntingfrontend.domain.User
import hu.aut.bme.treasurehuntingfrontend.network.auth.AuthApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator
import java.util.*


class LoginActivity : AppCompatActivity() {
    private var register = false
    private val dialogCreator = DialogCreator(this)

    private lateinit var registerLayout: LinearLayout
    private lateinit var title: TextView
    private lateinit var loginButton: Button
    private lateinit var getUser:()->User?

    private val authApiInteractor: AuthApiInteractor = AuthApiInteractor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerLayout = findViewById(R.id.registerLayout)
        title = findViewById(R.id.title_login)

        val registerButton: SwitchMaterial = findViewById(R.id.switchRegister)
        register = registerButton.isChecked
        registerButton.setOnClickListener{
            changeRegister((it as SwitchMaterial).isChecked)
        }

        loginButton= findViewById(R.id.bLogin)
        loginButton.setOnClickListener{
            if(register)
                handleRegister()
            else
                handleLogin()
        }

        val etUsername: EditText = findViewById(R.id.username_edit_text)
        val etPassword1: EditText = findViewById(R.id.password_edit_text)
        val etPassword2: EditText = findViewById(R.id.password2_edit_text)
        val etEmail: EditText = findViewById(R.id.email_edit_text)

        getUser = getUser@ {
            if(etUsername.text.isBlank() || etPassword1.text.isBlank())
                return@getUser null
            if(register){
                if(etPassword2.text.isBlank() || etEmail.text.isBlank())
                    return@getUser null
                if(etPassword1.text.toString() != etPassword2.text.toString())
                    return@getUser null
            }
           User(0,
                etUsername.text.toString(),
                etPassword1.text.toString(),
                etEmail.text.toString(),
               0
            )
        }
    }

    private fun createToken(username: String, password: String): String {
        val pass = "${username}:${password}"
        return Base64.getEncoder().encodeToString(pass.toByteArray())
    }

    private fun changeRegister(isChecked: Boolean){
        register = isChecked
        if(register){
            title.text = getString(R.string.title_register)
            loginButton.text = getString(R.string.b_register)
            registerLayout.visibility = View.VISIBLE
        }
        else {
            title.text = getString(R.string.title_login)
            loginButton.text = getString(R.string.b_sign_in)
            registerLayout.visibility = View.GONE
        }
    }

    private fun handleLogin(){
        val user = getUser()
        if(user == null){
            /*
            dialogCreator.createDialog("Login Error", "Could not login")
            return*/
            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
            myIntent.putExtra("token", createToken("asd", "asd")) //Optional parameters
            this@LoginActivity.startActivity(myIntent)
            return
        }
        val token = createToken(user.name, user.password)
        authApiInteractor.login(token, {
            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
            myIntent.putExtra("token", token) //Optional parameters
            myIntent.putExtra("user_id", it.id.toString())
            dialogCreator.createDialog("Login Error", it.id.toString())
            this@LoginActivity.startActivity(myIntent)
        }, {
            dialogCreator.createDialog("Login Error", "Server error. Could not login")
        })
    }

    private fun handleRegister(){
        val user = getUser()
        if(user == null){
            dialogCreator.createDialog("Register Error", "Could not register")
            return
        }
        authApiInteractor.register(user, {
            dialogCreator.createDialog("Registration Success", "Registration was successful. Now you can login.")
        }, {
            dialogCreator.createDialog("Register Error", it.message.toString())
        })
    }


}