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
import ir.safarzadehali.myapp.ViewModel.FlashCardViewModel
import ir.safarzadehali.myapp.adapter.RecyclerMyFlashAdapter
import ir.safarzadehali.myapp.databinding.FragmentMyFlashCardBinding
import ir.safarzadehali.myapp.model.all.FlashCard
import ir.safarzadehali.myapp.model.all.Word
import kotlin.getValue


class MainMyFlashCardFragment : Fragment() {

    private lateinit var binding: FragmentMyFlashCardBinding
    private val viewModel: FlashCardViewModel by viewModels()
    private lateinit var adapter: RecyclerMyFlashAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFlashCardBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loadFlashCards(8)
        viewModel.myflashCards.observe(viewLifecycleOwner) {
            it.forEach { card ->
                Log.d("TAcG", "onViewCreated: "+it)
            }
        }
        adapter = RecyclerMyFlashAdapter(emptyList()) { card ->
            card.words.forEach {
                Log.d("Card", "English word: ${it.english}")
            }

        }
        binding.recyclermyFlashCards.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclermyFlashCards.adapter = adapter

        // مشاهده‌ی داده‌ها از ViewModel
        viewModel.myflashCards.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
            Log.d("TAdG", "onViewCreated: "+list)
        }


    }
}