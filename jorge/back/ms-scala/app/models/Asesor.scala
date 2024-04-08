package models

import anorm.{Macro, RowParser, SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import play.api.libs.json.{Json, OFormat}


case class Asesor(id: Long, names: String, email: String, country: String)
object Asesor {
  implicit val format: OFormat[Asesor] = Json.format[Asesor]
  val parser: RowParser[Asesor] = Macro.namedParser[Asesor]
}
@Singleton
class AsesorRepository @Inject()(db: Database) {

  def get(): List[Asesor] = {
    db.withConnection { implicit c =>
      SQL"select * from cotizadores.asesores".as(Asesor.parser.*)
    }
  }
  def getId(id: Long): Option[Asesor] = {
    db.withConnection { implicit c =>
      SQL"select * from cotizadores.asesores where id = $id".as(Asesor.parser.singleOpt)
    }
  }
  def create(asesor: Asesor):  Option[Asesor] = {
    db.withConnection { implicit c =>
      SQL"""
          insert into cotizadores.asesores (names, email, country)
          values (${asesor.names}, ${asesor.email}, ${asesor.country})
          returning id,names, email, country
          """.as(Asesor.parser.singleOpt)
    }
  }

  def update(asesor: Asesor): Option[Asesor] = {
    db.withConnection { implicit c =>
      SQL"""
        update cotizadores.asesores set
          names = ${asesor.names},
          email = ${asesor.email},
          country = ${asesor.country}
        where id = ${asesor.id}
        returning id,names, email, country
        """.as(Asesor.parser.singleOpt)
    }
  }

}
