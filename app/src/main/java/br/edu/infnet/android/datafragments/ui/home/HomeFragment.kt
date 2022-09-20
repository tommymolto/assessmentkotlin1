package br.edu.infnet.android.datafragments.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.android.datafragments.databinding.FragmentHomeBinding
import br.edu.infnet.android.datafragments.viewmodel.TextoViewModel

class HomeFragment : Fragment() {

    private  val _textoViewModel: TextoViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        _binding!!.button.setOnClickListener {
            _ ->
            val texto = binding.editTextTextPersonName.text.toString()
            if(texto.isNotEmpty()){
                _textoViewModel.changeText(texto)
            }else{
                _binding!!.editTextTextPersonName.error = "entre com um valor"
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}