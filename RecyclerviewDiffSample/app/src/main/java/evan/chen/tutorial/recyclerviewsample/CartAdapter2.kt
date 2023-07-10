package evan.chen.tutorial.recyclerviewsample

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

class CartAdapter2 :
    //step 1
    ListAdapter<CartItem, RecyclerView.ViewHolder>(CartDiffCallback()) {
    var listener: CartAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CartViewHolder2(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        //step 2 change carts to currentList
        (viewHolder as CartViewHolder2).bind(currentList[position])
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int,
        payload: List<Any>
    ) {
        val cartItem = getItem(position)
        val holder = viewHolder as CartViewHolder2
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int {
        //step 3 change carts to currentList
        return currentList.count()
    }
}

private class CartViewHolder2(val viewBinding: ItemLayoutBinding, val listener: CartAdapterListener? = null) :
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
}

//step 4 add CartDiffCallback
class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}