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
    val articleById = articleExistsById(id)
    if(!articleById.isEmpty) {
      Ok(Json.toJson(articleById))
    } else {
      NotFound("No existe registro")
    }
  }

  def articlesGetAllActive() = Action { implicit request: Request[AnyContent] =>
    db.withConnection { conn =>
      val statement = conn.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM articles where isactive = true ")
      val articlesActive = Iterator.continually(resultSet).takeWhile(_.next()).map { rs =>
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val idType = rs.getInt("idType")
        val isActive = rs.getBoolean("isActive")
        Articles(id, name, idType, isActive) // Crea una instancia del (case class)
      }.toList
      println("articlesActive: " + articlesActive)
      Ok(Json.toJson(articlesActive))
    }
  }

  def addArticle = Action { request =>
    db.withConnection { conn =>
      val jsVal: JsValue = Json.parse(request.body.asJson.get.toString())
      val command = "INSERT INTO public.articles (name, idtype) VALUES(?,?)";
      val addstmt = conn.prepareStatement(command)
      addstmt.setObject(1, (jsVal \ "name").as[String]);
      addstmt.setObject(2, (jsVal \ "idType").as[Int]);
      addstmt.execute();
      println("Article created");
      Created(jsVal)
    }
  }

  def updateArticle(id: Long) = Action { request =>
    db.withConnection { conn =>
      if(!articleExistsById(id).isEmpty) {
        val jsVal: JsValue = Json.parse(request.body.asJson.get.toString())
        val command = "UPDATE public.articles SET name=?, idtype=?, isactive=? where id=? ";
        val updateStmt = conn.prepareStatement(command)
        updateStmt.setObject(1, (jsVal \ "name").as[String]);
        updateStmt.setObject(2, (jsVal \ "idType").as[Int]);
        updateStmt.setObject(3, (jsVal \ "isActive").as[Boolean]);
        updateStmt.setObject(4, id);
        updateStmt.execute();
        println("Article updated");
        Created(Json.toJson(articleExistsById(id)))
      } else {
        NotFound("No existe registro para actualizar")
      }
    }
  }

  def deleteArticle(id: Long) = Action { request =>
    db.withConnection { conn =>
      if(!articleExistsById(id).isEmpty) {
        val command = "DELETE FROM public.articles where id=? ";
        val updateStmt = conn.prepareStatement(command)
        updateStmt.setObject(1, id);
        updateStmt.execute();
        println("Article delete");
        Ok("Article delete")
      } else {
        NotFound("No existe registro para eliminar")
      }
    }
  }

  def articleExistsById(id: Long): List[Articles] = {
    db.withConnection { conn =>
      val statement = conn.createStatement()
      val resultSet = statement.executeQuery(s"SELECT * FROM articles where id = ${id} ")
      val articleById = Iterator.continually(resultSet).takeWhile(_.next()).map { rs =>
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val idType = rs.getInt("idType")
        val isActive = rs.getBoolean("isActive")
        Articles(id, name, idType, isActive) // Crea una instancia del (case class)
      }.toList
      articleById
    }
  }

}
