package br.edu.infnet.android.datafragments.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.android.datafragments.R
import br.edu.infnet.android.datafragments.adapter.LinguagemRecyclerViewAdapter
import br.edu.infnet.android.datafragments.databinding.FragmentNotificationsBinding
import br.edu.infnet.android.datafragments.model.LinguagemModel

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
        linguagemRVAdapter = LinguagemRecyclerViewAdapter(linguagemList, this)
        linguagemRV.adapter = linguagemRVAdapter

        linguagemList.add(LinguagemModel("Android 1", R.drawable.android))
        linguagemList.add(LinguagemModel("C++ ", R.drawable.c))
        linguagemList.add(LinguagemModel("Java 1", R.drawable.java))

        linguagemList.add(LinguagemModel("Android 2", R.drawable.android))

        linguagemList.add(LinguagemModel("Java 2", R.drawable.java))
        linguagemList.add(LinguagemModel("Android 3", R.drawable.android))

        linguagemList.add(LinguagemModel("Python ", R.drawable.python))
        linguagemList.add(LinguagemModel("Java 5", R.drawable.java))

        linguagemList.add(LinguagemModel("Android 4", R.drawable.android))

        linguagemList.add(LinguagemModel("JavaScript ", R.drawable.js))
        linguagemList.add(LinguagemModel("Java 8", R.drawable.java))

        linguagemRVAdapter.notifyDataSetChanged()




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}