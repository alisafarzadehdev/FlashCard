package ir.safarzadehali.myapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ir.safarzadehali.myapp.ViewModel.RegisterViewModel
import ir.safarzadehali.myapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private val vm: RegisterViewModel by viewModels()
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btntest.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToMainFragment()
            findNavController().navigate(action)
        }
        binding.btnRegister.setOnClickListener{
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            vm.register(username, email, password)
        }

        activity?.let {
            vm.registerResult.observe(it) { res ->

                Log.d("TAG", "status "+res.status)
                Log.d("TAG", "message "+res.message)
                Log.d("TAG", "user "+ res.user?.id +":"+ res.user?.username)
                var id: Int = res.user!!.id!!.toInt()
                val action = RegisterFragmentDirections.actionRegisterFragmentToMainFragment()
                /*
                val action = RegisterFragmentDirections
                    .actionRegisterFragmentToFlashCardListFragment(userId = id)


                 */
                findNavController().navigate(action)

            }
            vm.errorMessage.observe(it) { msg ->
                Log.d("TAG", "onViewCreated: succres "+msg)
            }
        }




        super.onViewCreated(view, savedInstanceState)
    }



}