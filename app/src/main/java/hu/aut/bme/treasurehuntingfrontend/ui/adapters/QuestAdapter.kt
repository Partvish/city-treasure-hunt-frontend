package hu.aut.bme.treasurehuntingfrontend.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R
import hu.aut.bme.treasurehuntingfrontend.domain.Quest
import hu.aut.bme.treasurehuntingfrontend.domain.Score

class QuestAdapter (private val dataSet: MutableList<Quest>) :
    RecyclerView.Adapter<QuestAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vUsername: TextView
        val vPoint: TextView

        init {
            // Define click listener for the ViewHolder's View.
            vUsername = view.findViewById(R.id.text_username)
            vPoint = view.findViewById(R.id.text_point)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rowitem_leaderboard, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.vUsername.text = dataSet[position].name
        viewHolder.vPoint.text = dataSet[position].point.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateDataSet(quests: List<Quest>){
        dataSet.clear()
        dataSet.addAll(0, quests)
        notifyDataSetChanged()
    }
}