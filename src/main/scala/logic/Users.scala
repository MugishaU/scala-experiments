package logic

import java.util.UUID

import scala.concurrent.Future
import domain.User
import repos.UserRepo

trait Users {
  def createUser (name:String):Future[User]
}

class ProdUser (repo: UserRepo, idGen: IDGen) extends Users {
  def createUser(name: String): Future[User] = repo.save(idGen.generate,name)
}