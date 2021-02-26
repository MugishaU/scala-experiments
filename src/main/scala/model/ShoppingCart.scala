package model

final case class Item (
                        name: String,
                        category: String,
                        price: Int,
                        essentialStatus: Boolean
                      )

final case class ItemSelection (item: Item, quantity: Int)

final case class ShoppingCart (
                              id: String, //todo UUID???
                              customerId: String,
                              itemSelections: Option[List[ItemSelection]],
                              deliverySlot: Option[DeliverySlot],
                              deliveryCost: Option[Int],
                              deliveryStatus: String, // todo should this be its own case class?
                              totalCost: Int
                              )