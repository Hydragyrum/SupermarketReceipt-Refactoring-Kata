package dojo.supermarket.model

import java.util.HashMap

class FakeCatalog : SupermarketCatalog {
    private val products = HashMap<String, Product>()
    private val prices = HashMap<String, Double>()

    override fun addProduct(product: Product, price: Double) {
        this.products[product.name] = product
        this.prices[product.name] = price
    }

    override fun getUnitPrice(p: Product): Double {
        return this.prices[p.name]!!
    }
}
