package dojo.supermarket.model

class FakeCatalog : SupermarketCatalog {
    private val products = mutableMapOf<String, Product>()
    private val prices = mutableMapOf<String, Double>()

    override fun addProduct(product: Product, price: Double) {
        this.products[product.name] = product
        this.prices[product.name] = price
    }

    override fun getUnitPrice(product: Product): Double {
        return this.prices[product.name] ?: 0.0
    }
}
