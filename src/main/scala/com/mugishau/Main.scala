//TODO (not really) LINK: https://blog.buildo.io/monad-transformers-for-the-working-programmer-aa7e981190e7
package com.mugishau

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.data.OptionT, cats.implicits._


case class FutOpt[A](value: Future[Option[A]]) {
  private def helper[B](opt: Option[A],f: A => FutOpt[B]) = {
    opt match {
      case Some(a) => f(a).value
      case None => Future.successful(None)
    }
  }


  def map[B](f: A => B): FutOpt[B] =
    FutOpt(value.map(optA => optA.map(f)))


  def flatMap[B](f: A => FutOpt[B]): FutOpt[B] =
    FutOpt(value.flatMap(opt => helper(opt, f)))
}

object Tutorial {

  class User
  class Address

  def findUserById(id: Long): Future[Option[User]] = ???

  def findAddressByUser(user: User): Future[Option[Address]] = ???

  def findAddressByUserId(id: Long): Future[Option[Address]] = {
    (for {
      user <- OptionT(findUserById(id))
      address <- OptionT(findAddressByUser(user))
    } yield address).value
  }
}
