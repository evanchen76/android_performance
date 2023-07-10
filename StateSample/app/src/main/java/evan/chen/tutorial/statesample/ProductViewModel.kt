package evan.chen.tutorial.statesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ProductViewModel(val repository: ProductRepository, val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var product: MutableLiveData<Product> = MutableLiveData()
    var productId: String = savedStateHandle["productId"] ?: ""

    init {
        productId = "pixel6"
    }

    fun setProductIdA(productId: String) {
        this.productId = productId
        savedStateHandle["productId"] = productId
    }

    fun getProduct() {
        product.value = repository.getProduct(productId)
    }

    init {
        println("ProductViewModel init:$productId")

    }
}