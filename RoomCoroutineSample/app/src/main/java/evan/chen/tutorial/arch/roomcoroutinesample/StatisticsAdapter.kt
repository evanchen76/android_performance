package evan.chen.tutorial.arch.roomcoroutinesample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountStatistics
import evan.chen.tutorial.arch.roomcoroutinesample.databinding.StatisticsItemRowBinding

class StatisticsAdapter(var items: List<AccountStatistics>) :
    RecyclerView.Adapter<StatisticsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ItemViewHolder constructor(private val binding: StatisticsItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AccountStatistics) {

            binding.type.text = item.type
            binding.category.text = item.categoryName
            binding.money.text = item.money.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StatisticsItemRowBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding)
            }
        }

    }

}
