package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

import scala.collection.mutable

case class TodoListItem(id: Long, description: String, isItDone: Boolean)

@Singleton
class TodoController  @Inject() extends Controller {

  private val todoList = new mutable.ListBuffer[TodoListItem]()
  todoList += TodoListItem(1, "test", true)
  todoList += TodoListItem(2, "some other value", false)

  implicit val todoListJson = Json.format[TodoListItem]

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


  // Método para manejar la solicitud POST
  def create = Action { implicit request: Request[AnyContent] =>
    // Obtener los datos del cuerpo de la solicitud
    val datos = request.body.asFormUrlEncoded

    datos.map { args =>
      // Manejar los datos de la solicitud POST
      val parametro1 = args("parametro1").headOption.getOrElse("")
      val parametro2 = args("parametro2").headOption.getOrElse("")

      // Realizar alguna lógica con los parámetros recibidos
      // Por ejemplo, puedes simplemente devolver una respuesta con los parámetros recibidos
      Ok(s"Parámetro 1: $parametro1, Parámetro 2: $parametro2")
    }.getOrElse {
      // En caso de que no se puedan obtener los datos del cuerpo de la solicitud
      BadRequest("Error: Los datos no fueron enviados correctamente")
    }
  }



}