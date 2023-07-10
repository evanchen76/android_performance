package evan.chen.tutorial.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.recyclerviewsample.databinding.ItemLayoutBinding
import java.text.NumberFormat
import java.util.*

class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var carts = listOf<CartItem>()
    var listener: CartAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CartViewHolder(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as CartViewHolder).bind(carts[position])
    }

    override fun getItemCount(): Int {
        return carts.count()
    }
}

private class CartViewHolder(val viewBinding: ItemLayoutBinding, val listener: CartAdapterListener? = null):
    RecyclerView.ViewHolder(viewBinding.root) {

    val nameView: TextView = viewBinding.name
    val priceView: TextView = viewBinding.price
    val quantityView: TextView = viewBinding.quantity
    val imageView:ImageView = viewBinding.image
    val plusButton:Button = viewBinding.btnPlus
    val minusButton:Button = viewBinding.btnMinus

    fun bind(cartItem: CartItem) {
        nameView.text = cartItem.productName
        priceView.text = priceToString(cartItem.productPrice)
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

    private fun priceToString(price: Int): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.TAIWAN)
        formatter.maximumFractionDigits = 0

        return formatter.format(price)
    }
}

