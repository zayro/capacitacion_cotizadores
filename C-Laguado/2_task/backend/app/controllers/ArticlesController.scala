package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.{Json, _}

import scala.collection.mutable

case class ArticlesItem(id: Long, name: String, idType: Long, isActive: Boolean)

@Singleton
class ArticlesController  @Inject() extends Controller {

  private val articles = new mutable.ListBuffer[ArticlesItem]()
  articles += ArticlesItem(1, "Article 1", 1, true)
  articles += ArticlesItem(2, "Article 2", 2, false)
  articles += ArticlesItem(3, "Article 3", 1, true)
  articles += ArticlesItem(4, "Article 4", 1, false)

  implicit val articlesJson = Json.format[ArticlesItem]

  def getAll: Action[AnyContent] = Action {
    if (articles.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(articles))
    }
  }

  def getById(itemId: Long): Action[AnyContent] = Action {
    val foundItem = articles.find( _.id == itemId )
    foundItem match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound
    }
  }

  def getAllActive: Action[AnyContent] = Action {
    val articlesActive = articles.filter( elem => elem.isActive)
    if (articlesActive.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(articlesActive))
    }
  }

  def getByIdType(idType: Long): Action[AnyContent] = Action {
    val articlesByIdType = articles.filter(elem => elem.idType == idType)
    if (articlesByIdType.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(articlesByIdType))
    }
  }

}
