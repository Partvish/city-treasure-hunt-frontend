package hu.aut.bme.treasurehuntingfrontend.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.network.auth.AuthApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator

class ProfileFragment : Fragment() {

    private val api: AuthApiInteractor = AuthApiInteractor()
    private lateinit var dialogCreator: DialogCreator

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textName: TextView  = root.findViewById(R.id.text_name)
        val textEmail: TextView  = root.findViewById(R.id.text_email)
        val textPoints: TextView  = root.findViewById(R.id.text_points)
        val bModify: Button = root.findViewById(R.id.bModify)
        dialogCreator = DialogCreator(root.context)
        api.getUser({
            textName.text = it.name
            textEmail.text = it.email
            textPoints.text = it.points.toString()
        },{
            dialogCreator.createDialog("User data error", "Something went wrong. Could not get User data.")
        })

        bModify.setOnClickListener{

        }
        return root
    }
}