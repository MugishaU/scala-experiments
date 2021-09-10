package com.mugishau
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import com.amazonaws.services.dynamodbv2.document.ItemUtils

import java.util
import scala.collection.immutable.HashMap
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}

object Main extends App {
  val ddb = AmazonDynamoDBClientBuilder.defaultClient

  val keyToGet = HashMap("id" -> new AttributeValue("1")).asJava

  val request =
    new GetItemRequest()
      .withKey(keyToGet)
      .withTableName("sunnymart-inventory")

  val tryr = Try(Option(ddb.getItem(request).getItem))

  tryr match {
//    case Failure(exception: AmazonServiceException) => println(exception.getErrorMessage)
    case Failure(error: AmazonServiceException) =>
      println(error.getErrorMessage)
    case Failure(_)           => println("Unknown Error")
    case Success(None)        => println("No Item")
    case Success(Some(value)) => println(ItemUtils.toItem(value).toJSON)
  }

//  try {
//    val returned_item = ddb.getItem(request).getItem
//    if (returned_item != null) {
//      println(ItemUtils.toItem(returned_item).toJSON)
//    } else println("No item found with the key")
//  } catch {
//    case e: AmazonServiceException =>
//      System.err.println(e.getErrorMessage)
//      System.exit(1)
//  }
}
