package evan.chen.tutorial.macrobenchmarksample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.macrobenchmarksample.databinding.ItemLayoutBinding

class MainAdapter(private val names: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as ItemViewHolder).setText(names[position])
    }

    override fun getItemCount(): Int {
        return names.count()
    }

}

private class ItemViewHolder(viewBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    var textView: TextView = viewBinding.textView

    fun setText(name:String){
        textView.text = name
    }

}
