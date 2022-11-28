package hu.aut.bme.treasurehuntingfrontend.ui.fragments.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.domain.Suggestion
import hu.aut.bme.treasurehuntingfrontend.domain.User
import hu.aut.bme.treasurehuntingfrontend.network.QuestApiInteractor
import hu.aut.bme.treasurehuntingfrontend.network.SuggestionApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.adapters.QuestAdapter
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator

class SuggestionFragment:Fragment() {
    private val api = SuggestionApiInteractor()
    private lateinit var dialogCreator: DialogCreator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_suggestion, container, false)
        val questId: Long =  arguments?.getLong("quest_id")?: 0L
        dialogCreator = DialogCreator(root.context)
        val bSend: Button = root.findViewById(R.id.bSend)
        val etSuggestion: EditText = root.findViewById(R.id.text_suggestion)
        bSend.setOnClickListener{
            if(etSuggestion.text.length > 250) {
                dialogCreator.createDialog("Suggestion Error", "Message too long!")
                return@setOnClickListener
            }
            api.postSuggestion(Suggestion(
                userId = User.ID,
                questId = questId,
                description = etSuggestion.text.toString().trim()
            ),success@{ status ->
                if(status!=200) {
                    dialogCreator.createDialog("Suggestion error", "Bad request")
                    return@success
                }
                dialogCreator.createDialog("Suggestion successfull", "Suggestion received.")

            },{
                dialogCreator.createDialog("Network error", "Couldn't fetch quests data")
            })
        }

        return root
    }
}