package hu.aut.bme.treasurehuntingfrontend.ui.fragments.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.network.QuestApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.adapters.QuestAdapter
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator

class QuestsFragment : Fragment() {

    private lateinit var adapter: QuestAdapter
    private val api = QuestApiInteractor()
    private lateinit var dialogCreator: DialogCreator

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_quests, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        dialogCreator = DialogCreator(root.context)
        adapter = QuestAdapter(mutableListOf())
        recyclerView.adapter = adapter
        api.getQuests({
            adapter.updateDataSet(it)
        },{
            dialogCreator.createDialog("Network error", "Couldn't fetch quests data")
        })
        return root
    }
}