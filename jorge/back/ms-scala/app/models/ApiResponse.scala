package models

import play.api.libs.json.{JsValue, Json}

case class ApiResponse(error: Option[Boolean] = None, message: Option[String] = None, data: Option[JsValue] = None)

object ApiResponse {
  implicit val format = Json.format[ApiResponse]
}
