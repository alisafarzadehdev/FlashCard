package ir.safarzadehali.myapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.safarzadehali.myapp.databinding.ItemFlashCardBinding
import ir.safarzadehali.myapp.model.all.FlashCard

class FlashCardAdapter(
    private var flashCards: List<FlashCard>,
    private val onItemClick: (FlashCard) -> Unit   // ✅ کال‌بک برای کلیک
) : RecyclerView.Adapter<FlashCardAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFlashCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlashCard) {
            binding.tvDescription.text = item.description
            binding.tvTitle.text = item.title

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
        holder.bind(flashCards[position])
    }

    override fun getItemCount() = flashCards.size

    fun setData(newList: List<FlashCard>) {
        flashCards = newList
        notifyDataSetChanged()
    }
}

