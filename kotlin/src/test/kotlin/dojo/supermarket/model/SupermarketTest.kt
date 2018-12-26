package dojo.supermarket.model

import dojo.supermarket.ReceiptPrinter
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class SupermarketTest {

    @Test
    fun `Test receipt with standard pricing and 1 item`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 2.5)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              4.98
              1.99 * 2.500

            Total:                              4.98
        """.trimIndent()
    }

    @Test
    fun `Test receipt with discount pricing and 1 item`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(toothbrush, 1.0)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            toothbrush                          0.99
            10.0% off(toothbrush)              -0.10

            Total:                              0.89
        """.trimIndent()
    }

    @Test
    fun `Test receipt with discount pricing and 2 toothbrushes`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(toothbrush, 2.0)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            toothbrush                          1.98
              0.99 * 2
            10.0% off(toothbrush)              -0.20

            Total:                              1.78
        """.trimIndent()
    }

    @Test
    fun `Test receipt with three for 2 discount pricing and 2 toothbrushes`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(toothbrush, 2.0)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            toothbrush                          1.98
              0.99 * 2

            Total:                              1.98
        """.trimIndent()
    }

    @Test
    fun `Test receipt with three for 2 discount pricing and 3 toothbrushes`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(toothbrush, 3.0)

        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            toothbrush                          2.97
              0.99 * 3
            3 for 2(toothbrush)                -0.99

            Total:                              1.98
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 2 for x discount pricing and 1kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItem(apples)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, apples, 3.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              1.99

            Total:                              1.99
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 2 for x discount pricing and 2kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 2.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, apples, 3.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              3.98
              1.99 * 2.000
            2 for 3.0(apples)                  -0.98

            Total:                              3.00
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 2 for x discount pricing and 5kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 5.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, apples, 3.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              9.95
              1.99 * 5.000
            2 for 3.0(apples)                  -1.96

            Total:                              7.99
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 5 for x discount pricing and 4kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 4.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, apples, 5.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              7.96
              1.99 * 4.000

            Total:                              7.96
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 5 for x discount pricing and 5kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 5.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, apples, 5.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              9.95
              1.99 * 5.000
            5 for 5.0(apples)                  -4.95

            Total:                              5.00
        """.trimIndent()
    }

    @Test
    fun `Test receipt with 5 for x discount pricing and 11kg of apples`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 11.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, apples, 5.00)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                             21.89
              1.99 * 11.000
            5 for 5.0(apples)                  -9.90

            Total:                             11.99
        """.trimIndent()
    }

    @Test
    fun `Test receipt with standard pricing added in two times`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 1.0)
        cart.addItemQuantity(apples, 2.0)


        val teller = Teller(catalog)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              1.99
            apples                              3.98
              1.99 * 2.000

            Total:                              5.97
        """.trimIndent()
    }

    @Test
    fun `Test receipt with discount pricing added in two times`() {
        // Given
        val catalog = FakeCatalog()
        val toothbrush = Product("toothbrush", ProductUnit.Each)
        catalog.addProduct(toothbrush, 0.99)
        val apples = Product("apples", ProductUnit.Kilo)
        catalog.addProduct(apples, 1.99)

        val cart = ShoppingCart()
        cart.addItemQuantity(apples, 1.0)
        cart.addItemQuantity(apples, 2.0)


        val teller = Teller(catalog)
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, apples, 1.0)

        val receipt = teller.checksOutArticlesFrom(cart)

        val printer = ReceiptPrinter()

        // When
        val printedReceipt = printer.printReceipt(receipt)

        //Then
        printedReceipt `should be equal to`
        """
            apples                              1.99
            apples                              3.98
              1.99 * 2.000
            3 for 2(apples)                    -1.99

            Total:                              3.98
        """.trimIndent()
    }


}
