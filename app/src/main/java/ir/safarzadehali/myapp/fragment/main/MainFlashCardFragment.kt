package ir.safarzadehali.myapp.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ir.safarzadehali.myapp.adapter.FlashCardAdapter
import ir.safarzadehali.myapp.ViewModel.FlashCardViewModel
import ir.safarzadehali.myapp.databinding.FragmentFlashCardMainBinding
import kotlin.getValue


class MainFlashCardFragment : Fragment() {

    private lateinit var binding: FragmentFlashCardMainBinding
    private val viewModel: FlashCardViewModel by viewModels()
    private lateinit var adapter: FlashCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFlashCardMainBinding.inflate(inflater,container, false)
        Log.d("onViewCreated", "ddd:"+binding.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("xxxx", "xxxx:"+binding.toString())

        viewModel.loadFlashCards()
        viewModel.flashcards.observe(viewLifecycleOwner) {
            it.forEach { card ->
                Log.d("SSG", "onViewCreated: " + card.id)
                Log.d("TAcG", "onViewCreated: "+it)
            }

        }

        adapter = FlashCardAdapter(emptyList()) { card ->
            card
            // چاپ برای تست
            card.words.forEach {
                Log.d("TAxG", "onViewCreated: "+it)
                Log.d("Card", "English word: ${it.english}")
            }

            // اینجا باید Navigation انجام بشه 👇

            val action = MainFlashCardFragmentDirections
                .actionMyFlashCardFragmentToWordFragment(card)

            findNavController().navigate(action)






        }

        binding.recyclerFlashCards.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerFlashCards.adapter = adapter

        // مشاهده‌ی داده‌ها از ViewModel
        viewModel.flashcards.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
            Log.d("TAdG", "onViewCreated: "+list)
        }


    }

}