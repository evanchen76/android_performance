package evan.chen.tutorial.arch.roomcoroutinesample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.arch.roomcoroutinesample.data.DailyRecord
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.ItemRowBinding

class AccountAdapter(private var viewModel: AccountViewModel, private var items: List<DailyRecord>) :
    RecyclerView.Adapter<AccountAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]
        holder.bind(viewModel, item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ItemViewHolder constructor(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: AccountViewModel, item: DailyRecord ) {

            binding.type.text = item.type
            binding.category.text = item.categoryName
            binding.money.text = item.money.toString()
            binding.memo.text = item.memo.toString()

            binding.root.setOnClickListener {
                viewModel.openItem(item.id)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRowBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding)
            }
        }

    }

}
