package hu.aut.bme.treasurehuntingfrontend.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.aut.bme.treasurehuntingfrontend.R

 class QuestDetailsOptionsAdapter (private val dataSet: List<String>) :
    RecyclerView.Adapter<QuestDetailsOptionsAdapter.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val vText: TextView

        init{
            // Define click listener for the ViewHolder's View.
            vText=view.findViewById(R.id.text)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:Int):ViewHolder{
        // Create a new view, which defines the UI of the list item
        val view= LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rowitem_quest_details_options,viewGroup,false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder:ViewHolder,position:Int){

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.vText.text=dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount()=dataSet.size

}