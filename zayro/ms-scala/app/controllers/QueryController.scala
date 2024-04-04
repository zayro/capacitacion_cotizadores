package controllers


import play.api.mvc._
import play.api.db._
import play.libs.Json

import javax.inject._

@Singleton
class QueryController @Inject()(db: Database) extends Controller {

  def connect() = Action { implicit request: Request[AnyContent] =>
    db.withConnection { conn =>
      // Aquí puedes realizar operaciones en la base de datos usando `conn`
      // Por ejemplo: val statement = conn.createStatement()
      // Más comúnmente, usarás bibliotecas como Slick para interactuar con la base de datos.
      Ok("Conexión exitosa a la base de datos")
    }
  }

  def country() = Action { implicit request: Request[AnyContent] =>
    db.withConnection { conn =>
      val statement = conn.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM country")
      // Procesar el resultado del conjunto de resultados
      val countries = Iterator.continually(resultSet).takeWhile(_.next()).map { rs =>
        // Obtener los datos de cada fila del ResultSet y construir un objeto Country
        val code = rs.getInt("code")
        val name = rs.getString("name")
        // Supongamos que tienes una case class Country(id: Int, name: String)

        print(code)

      }.toList

      // Ahora tienes la lista de países (countries)
      // Puedes devolverla, por ejemplo, en formato JSON
      Ok("Query Exitosa")
    }
  }




}
