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
/*
  def addNewItem() = Action { implicit request =>
    val content = request.body
    val jsonObject = content.asJson
    val todoListItem: Option[NewTodoListItem] =
      jsonObject.flatMap(
        Json.fromJson[NewTodoListItem](_).asOpt
      )
  }

def addNewItem() = Action { implicit request =>
  // existing code
  val content = request.body
  val jsonObject = content.asJson
  todoListItem match {
    case Some(newItem) =>
      val nextId = todoList.map(_.id).max + 1
      val toBeAdded = TodoListItem(nextId, newItem.description, false)
      todoList += toBeAdded
      Created(Json.toJson(toBeAdded))
    case None =>
      BadRequest
  }
}
*/

  def addNewItem2 = Action { request =>
    val json = request.body.asJson.get
    val stock = json.as[NewTodoListItem]
    println("json : " + json)
    println("stock : " + stock)
    Ok("Ok....")
  }

  def addNewItem = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val description = args("description")
      println("description : " + description)
      //???
    }.getOrElse("oooooo")
    println("postVals : " + postVals)
    Ok("Ok....")
  }

}