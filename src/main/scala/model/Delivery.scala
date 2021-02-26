package model

import java.util.Date

final case class DeliverySlot (date: Date, hour: Int) //todo Look into java.util.Date

final case class DeliverySchedule (deliverySlots: List[DeliverySlot]) //todo is a list the best way to do this?

