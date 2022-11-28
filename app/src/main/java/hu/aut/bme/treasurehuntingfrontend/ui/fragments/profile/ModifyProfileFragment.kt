package hu.aut.bme.treasurehuntingfrontend.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.domain.User
import hu.aut.bme.treasurehuntingfrontend.network.auth.AuthApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator

class ModifyProfileFragment: Fragment() {
    private val api: AuthApiInteractor = AuthApiInteractor()
    private lateinit var dialogCreator: DialogCreator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_modify_profile, container, false)
        val etUsername: EditText = root.findViewById(R.id.username_edit_text)
        val etPassword1: EditText = root.findViewById(R.id.password_edit_text)
        val etPassword2: EditText = root.findViewById(R.id.password2_edit_text)
        val etEmail: EditText = root.findViewById(R.id.email_edit_text)
        val bModify: Button = root.findViewById(R.id.bModify)
        bModify.setOnClickListener{
            if(etUsername.text.isBlank() || etPassword1.text.isBlank() || etPassword2.text.isBlank() || etEmail.text.isBlank())
                dialogCreator.createDialog("User data error", "One of the values is blank")
            if(etPassword1.text.toString() != etPassword2.text.toString())
                dialogCreator.createDialog("User data error", "Passwords are not matching")
            val user = User(
                User.ID,
                etUsername.text.toString(),
                etPassword1.text.toString(),
                etEmail.text.toString(),
                0
            )

            dialogCreator = DialogCreator(root.context)
            api.modifyProfile(user,{ _, status ->
                if(status!=200) {
                    dialogCreator.createDialog(
                        "User data error",
                        "Something went wrong. Could not get User data."
                    )
                    return@modifyProfile
                }
                dialogCreator.createDialog("Profile update success", "Profile update was successful!")
            },{
                dialogCreator.createDialog("User data error", "Something went wrong. Could not get User data.")
            })
        }


        return root
    }
}