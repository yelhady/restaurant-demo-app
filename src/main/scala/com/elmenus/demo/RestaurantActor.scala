package com.elmenus.demo

import java.util.UUID
import akka.actor.{ Actor, ActorLogging, Props }

object RestaurantState extends Enumeration {
  type RestaurantState = Value
  val Published = Value("PUBLISHED")
  val unPublished = Value("UNPUBLISHED")
}

object RoutingMethod extends Enumeration {
  type RoutingMethod = Value
  val Method1 = Value("METHOD_I")
  val Method2 = Value("METHOD_II")
}

import RestaurantState._
import RoutingMethod._

final case class RestaurantData(enName: String, arName: String, state: RestaurantState, routingMethod: Option[RoutingMethod],
  logo: Option[String], coverPhoto: Option[String], enDescription: Option[String], arDescription: Option[String],
  shortNumber: Option[String], facebookLink: Option[String], twitterLink: Option[String], youtubeLink: Option[String],
  website: Option[String], onlinePayment: Boolean, client: Boolean,
  pendingInfo: Boolean, pendingMenu: Boolean, closed: Boolean)
final case class Restaurant(uuid: UUID, data: RestaurantData)
final case class Restaurants(restaurants: Seq[Restaurant])

object RestaurantActor {
  final case class ActionPerformed(description: String)

  case class ErrorResult(errorType: String, errors: Option[Map[String, Seq[String]]])
  final case object GetRestaurants

  final case class GetRestaurant(uuid: String)
  final case class QueryRestaurants(closed: Boolean) // need to think of a more generic solution, maybe a parser for inputs.
  final case class CreateRestaurant(restaurant: Restaurant)
  final case class UpdateRestaurant(updatedVersion: Restaurant)

  def props: Props = Props[RestaurantActor]
}

class RestaurantActor extends Actor with ActorLogging {
  import RestaurantActor._

  def receive: Receive = {
    case GetRestaurants =>
      sender() ! Restaurants(RestaurantService.getAllRestaurants)

    case GetRestaurant(uuidString) =>
      sender() ! RestaurantService.getRestaurant(UUID.fromString(uuidString))

    case QueryRestaurants(closed) =>
      sender() ! Restaurants(RestaurantService.queryRestaurants(closed))

    case CreateRestaurant(restaurant) =>
      val newRestaurant = RestaurantService.addRestaurant(restaurant)
      sender() ! ActionPerformed(s"New restaurant added (${newRestaurant.uuid} ${newRestaurant.data.enName})")

    case UpdateRestaurant(restaurant) =>
      val updatedResult = RestaurantService.updateRestaurant(restaurant)
      sender() ! ActionPerformed(s"Updated restaurant (${updatedResult.uuid} ${updatedResult.data.enName})")
  }
}