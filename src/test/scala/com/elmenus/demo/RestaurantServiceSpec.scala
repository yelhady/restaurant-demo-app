package com.elmenus.demo

import java.util.UUID

import org.scalatest.FlatSpec

/**
 * Created by yehia on 5/29/18.
 */

class RestaurantServiceSpec extends FlatSpec {

  "The Restaurant Service" should " return all restaurants " in {
    assert(RestaurantService.getAllRestaurants.size == RestaurantService.restaurantsInitialCount())
  }

  it should " list all open restaurants " in {
    val openRestaurants = RestaurantService.queryRestaurants(closed = false)
    val validAllOpenRestaurants = RestaurantService.getAllRestaurants.filter(_.data.closed == false)
    assert(openRestaurants.nonEmpty) // Not sure if this is a valid test or not
    assert(openRestaurants.size == validAllOpenRestaurants.size)
  }

  it should " add a new restaurant and return the new created restaurant" in {

    val uuid = UUID.randomUUID()
    val restaurantData = RestaurantData("Restaurant-Test", "تجربة 1", RestaurantState.Published, Some(RoutingMethod.Method1),
      None, None, None, None, None, None, None, None, None,
      onlinePayment = false, client = true, pendingInfo = false, pendingMenu = true,
      closed = false)
    val newRestaurant = Restaurant(uuid, restaurantData)

    val addedRestaurant = RestaurantService.addRestaurant(newRestaurant)

    assert(addedRestaurant.uuid.compareTo(uuid) == 0)
  }

  it should " throw an exception when a new restaurant is added with an existing uuid " in {

    assertThrows[IllegalStateException] {
      val sampleRestaurant = RestaurantService.queryRestaurants(closed = false).head
      RestaurantService.addRestaurant(sampleRestaurant)
    }

  }

  it should "update an existing restaurant" in {

    val existingRestaurant = RestaurantService.getAllRestaurants.head
    val modifiedRestaurantData = existingRestaurant.data.copy(enName = "test-update-name")

    val updatedRestaurant = existingRestaurant.copy(data = modifiedRestaurantData)
    val updateResult = RestaurantService.updateRestaurant(updatedRestaurant)

    assert(updateResult.data.enName.compareTo(modifiedRestaurantData.enName) == 0)
    assert(updateResult.data.enName.compareTo(existingRestaurant.data.enName) != 0)
  }

}
