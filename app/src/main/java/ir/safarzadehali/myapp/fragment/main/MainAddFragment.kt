package ir.safarzadehali.myapp.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import ir.safarzadehali.myapp.R
import ir.safarzadehali.myapp.ViewModel.FlashCardViewModel
import ir.safarzadehali.myapp.databinding.FragmentAddBinding
import kotlin.getValue


class MainAddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: FlashCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // افزودن یک آیتم ورودی جدید
        binding.btnAddWord.setOnClickListener {
            val itemView = layoutInflater.inflate(R.layout.item_word, binding.wordsContainer, false)
            binding.wordsContainer.addView(itemView)
        }

        // ثبت نهایی فلش کارت
        binding.btnSubmitFlashcard.setOnClickListener {

            // ۱) لیست قابل تغییر

            val englishList = mutableListOf<String>()
            val persianList = mutableListOf<String>()

            // ۲) پیمایش همه‌ی آیتم‌های داخل wordsContainer
            for (i in 0 until binding.wordsContainer.childCount) {
                val item = binding.wordsContainer.getChildAt(i)

                val etEnglish = item.findViewById<EditText>(R.id.etEnglish)
                val etPersian = item.findViewById<EditText>(R.id.etPersian)

                val en = etEnglish.text.toString().trim()
                val fa = etPersian.text.toString().trim()

                if (en.isNotEmpty() && fa.isNotEmpty()) {
                    englishList.add(en)
                    persianList.add(fa)
                }
            }

            // بررسی اینکه حداقل یک کلمه وارد شده باشد
            if (englishList.isEmpty()) {
                Toast.makeText(requireContext(), "حداقل یک کلمه اضافه کن!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ارسال به سرور
            val id = (1..600).random()

            viewModel.addFlashCard(
                userId = 8,
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString(),
                english = englishList,
                persian = persianList
            )

            // LiveData مشاهده نتایج
            viewModel.responseLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "فلش کارت با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show()
            }

            viewModel.errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }

        }

    }
}
