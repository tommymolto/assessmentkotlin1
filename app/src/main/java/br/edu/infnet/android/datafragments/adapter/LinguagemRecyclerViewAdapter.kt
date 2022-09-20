package br.edu.infnet.android.datafragments.adapter

import android.content.Context
import android.view.ViewGroup
import br.edu.infnet.android.datafragments.model.LinguagemModel
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.android.datafragments.R
import br.edu.infnet.android.datafragments.ui.notifications.NotificationsFragment

class LinguagemRecyclerViewAdapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<LinguagemModel>,
    private val context: NotificationsFragment
    ) : RecyclerView.Adapter<LinguagemRecyclerViewAdapter.LinguagemViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): LinguagemRecyclerViewAdapter.LinguagemViewHolder {
            // this method is use to inflate the layout file
            // which we have created for our recycler view.
            // on below line we are inflating our layout file.
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.linguagem_card,
                parent, false
            )
            // at last we are returning our view holder
            // class with our item View File.
            return LinguagemViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: LinguagemRecyclerViewAdapter.LinguagemViewHolder, position: Int) {
            // on below line we are setting data to our text view and our image view.
            holder.courseNameTV.text = courseList.get(position).linguagemName
            holder.courseIV.setImageResource(courseList.get(position).linguagemImg)
        }

        override fun getItemCount(): Int {
            // on below line we are returning
            // our size of our list
            return courseList.size
        }

        class LinguagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // on below line we are initializing our course name
            // text view and our image view.
            val courseNameTV: TextView = itemView.findViewById(R.id.idTVLinguagem)
            val courseIV: ImageView = itemView.findViewById(R.id.idIVLinguagem)
        }
}