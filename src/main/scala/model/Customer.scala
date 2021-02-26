package model

import java.util.Date

final case class Name (
                      title: String,
                      forename: String,
                      surname: String
                      )

final case class Address (
                         line1: String,
                         line2: Option[String],
                         county: Option[String],
                         city: String,
                         postcode: String
                         )

final case class PaymentInfo (
                             cardNumber: Int,
                             expiryDate: String, //todo datetime??
                             securityCOde: Int
                             )

final case class Customer (
                          id: String, //todo UUID?
                          name: Name,
                          address: Address,
                          paymentInfo: PaymentInfo,
                          shoppingCart: ShoppingCart,
                          lastActive: Date //todo Look into java.util.Date???
                          )