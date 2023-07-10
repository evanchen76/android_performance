package evan.chen.tutorial.statesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.statesample.databinding.ActivityProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProductBinding.inflate(layoutInflater) }

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        viewModel.setProductIdA("pixel6")
        viewModel.getProduct()

        binding.pixel7.setOnClickListener {
            viewModel.setProductIdA("pixel7")
            viewModel.getProduct()
        }

        binding.pixel6.setOnClickListener {
            viewModel.setProductIdA("pixel6")
            viewModel.getProduct()
        }

//        if (savedInstanceState != null){
//            println("ProductActivity onCreate savedInstanceState")
//            productId = savedInstanceState.getString("productId") ?: ""
//        }else {
//            productId = intent.getStringExtra("productId") ?: "1"
//        }

        viewModel.product.observe(this) {

            binding.productName.text = it.productName
            binding.productPrice.text = "$${it.productPrice}"

            binding.productImage.setImageResource(
                resources.getIdentifier(
                    it.productImage,
                    "mipmap",
                    packageName
                )
            )
        }
    }

}