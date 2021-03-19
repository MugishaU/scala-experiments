package domain

import logic.ProdUser
import logic.IDGen
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import repos.UserRepo

import scala.concurrent.Future

class UserLogicSpec extends AnyFlatSpec with Matchers with ScalaFutures {
  "The ProdUser object" should "create a new user" in {
    val idGen = new DummyIdGen("123")
    val service = new ProdUser(new DummyUserRepo, idGen)
    val saved = service.createUser("Mugisha").futureValue

    saved.name shouldEqual "Mugisha"
    saved.id shouldEqual "123"
    //Cannot test UUID as is not pure
    //Create trait of idGenerator
  }
}

class DummyUserRepo extends UserRepo{
  def save(id: String, name: String): Future[User] = {
    Future.successful(User(id,name))
  }
}

class DummyIdGen (value: String) extends IDGen {
  def generate: String = {
    value
  }
}