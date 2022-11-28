package hu.aut.bme.treasurehuntingfrontend.ui.fragments.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.domain.AcceptUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.AnswerUserQuestDto
import hu.aut.bme.treasurehuntingfrontend.domain.Quest
import hu.aut.bme.treasurehuntingfrontend.network.QuestApiInteractor
import hu.aut.bme.treasurehuntingfrontend.network.UserQuestApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.adapters.QuestDetailsOptionsAdapter
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator
import java.util.*

class QuestDetailsFragment: Fragment() {
    private lateinit var dialogCreator: DialogCreator
    private val questApi: QuestApiInteractor = QuestApiInteractor()
    private val userQuestApi: UserQuestApiInteractor = UserQuestApiInteractor()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_quest_details, container, false)
        dialogCreator = DialogCreator(root.context)
        val tName: TextView  = root.findViewById(R.id.text_name)

        val tLongitude: TextView = root.findViewById(R.id.text_longitude)
        val tLatitude: TextView = root.findViewById(R.id.text_latitude)
        val tPoints: TextView = root.findViewById(R.id.text_points)

        val layoutActiveQuest: LinearLayout = root.findViewById(R.id.layout_active_quest)
        val tDescription: TextView = root.findViewById(R.id.text_description)
        val etAnswer: EditText = root.findViewById(R.id.answer_text_input)
        val rwOptions: RecyclerView = root.findViewById(R.id.recycler_view)
        rwOptions.adapter = QuestDetailsOptionsAdapter(listOf())
        val bFinish: Button = root.findViewById(R.id.bFinish)
        val bAbandon: Button = root.findViewById(R.id.bAbandon)

        val bAccept: Button = root.findViewById(R.id.bAccept)
        val bSuggest: Button = root.findViewById(R.id.bSuggest)
        val questId = 0L
        questApi.getQuest(questId,{
            tName.text = it.name
            tLatitude.text = it.latitude.toString()
            tLongitude.text = it.longitude.toString()
            tPoints.text = it.point.toString()
            tDescription.text = it.description.toString()
            if(it.options.contains(":")){
                rwOptions.visibility = View.VISIBLE
                rwOptions.adapter=QuestDetailsOptionsAdapter(it.options.split(":"))
            }
            userQuestApi.getState(questId, { id, _ ->
                if(id == Quest.STARTED) {
                    layoutActiveQuest.visibility = View.VISIBLE
                    bAccept.visibility = View.GONE
                }
                else {
                    layoutActiveQuest.visibility = View.GONE
                    bAccept.visibility = View.VISIBLE
                }
            }, {})

        },{
            dialogCreator.createDialog("Quest data error", "Something went wrong.")
        })

        bFinish.setOnClickListener{
            val answer = etAnswer.text.toString()
            userQuestApi.finish(questId, AnswerUserQuestDto(answer),
                { response, _ ->
                    if( response.lowercase(Locale.ROOT) !="done") {
                        dialogCreator.createDialog(
                            "Quest not finished",
                            "Wrong answer, Quest was not finished."
                        )
                        return@finish
                    } else
                    {
                        dialogCreator.createDialog(
                            "Quest finished",
                            "Right answer, Quest was finished."
                        )
                    }

            },{
                dialogCreator.createDialog("Quest data error", "Something went wrong.")
            })
        }

        bAbandon.setOnClickListener{
            userQuestApi.abandon(questId, { _, code ->
                if(code!=200){
                    dialogCreator.createDialog("Abandonment error", "Quest was not abandoned")
                    return@abandon
                }
                dialogCreator.createDialog("Abandonment success", "Quest was abandoned")
            },{
                dialogCreator.createDialog("Network error", "Something went wrong.")
            })
        }

        bAccept.setOnClickListener{
            userQuestApi.accept(AcceptUserQuestDto(0f,0f, questId), { _ ,status ->
                if(status != 200)
                    dialogCreator.createDialog("Quest acceptation error", "Quest was not accepted")
            }, {
                dialogCreator.createDialog("Network error", "Something went wrong.")
            })
        }


        return root
    }
}