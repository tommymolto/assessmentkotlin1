package br.edu.infnet.android.datafragments.ui

import android.Manifest
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import br.edu.infnet.android.datafragments.databinding.FragmentLinguagemBinding
import br.edu.infnet.android.datafragments.helper.Permission
import br.edu.infnet.android.datafragments.helper.PermissionManager
import com.google.android.material.snackbar.Snackbar


class LinguagemFragment : Fragment() {
    private val permissionManager = PermissionManager.from(this)

    private var _binding: FragmentLinguagemBinding? = null
    private var linguagemName: String = ""
    private var linguagemImg: String = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
             linguagemImg = bundle.getString("linguagemImg")!!
             linguagemName = bundle.getString("linguagemName")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLinguagemBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        if(linguagemName.isNotEmpty()){
            binding.textView.text = linguagemName
        }
        if(linguagemImg.isNotEmpty()){
            //LinguagemRecyclerViewAdapter.DownloadImageFromInternet(binding.imageView).execute(linguagemImg)
        }
        binding.btnIntent.setOnClickListener {
            _ ->
            Snackbar.make(this.requireView(), "Ja vai", Snackbar.LENGTH_SHORT).show()

            val subject = "Dica de linguagem"
            val message = "Te recomentdaram a linguagem $linguagemName"
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, "paulo.marinho@prof.infnet.edu.br")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }
            if(intent.resolveActivity(requireActivity().packageManager) != null){
                startActivity(intent)
            }else{
                Snackbar.make(this.requireView(), "Não há programa de email configurado", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.btLigar.setOnClickListener {
                _ ->


            val telefone = binding.editTextPhone.text.trim().toString()
            if(telefone.isNotEmpty() && telefone.isDigitsOnly() ){
                permissionManager
                    .request(Permission.CallPhone, Permission.Storage)
                    .rationale("We need two permissions at once!")
                    .checkDetailedPermission { result: Map<Permission, Boolean> ->
                        if (result.all { it.value }) {
                            // We have all the permissions
                            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$telefone"))
                                if(intent.resolveActivity(requireActivity().packageManager) != null){
                                    startActivity(intent)
                                }else{
                                    Snackbar.make(this.requireView(), "Não há programa de email configurado", Snackbar.LENGTH_SHORT).show()
                                }
                        } else {
                            // Check in result which permission was denied
                            Snackbar.make(this.requireView(), "Negado", Snackbar.LENGTH_SHORT).show()

                        }
                    }
                /*val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$telefone"))
                if(intent.resolveActivity(requireActivity().packageManager) != null){
                    startActivity(intent)
                }else{
                    Snackbar.make(this.requireView(), "Não há programa de email configurado", Snackbar.LENGTH_SHORT).show()
                }*/
            }else{
                binding.editTextPhone.error = "Entre com um telefone valido"
            }

        }
        binding.btShare.setOnClickListener {
            _ ->
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Gostaria de saber mais sobre a linguagem $linguagemName")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }



        return root
    }




}