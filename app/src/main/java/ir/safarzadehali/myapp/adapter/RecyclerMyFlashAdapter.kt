package ir.safarzadehali.myapp.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.safarzadehali.myapp.R
import ir.safarzadehali.myapp.databinding.ItemFlashCardBinding
import ir.safarzadehali.myapp.databinding.ItemWordCardBinding
import ir.safarzadehali.myapp.model.all.FlashCard
import ir.safarzadehali.myapp.model.all.Word
import ir.safarzadehali.myapp.model.mycard.MyFlashcardData

class RecyclerMyFlashAdapter(
    private var flashcard: List<MyFlashcardData>,
    private val onItemClick: (MyFlashcardData) -> Unit
    ):
    RecyclerView.Adapter<RecyclerMyFlashAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFlashCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: MyFlashcardData) {
            binding.tvDescription.text = item.title

            // ✅ کلیک
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFlashCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(flashcard[position])
    }

    override fun getItemCount() = flashcard.size

    fun setData(newList: List<MyFlashcardData>) {
        this.flashcard = newList
        notifyDataSetChanged()
    }
}
