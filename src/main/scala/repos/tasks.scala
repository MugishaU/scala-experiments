package repos

import domain.Task
import scala.concurrent.Future

trait TaskRepo {
  def save (id:String, userId:String, title:String, done:Boolean): Future[Task]
  def update(task: Task): Future[Task]
  def get (id: String): Future[Option[Task]] //Distinguish between not found & other errors
}