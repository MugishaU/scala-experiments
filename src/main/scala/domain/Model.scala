package domain

/*
Objectives:
- Create User
- Create Task
- Mark Task Done
- Use Future
 */

case class User (id:String, name: String){
  def rename (newName:String): User = {
    copy(name = newName)
  }
}
case class Task (id:String, userId:String, title:String, done:Boolean)

//WHAT before HOW, keep pure