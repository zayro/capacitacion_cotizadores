package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import models.{ApiResponse,Asesor, AsesorRepository}

@Singleton
class AsesorController @Inject()(asesorRepository: AsesorRepository) extends Controller {
  def get(): Action[AnyContent] = Action { request =>
    var messag = "Listado de asesores"
    val asesores = asesorRepository.get()
    if(asesores.isEmpty) {messag = "No hay datos que mostrar" }
    Ok(Json.toJson(ApiResponse(
      data = Some(Json.toJson(asesores)),
      message = Some(messag)
    )))
  }

  def getId(id: Long): Action[AnyContent] = Action { request =>
    var messag = "Listado de asesores x id: " + id
    val asesor = asesorRepository.getId(id)
    if (asesor.isEmpty) {messag= "Asesor no encontrado"}

    Ok(Json.toJson(ApiResponse(
      data = Some(Json.toJson(asesor)),
      message = Some(messag)
    )))
  }

  def create(): Action[Asesor] = Action(parse.json[Asesor]) { request =>
    var messag = "Asesor creado correctamente"
    val asesor = asesorRepository.create(request.body)
    if (asesor.isEmpty) {messag= "No se pudo crear el asesor"}

    Ok(Json.toJson(ApiResponse(
      data = Some(Json.toJson(asesor)),
      message = Some(messag)
    ))).withHeaders("Access-Control-Allow-Origin" -> "*",
      "Allow" -> "*",
      "Access-Control-Allow-Methods" -> "POST, GET, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Referrer, User-Agent, X-Auth-Token, X-Api-Key")
  }

  def update(): Action[Asesor] = Action(parse.json[Asesor]) { request =>
    var messag = "Asesor actualizado correctamente"
    val asesor = asesorRepository.update(request.body)
    if (asesor.isEmpty) { messag = "No se pudo actualizar el asesor" }

    Ok(Json.toJson(ApiResponse(
      data = Some(Json.toJson(asesor)),
      message = Some(messag)
    ))).withHeaders("Access-Control-Allow-Origin" -> "*",
      "Allow" -> "*",
      "Access-Control-Allow-Methods" -> "POST, GET, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Referrer, User-Agent, X-Auth-Token, X-Api-Key")
  }

}
