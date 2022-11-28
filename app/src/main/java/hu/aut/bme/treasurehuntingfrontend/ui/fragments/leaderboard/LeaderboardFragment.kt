package hu.aut.bme.treasurehuntingfrontend.ui.fragments.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.network.LeaderboardApiInteractor
import hu.aut.bme.treasurehuntingfrontend.ui.adapters.LeaderboardAdapter
import hu.aut.bme.treasurehuntingfrontend.ui.messaging.DialogCreator

class LeaderboardFragment : Fragment() {


    private lateinit var adapter: LeaderboardAdapter
    private val api = LeaderboardApiInteractor()
    private lateinit var dialogCreator: DialogCreator

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        dialogCreator = DialogCreator(root.context)
        adapter = LeaderboardAdapter(mutableListOf())
        recyclerView.adapter = adapter
        api.getScores({
            adapter.updateDataSet(it)
        },{
            dialogCreator.createDialog("Network error", "Couldn't fetch leaderboard data")
        })
        return root
    }
}