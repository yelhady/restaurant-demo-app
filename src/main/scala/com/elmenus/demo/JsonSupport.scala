package com.elmenus.demo

import java.util.UUID

import com.elmenus.demo.RestaurantActor.{ ActionPerformed, ErrorResult }
import com.elmenus.demo.RestaurantState.RestaurantState
import com.elmenus.demo.RoutingMethod.RoutingMethod
import spray.json.{ JsString, JsValue, JsonFormat }

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.json._

trait JsonSupport extends DefaultJsonProtocol with SprayJsonSupport with NullOptions {

  implicit object UUIDFormat extends JsonFormat[UUID] {
    override def write(uuid: UUID): JsValue = JsString(uuid.toString)
    override def read(jsonValue: JsValue): UUID = jsonValue match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _ => deserializationError("UUID expected.")
    }
  }

  implicit object RestaurantStateFormat extends JsonFormat[RestaurantState] {
    override def write(obj: RestaurantState): JsValue = JsString(obj.toString)
    override def read(json: JsValue): RestaurantState = json match {
      case JsString(state) => RestaurantState.withName(state)
      case _ => deserializationError("String expected.")
    }
  }

  implicit object RoutingMethodFormat extends JsonFormat[RoutingMethod] {
    override def write(obj: RoutingMethod): JsValue = JsString(obj.toString)
    override def read(json: JsValue): RoutingMethod = json match {
      case JsString(routingMethod) => RoutingMethod.withName(routingMethod)
      case _ => deserializationError("String expected.")
    }
  }

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
  implicit val errorResultFormat = jsonFormat2(ErrorResult)

  implicit val restaurantDataFormat = jsonFormat18(RestaurantData)
  implicit val restaurantFormat = jsonFormat2(Restaurant)
  implicit val resturantsFormat = jsonFormat1(Restaurants)

}
