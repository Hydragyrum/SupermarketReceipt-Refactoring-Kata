package dojo.supermarket.model

data class Offer(internal var offerType: SpecialOfferType, internal val product: Product, internal var argument: Double)
