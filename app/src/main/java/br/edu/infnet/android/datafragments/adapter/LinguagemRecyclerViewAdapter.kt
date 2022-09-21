package br.edu.infnet.android.datafragments.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import br.edu.infnet.android.datafragments.model.LinguagemModel
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.android.datafragments.R
import br.edu.infnet.android.datafragments.ui.notifications.NotificationsFragment
import java.util.concurrent.Executors

class LinguagemRecyclerViewAdapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<LinguagemModel>,
    private val context: NotificationsFragment,
    private val onClickListener: OnClickListener
    ) : RecyclerView.Adapter<LinguagemRecyclerViewAdapter.LinguagemViewHolder>() {
    val executor = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())
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
            holder.courseNameTV.text = courseList[position].linguagemName
            holder.itemView.setOnClickListener {
                onClickListener.onClick(courseList[position])
            }
            DownloadImageFromInternet(holder.courseIV).execute(courseList.get(position).linguagemImg)



            //holder.courseIV.setImageResource(courseList.get(position).linguagemImg)
        }

        override fun getItemCount(): Int {
            // on below line we are returning
            // our size of our list
            return courseList.size
        }

        class LinguagemViewHolder(itemView: View,
             ) : RecyclerView.ViewHolder(itemView) {
            // on below line we are initializing our course name
            // text view and our image view.

            val courseNameTV: TextView = itemView.findViewById(R.id.idTVLinguagem)
            val courseIV: ImageView = itemView.findViewById(R.id.idIVLinguagem)

            init{
                itemView.setOnClickListener { _ ->
                    Toast.makeText(
                        itemView!!.context,
                        "Please wait, it may take a few minute...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    class OnClickListener(val clickListener: (linguagem: LinguagemModel) -> Unit) {
        fun onClick(linguagem: LinguagemModel) = clickListener(linguagem)
    }
    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
           // Toast.makeText(applicationContext, "Please wait, it may take a few minute...",     Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }
}