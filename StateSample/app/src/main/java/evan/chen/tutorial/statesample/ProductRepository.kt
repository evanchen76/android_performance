package evan.chen.tutorial.statesample

class ProductRepository {
    fun getProduct(productId: String): Product {
        println("ProductRepository getProduct:$productId")

        //模擬由後端取得產品資料
        if ( productId == "pixel6") {
            return Product(
                productId = productId,
                productName = "Google Pixel 6",
                productPrice = 20000,
                productImage = "pixel6"
            )
        }else if ( productId == "pixel7") {
            return Product(
                productId = productId,
                productName = "Google Pixel 7",
                productPrice = 28000,
                productImage = "pixel7"
            )
        }else{
            return Product(
                productId = productId,
                productName = "",
                productPrice = 0,
                productImage = ""
            )
        }
    }
}