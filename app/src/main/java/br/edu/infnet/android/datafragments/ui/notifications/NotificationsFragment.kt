package br.edu.infnet.android.datafragments.ui.notifications

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.android.datafragments.R
import br.edu.infnet.android.datafragments.adapter.LinguagemRecyclerViewAdapter
import br.edu.infnet.android.datafragments.databinding.FragmentNotificationsBinding
import br.edu.infnet.android.datafragments.model.LinguagemModel
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Executors

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var linguagemRV: RecyclerView
    lateinit var linguagemRVAdapter: LinguagemRecyclerViewAdapter
    lateinit var linguagemList: ArrayList<LinguagemModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        linguagemRV = _binding!!.rvLinguagens
        linguagemList = ArrayList()
        linguagemRVAdapter = LinguagemRecyclerViewAdapter(
            linguagemList,
            this,
            LinguagemRecyclerViewAdapter.OnClickListener { item ->
                acaoClickListView(item)
                //Toast.makeText(context, "${item.linguagemName}", Toast.LENGTH_SHORT).show()
                })
        linguagemRV.adapter = linguagemRVAdapter

        linguagemList.add(LinguagemModel("Android 1",  "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))
        linguagemList.add(LinguagemModel("C++ ", "https://www.alura.com.br/artigos/assets/formacao-linguagem-c-plus-plus/img-01.png"))
        linguagemList.add(LinguagemModel("Java 1", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemList.add(LinguagemModel("Android 2", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemList.add(LinguagemModel("Java 2", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))
        linguagemList.add(LinguagemModel("Android 3", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemList.add(LinguagemModel("Python ", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))
        linguagemList.add(LinguagemModel("Java 5", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemList.add(LinguagemModel("Android 4", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemList.add(LinguagemModel("JavaScript ", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))
        linguagemList.add(LinguagemModel("Java 8", "https://img.ibxk.com.br/2014/05/08/08145827911459.png?ims=704x"))

        linguagemRVAdapter.notifyDataSetChanged()
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse: LinguagemModel =
                    linguagemList[viewHolder.adapterPosition]

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                linguagemList.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                linguagemRVAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                Snackbar.make(linguagemRV, "Deletado " + deletedCourse.linguagemName, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Desfazer",
                        View.OnClickListener {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            linguagemList.add(position, deletedCourse)

                            // below line is to notify item is
                            // added to our adapter class.
                            linguagemRVAdapter.notifyItemInserted(position)
                        }).show()
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(linguagemRV)



        return root
    }
    fun acaoClickListView(item: LinguagemModel){
        val bundle = Bundle()
        bundle.putString("linguagemName",item.linguagemName)
        bundle.putString("linguagemImg",item.linguagemImg)

        findNavController()
            .navigate(R.id.navigation_linguagem, bundle)
        Toast.makeText(context, item.linguagemName, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}