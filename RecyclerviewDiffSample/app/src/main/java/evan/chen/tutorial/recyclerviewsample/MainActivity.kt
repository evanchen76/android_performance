package evan.chen.tutorial.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import evan.chen.tutorial.recyclerviewsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: CartAdapter
    private var carts = listOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        title = "購物車"

        createCarts()

        adapter = CartAdapter()
        adapter.carts = carts

        val listener = object : CartAdapterListener {
            override fun onProductPlus(productId: String) {
                carts = carts.map {
                    if (it.productId == productId) {
                        it.copy(productQuantity = it.productQuantity + 1)
                    } else {
                        it
                    }
                }
//                adapter.submitList(carts)
                adapter.carts = carts
                adapter.notifyDataSetChanged()
            }

            override fun onProductMinus(productId: String) {
                carts = carts.map {
                    if (it.productId == productId) {
                        it.copy(productQuantity = it.productQuantity - 1)
                    } else {
                        it
                    }
                }
                carts = carts.filter { it.productQuantity > 0 }
                adapter.carts = carts
                adapter.notifyDataSetChanged()
//                adapter.submitList(carts)
            }
        }

        adapter = CartAdapter()
        adapter.carts = carts
//        adapter.submitList(carts)
        adapter.listener = listener
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

    }

    private fun createCarts() {
        carts = listOf(
            CartItem("1", "Google Pixel 6", 1, 23900, "pixel6"),
            CartItem("2", "Google Pixel 6 Pro", 1, 32900, "pixel6pro"),
            CartItem("3", "Google Pixel 7", 1, 16900,"pixel7"),
            CartItem("4", "Google Pixel 7 Pro", 1, 12490,"pixel7pro"),
            CartItem("5", "Google Pixel Watch", 1, 9999, "watch"),
            CartItem("6", "Google Pixel Buds A-Series", 1, 3290, "pixelbudsaseries"),
            CartItem("7", "Google Chromecast with Google TV", 1, 2190, "tv"),
            CartItem("8", "Google Nest Hub", 1, 4990, ""),
            CartItem("9", "Google Nest Audio", 1, 3490,""),
            CartItem("10", "Google Pixel Stand", 1, 2390,""),
            CartItem("11", "Google Nest Mini", 1, 1290,""),
            CartItem("12", "Google Wifi", 1, 3390,""),
            CartItem("13", "Google Pixelbook Go", 1, 42900,""),
            CartItem("14", "Google Nest Cam Indoor", 1, 3990,""),
            CartItem("15", "Google Stadia", 1, 1190,""),
            CartItem("16", "Google Nest Thermostat", 1, 9990,""),
            CartItem("17", "Google Pixel Slate", 1, 59900,""),
            CartItem("18", "Google Daydream View", 1, 1490,""),
            CartItem("19", "Google Clips", 1, 4990,""),
            CartItem("20", "Google Wifi Router", 1, 2190,"")
        )
    }

}
