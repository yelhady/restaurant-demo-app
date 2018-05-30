package com.elmenus.demo

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

object Boot extends App with RestaurantRoutes {

  implicit val system: ActorSystem = ActorSystem("RestaurantHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val restaurantActor = system.actorOf(RestaurantActor.props, "restaurantActor")

  lazy val routes: Route = restaurantRoutes

  Http().bindAndHandle(routes, "localhost", 4000)

  println(s"Server online at http://localhost:4000/")

  Await.result(system.whenTerminated, Duration.Inf)
}
