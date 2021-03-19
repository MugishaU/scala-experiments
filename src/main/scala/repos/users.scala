package repos

import domain.User
import scala.concurrent.Future

trait UserRepo {
  def save (id: String, name: String):Future[User]
}