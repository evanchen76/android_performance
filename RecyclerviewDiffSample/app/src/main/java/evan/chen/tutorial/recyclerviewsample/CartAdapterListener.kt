package evan.chen.tutorial.recyclerviewsample

interface CartAdapterListener{
    fun onProductPlus(productId:String)
    fun onProductMinus(productId:String)
}