package logic

import domain.Task
import repos.TaskRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Tasks {
  def createTask (userId: String, title: String): Future[Task]
  def markDone (taskId: String): Future[Task]
}

class ProdTask (taskRepo: TaskRepo) extends Tasks {
  def createTask (userId: String, title: String): Future[Task] = ???
  def markDone (taskId: String): Future[Task] = {
    val maybeTask =  taskRepo.get(taskId).map{markTaskAsDone}
    maybeTask.flatMap(saveTask)
  }

  def markTaskAsDone(taskOption: Option[Task]): Option[Task] = {
    taskOption.map{task: Task => task.copy(done=true)}
  }

  def saveTask(taskOption: Option[Task]): Future[Task] = {
//    taskOption.map{task => taskRepo.update(task)}
    taskOption match {
      case Some(task) => taskRepo.update(task)
      case None => Future.failed(new Exception("Task does not exist"))
    }
  }

  //-----------------------------

  def markDone2 (taskId: String): Future[Task] = {
    for {
      maybeTask <- taskRepo.get(taskId)
      updatedTask = maybeTask.map{task: Task => task.copy(done=true)}
      saved <-  updatedTask match {
        case Some(task) => taskRepo.update(task)
        case None => Future.failed(new Exception("Task does not exist"))
      }
    } yield(saved)
  }
}

