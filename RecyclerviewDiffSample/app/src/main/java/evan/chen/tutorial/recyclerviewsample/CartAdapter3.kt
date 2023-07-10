package evan.chen.tutorial.recyclerviewsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.recyclerviewsample.databinding.ItemLayoutBinding
import java.util.*

class CartAdapter3 :
    ListAdapter<CartItem, RecyclerView.ViewHolder>(CartDiffCallback2()) {
    var listener: CartAdapterListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CartViewHolder3(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CartViewHolder3).bind(currentList[position])
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int,
        payload: List<Any>
    ) {
        val cartItem = getItem(position)
        val holder = viewHolder as CartViewHolder3

        //從payload取得更新的資料
        if (payload.isEmpty() || payload[0] !is Bundle) {
            holder.bind(cartItem)
        } else {
            //只更新購買數量
            val bundle = payload[0] as Bundle
            holder.update(bundle)
        }
    }

    override fun getItemCount(): Int {
        return currentList.count()
    }
}

private class CartViewHolder3(val viewBinding: ItemLayoutBinding, val listener: CartAdapterListener? = null) :
    RecyclerView.ViewHolder(viewBinding.root) {

    val nameView: TextView = viewBinding.name
    val priceView: TextView = viewBinding.price
    val quantityView: TextView = viewBinding.quantity
    val imageView: ImageView = viewBinding.image
    val plusButton: Button = viewBinding.btnPlus
    val minusButton: Button = viewBinding.btnMinus

    fun bind(cartItem: CartItem) {
        nameView.text = cartItem.productName
        priceView.text = cartItem.productPrice.toString()
        quantityView.text = "數量：${cartItem.productQuantity}"

        val resourceId = viewBinding.root.context.resources.getIdentifier(
            cartItem.productImage,
            "mipmap",
            "evan.chen.tutorial.recyclerviewsample"
        )
        imageView.setImageResource(resourceId)

        plusButton.setOnClickListener {
            listener?.onProductPlus(cartItem.productId)
        }

        minusButton.setOnClickListener {
            listener?.onProductMinus(cartItem.productId)
        }
    }

    fun update(bundle: Bundle) {
        bundle.getInt("Quantity").let {
            quantityView.text = "數量：$it"
        }
    }
}

class CartDiffCallback2 : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CartItem, newItem: CartItem): Any? {
        if (oldItem.productId == newItem.productId) {
            return if (oldItem == newItem) {
                //整個item都相同
                super.getChangePayload(oldItem, newItem)
            } else {
                val diff = Bundle()
                if (oldItem.productQuantity != newItem.productQuantity) {
                    diff.putInt("Quantity", newItem.productQuantity)
                }
                diff
            }
        }

        //id不同
        return super.getChangePayload(oldItem, newItem)
    }
}