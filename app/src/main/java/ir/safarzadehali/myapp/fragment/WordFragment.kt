package ir.safarzadehali.myapp.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import ir.safarzadehali.myapp.adapter.WAdapter
import ir.safarzadehali.myapp.adapter.WordCardAdapter
import ir.safarzadehali.myapp.databinding.FragmentWordBinding
import ir.safarzadehali.myapp.model.all.Word
import java.util.Locale
import kotlin.getValue


class WordFragment : Fragment(),TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentWordBinding
    private lateinit var adapter: WAdapter

    private val args: WordFragmentArgs by navArgs()
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val title = args.FlashCard.title
        val desc = args.FlashCard.description
        args.FlashCard.words.forEach {
            Log.d("ddx", title+"onView: "+it.english)
            Log.d("ddx", desc+"Created: "+it.persian)
        }


        adapter = WAdapter(args.FlashCard.words) { word ->
            speak(word.english)
        }
        adapter.setData(args.FlashCard.words)

        binding.recyclerWords.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerWords.adapter = adapter

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.UK)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

}



/*
class WordFragment : Fragment() {

    private val args: WordFragmentArgs by navArgs()
    val card = args.flashCard
    private lateinit var binding: FragmentWordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var str = ""
        for (word in card.words)
        {
            str+=word.english+" : "+word.persian+"\n"
        }
        binding.titleee.setText(str)

        super.onViewCreated(view, savedInstanceState)
    }

}

 */