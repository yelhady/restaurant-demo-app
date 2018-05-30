package com.elmenus.demo

import java.util.UUID

import akka.actor.ActorRef
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }

class RestaurantRoutesSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
  with RestaurantRoutes {

  override val restaurantActor: ActorRef = system.actorOf(RestaurantActor.props, "restaurantActor")

  lazy val routes = restaurantRoutes

  "Restaurant Routes" should {
    "return all restaurants if there is a (GET /api/restaurant) request" in {
      val request = HttpRequest(uri = "/api/restaurant")
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        val jsString = entityAs[String]
        jsString should !==("")
      }
    }
    "return only open restaurant when given the query parameter closed=0 as (GET /api/restaurant?closed=0) request" in {
      val request = HttpRequest(uri = "/api/restaurant?closed=0")
      request ~> routes ~> check {
        status shouldEqual StatusCodes.OK
        val jsString = responseAs[String]
        jsString should !==("")
        assert(jsString.contains("uuid")) //TODO: revisit testing and validation
      }
    }
    "successfully adds a new restaurant when provided a valid restaurant object" in {

      val uuid = UUID.randomUUID()
      val restaurantData = RestaurantData("Restaurant-Test", "تجربة 1", RestaurantState.Published, Some(RoutingMethod.Method1),
        None, None, None, None, None, None, None, None, None,
        onlinePayment = false, client = true, pendingInfo = false, pendingMenu = true,
        closed = false)
      val newRestaurant = Restaurant(uuid, restaurantData)

      val payload = Marshal(newRestaurant).to[MessageEntity].futureValue

      val postRequest = Post("/api/restaurant").withEntity(payload)

      postRequest ~> routes ~> check {
        status shouldEqual StatusCodes.Created
        contentType should ===(ContentTypes.`application/json`)
        assert(entityAs[String].contains("Restaurant-Test"))
        assert(entityAs[String].contains(uuid.toString))

      }

    }

    // A failing test case, couldn't get the exception handler to work here :(
    "rejects a POST request when a duplicate uuid is provided" in {

      val restaurantData = RestaurantData("Restaurant-Test", "تجربة 1", RestaurantState.Published, Some(RoutingMethod.Method1),
        None, None, None, None, None, None, None, None, None,
        onlinePayment = false, client = true, pendingInfo = false, pendingMenu = true,
        closed = false)

      val newRestaurant = Restaurant(UUID.fromString("5d81a479-add9-11e7-b988-0242ac110002"), restaurantData)

      val payload = Marshal(newRestaurant).to[MessageEntity].futureValue

      val postRequest = Post("/api/restaurant").withEntity(payload)

      postRequest ~> Route.seal(routes) ~> check {
        status shouldEqual StatusCodes.InternalServerError
        contentType shouldEqual ContentTypes.`application/json`
      }
    }
    "rejects a POST request when an invalid object is provided" in {

      val badJsonString =
        """
          |{
          | "uuid": "5dc952f9-add9-11e7-b988-0242ac110002",
          | "data": {
          | "website": "",
          | "routingMethod": null,
          | "state": "PUBLISHED",
          | "closed": false,
          | "enName": "Andrea Maadi"
          | }
          |}
        """.stripMargin

      val badRequest = Post("/api/restaurant").withEntity(ContentTypes.`application/json`, badJsonString)

      badRequest ~> Route.seal(routes) ~> check {
        status shouldEqual StatusCodes.BadRequest
      }

    }
    "successfully updates a given restaurant and return the updated one" in {

      val restaurantData = RestaurantData("Restaurant-Test-update", "تجربة 1", RestaurantState.Published, Some(RoutingMethod.Method1),
        None, None, None, None, None, None, None, None, None,
        onlinePayment = false, client = true, pendingInfo = false, pendingMenu = true,
        closed = false)

      val updatedRestaurant = Restaurant(UUID.fromString("5d81a479-add9-11e7-b988-0242ac110002"), restaurantData)

      val payload = Marshal(updatedRestaurant).to[MessageEntity].futureValue

      val putRequest = Put("/api/restaurant/" + updatedRestaurant.uuid).withEntity(payload)

      putRequest ~> routes ~> check {
        status shouldEqual StatusCodes.OK
        contentType shouldEqual ContentTypes.`application/json`
        assert(entityAs[String].contains(updatedRestaurant.uuid.toString))
      }
    }
  }

}
