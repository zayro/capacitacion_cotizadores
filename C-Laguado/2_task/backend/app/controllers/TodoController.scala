package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

import scala.collection.mutable

case class TodoListItem(id: Long, description: String, isItDone: Boolean)
case class NewTodoListItem(description: String)

@Singleton
class TodoController  @Inject() extends Controller {

  private val todoList = new mutable.ListBuffer[TodoListItem]()
  todoList += TodoListItem(1, "test", true)
  todoList += TodoListItem(2, "some other value", false)

  implicit val todoListJson = Json.format[TodoListItem]
  implicit val newTodoListJson = Json.format[NewTodoListItem]

  def getAll: Action[AnyContent] = Action {
    if (todoList.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(todoList))
    }
  }

  def getById(itemId: Long): Action[AnyContent] = Action {
    val foundItem = todoList.find(_.id == itemId)
    foundItem match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound
    }
  }
}