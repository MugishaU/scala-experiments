package model

final case class InventoryEntry (item: Item, quantity: Int)

final case class Inventory(stock: List[InventoryEntry]) //todo best way to do this?