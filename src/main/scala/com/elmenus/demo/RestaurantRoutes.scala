package com.elmenus.demo

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ ExceptionHandler, Route }
import akka.http.scaladsl.server.directives.MethodDirectives.{ get, post }
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import com.elmenus.demo.RestaurantActor._
import spray.json.DeserializationException

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{ Failure, Success }

trait RestaurantRoutes extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[RestaurantRoutes])

  def restaurantActor: ActorRef

  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  private val mainRoute: Route = pathPrefix("api" / "restaurant") {
    concat(
      //#restaurant-get-post
      pathEnd {
        concat(
          get {

            //TODO: Refactor: push down this logic to RestaurantsService
            parameters('closed.as[Int].?) { closed: Option[Int] =>
              closed match {
                case None =>
                  complete {
                    (restaurantActor ? GetRestaurants).mapTo[Restaurants]
                  }

                case Some(closedValue) =>
                  val closedBoolean = closedValue match {
                    case 0 => false
                    case 1 => true
                    case _ => false
                  }
                  complete {
                    (restaurantActor ? QueryRestaurants(closedBoolean)).mapTo[Restaurants]
                  }
              }
            }

          },
          post {
            entity(as[Restaurant]) { restaurant =>
              val restaurantCreated: Future[ActionPerformed] =
                (restaurantActor ? CreateRestaurant(restaurant)).mapTo[ActionPerformed]
              onSuccess(restaurantCreated) { performed =>
                log.info("Created restaurant [{}]: {}", restaurant.uuid, performed.description)
                complete((StatusCodes.Created, performed))
              }
            }
          })
      },
      path(Segment) { uuid =>
        concat(
          get {
            val maybeRestaurant =
              (restaurantActor ? GetRestaurant(uuid)).mapTo[Option[Restaurant]]
            rejectEmptyResponse {
              complete(maybeRestaurant)
            }
          },
          put {
            entity(as[Restaurant]) { restaurant =>
              val restaurantUpdated =
                (restaurantActor ? UpdateRestaurant(restaurant)).mapTo[ActionPerformed]
              onComplete(restaurantUpdated) {
                case Success(performed) =>
                  log.info("Updated restaurant [{}]: {}", restaurant.uuid, performed.description)
                  complete((StatusCodes.OK, performed))
                case Failure(throwable) => failWith(throwable)
              }
            }
          })
      })
  }

  lazy val restaurantRoutes: Route =
    handleExceptions(exceptionHandler) {
      mainRoute
    }

  def exceptionHandler =
    ExceptionHandler {
      case _: IllegalStateException =>
        complete(StatusCodes.BadRequest, ErrorResult("Very very bad request", None))

      case _: DeserializationException =>
        complete(StatusCodes.BadRequest, ErrorResult("JSONError", None))

      case e: Exception =>
        complete(StatusCodes.InternalServerError, ErrorResult("InternalServerError", Some(Map("message" -> Seq(e.getMessage)))))
    }

}
