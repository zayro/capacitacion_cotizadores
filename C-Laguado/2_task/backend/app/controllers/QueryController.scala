package controllers

import javax.inject._
import play.api.mvc._
import play.api.db._
import play.api.libs.json.{JsValue, Json, Writes}

case class Articles(id: Long, name: String, idType: Long, isActive: Boolean)

object Articles {
  implicit val articlesWrites: Writes[Articles] = new Writes[Articles] {
    def writes(article: Articles): JsValue = Json.obj(
      "id" -> article.id,
      "name" -> article.name,
      "idType" -> article.idType,
      "isActive" -> article.isActive
    )
  }
}

@Singleton
class QueryController @Inject()(db: Database) extends Controller {

  def connect(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      db.withConnection {
        conn =>
          //
          Ok("Conexión exitosa a la BD")
      }
  }

  def articlesGetAll() = Action { implicit request: Request[AnyContent] =>
    db.withConnection { conn =>
      val statement = conn.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM articles")
      // Procesar el resultado del conjunto de resultados
      val articlesResult = Iterator.continually(resultSet).takeWhile(_.next()).map { rs =>
          // Obtener los datos de cada fila del ResultSet y construir un objeto
          val id = rs.getInt("id")
          val name = rs.getString("name")
          val idType = rs.getInt("idType")
          val isActive = rs.getBoolean("isActive")
          println(s" ${id} - ${name} - ${idType} - ${isActive} ")
          Articles(id, name, idType, isActive) // Crea una instancia del (case class)
      }.toList
      println("articlesResult: " + articlesResult)
      Ok(Json.toJson(articlesResult))
    }
  }

  def articlesById(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { conn =>
      val statement = conn.createStatement()
      val resultSet = statement.executeQuery(s"SELECT * FROM articles where id = ${id} ")
      // Procesar el resultado del conjunto de resultados
      val articlesResultById = Iterator.continually(resultSet).takeWhile(_.next()).map { rs =>
        // Obtener los datos de cada fila del ResultSet y construir un objeto
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val idType = rs.getInt("idType")
        val isActive = rs.getBoolean("isActive")
        println(s" ${id} - ${name} - ${idType} - ${isActive} ")
        Articles(id, name, idType, isActive) // Crea una instancia del (case class)
      }.toList
      println("articlesResultById: " + articlesResultById)
      Ok(Json.toJson(articlesResultById))
    }
  }

}
