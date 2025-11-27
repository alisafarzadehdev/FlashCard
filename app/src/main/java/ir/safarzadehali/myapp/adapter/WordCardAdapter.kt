package ir.safarzadehali.myapp.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.safarzadehali.myapp.R
import ir.safarzadehali.myapp.databinding.ItemWordCardBinding
import ir.safarzadehali.myapp.model.all.Word

class WordCardAdapter(
    private var words: List<Word>,
    private val onSpeakClick: (Word) -> Unit          // برای تلفظ
) : RecyclerView.Adapter<WordCardAdapter.WordViewHolder>() {

    inner class WordViewHolder(val binding: ItemWordCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var isFlipped = false

        fun bind(item: Word, context: Context) {

            binding.tvEnglish.text = item.english
            binding.tvPersian.text = item.persian

            val animatorflip_in = AnimatorInflater.loadAnimator(context, R.animator.flip_in)

            val animatorflip_out = AnimatorInflater.loadAnimator(context, R.animator.flip_out)

            binding.cardRoot.setOnClickListener {

                if (!isFlipped) {
                    animatorflip_out.setTarget(binding.frontView)
                    animatorflip_in.setTarget(binding.backView)
                    binding.backView.visibility = ViewGroup.VISIBLE
                    animatorflip_out.start()
                    animatorflip_in.start()
                } else {
                    animatorflip_out.setTarget(binding.backView)
                    animatorflip_in.setTarget(binding.frontView)
                    animatorflip_out.start()
                    animatorflip_in.start()
                }
                isFlipped = !isFlipped
            }

            binding.btnSpeak.setOnClickListener {
                onSpeakClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemWordCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(words[position], holder.itemView.context)
    }

    override fun getItemCount() = words.size

    fun setData(newList: List<Word>) {
        this.words = newList
        notifyDataSetChanged()
    }
}
