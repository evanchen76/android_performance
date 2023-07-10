package evan.chen.tutorial.containertransformsample

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso


class MainAdapter(val context: Context, var scenerys: List<Scenery>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: OnItemClickListener? = null

    companion object {
        const val VIEW_TYPE_ITEM = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val scenery = scenerys[position]

            Picasso.with(context).load(scenery.thumbUrl)
                    .noFade()
                    .into(viewHolder.imageView)

            viewHolder.textView.text = scenery.name

            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.getLayoutPosition()
                listener?.onItemClick(viewHolder.itemView, position)
            }

        }

    }

    override fun getItemCount(): Int {
        return scenerys.count()
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_ITEM
    }

    private inner class ItemViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.findViewById(R.id.sceneryTitle)
        var imageView: SquareImageView = itemView.findViewById(R.id.sceneryImageView)

    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


}
