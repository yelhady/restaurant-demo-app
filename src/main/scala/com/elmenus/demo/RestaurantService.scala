package com.elmenus.demo

import java.util.UUID

import spray.json._

/**
 * Created by yehia on 5/29/18.
 */

object RestaurantService extends JsonSupport {

  private val jsonString: String = """
                     |[
                     |    {
                     |        "uuid": "5d81a479-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "3al Ahwa Cafe",
                     |            "arName": "عالقهوة كافيه",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "i3qf6gym1p833di.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc8c6e0-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abdo Kofta",
                     |            "arName": "عبده كفتة",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": null,
                     |            "coverPhoto": null,
                     |            "enDescription": null,
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": null,
                     |            "youtubeLink": null,
                     |            "website": "",
                     |            "onlinePayment": true,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": false,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc8c9cb-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abo Adel",
                     |            "arName": "أبو عادل",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "sq48dmzw.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc8cbba-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abou El Sid",
                     |            "arName": "أبو السيد",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "j02aub2uvkv86w29.jpg",
                     |            "coverPhoto": "1006.jpg",
                     |            "enDescription": "Now Open in Concord Plaza in Tagamoa elkhames, Teseen Street\r\n  زورونا في فرعنا الجديدة كونكورد بلازا  في شارع التسعين،التجمع الخامس\"\r\n0225365300",
                     |            "arDescription": "",
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc945e6-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abou Ghaly",
                     |            "arName": "ابو غالي",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "ooddxqr1.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc947ed-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abou Shakra",
                     |            "arName": "أبو شقرة",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "hyezwomtvab2csor.jpg",
                     |            "coverPhoto": "1009.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "19090",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc94996-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Abo Ammar El Souri",
                     |            "arName": "أبو عمار السورى",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "dn1yv5gw.jpg",
                     |            "coverPhoto": "1012.jpg",
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc94b28-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Auntie Anne's",
                     |            "arName": "أنتي أنز",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "wxdi18cq.jpg",
                     |            "coverPhoto": "1014.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "16629",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc94cbf-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "After Eight(Closed)",
                     |            "arName": "أفتر ايت(مغلق)",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "ig22v040ud4h1tt9.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "11361",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": true
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc94e49-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Spago",
                     |            "arName": "سباجو",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "e9fo4fb8.jpg",
                     |            "coverPhoto": "irotwr8uyn0l766r.jpg",
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc94fd3-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Alain Le Notre (Closed)",
                     |            "arName": "ألان لو نوتر(مغلق)",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "dmg3r20b.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": true
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc95164-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Alto's Bistro (Closed)",
                     |            "arName": "التوز بيسترو ( مغلق)",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "wd5zwdx4.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": true
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc952f9-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Andrea Maadi",
                     |            "arName": "أندريا المعادى",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "t1502hba.jpg",
                     |            "coverPhoto": "iros6vbd4fq4obt9.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc95488-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Aqua (Closed)",
                     |            "arName": "أكوا ( مغلق)",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "0dim8mnc.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": true
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc956b8-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Arabiata El Shabrawy",
                     |            "arName": "أرابياتا الشبراوى",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "9lfqthe1.jpg",
                     |            "coverPhoto": "1054.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "16919",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc9585b-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Arabica (Closed)",
                     |            "arName": "أرابيكا (مغلق)",
                     |            "state": "UNPUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "dl1308zl.jpg",
                     |            "coverPhoto": null,
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": true
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc959f5-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Arzak Al Qawther",
                     |            "arName": "أرزاق الكوثر",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "avijv9dx.jpg",
                     |            "coverPhoto": "irouje994l81if6r.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "تعد سلسله مطاعم ارزاق الكوثر من اقوى مطاعم الطعام الشرقى فى مصر، حيث تدمج مابين اصاله الطعام الشرقى كالفول و الفلافل و الكشرى و بين الوجبات السريعه و سندوتشات اللحوم و الدجاج الشهيه. نتميز بوجود روح العائله بمطاعمنا و توافر كل ما يلائم اى ذوق من اذواق عملائنا اللذين هم اهم ما لدينا",
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc95bd2-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Asian Corner",
                     |            "arName": "الركن الاسيوي",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "t5jyfiwb.jpg",
                     |            "coverPhoto": "1065.jpg",
                     |            "enDescription": "",
                     |            "arDescription": "",
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc95d63-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Ataturk",
                     |            "arName": "أتاتورك",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "pt552i7h.jpg",
                     |            "coverPhoto": "1067.jpg",
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    },
                     |    {
                     |        "uuid": "5dc95ee4-add9-11e7-b988-0242ac110002",
                     |        "data": {
                     |            "enName": "Beano's Cafe",
                     |            "arName": "كافيه بينوس",
                     |            "state": "PUBLISHED",
                     |            "routingMethod": null,
                     |            "logo": "2b873idz.jpg",
                     |            "coverPhoto": "1083.jpg",
                     |            "enDescription": "",
                     |            "arDescription": null,
                     |            "shortNumber": "",
                     |            "facebookLink": "",
                     |            "twitterLink": "",
                     |            "youtubeLink": "",
                     |            "website": "",
                     |            "onlinePayment": false,
                     |            "client": false,
                     |            "pendingInfo": true,
                     |            "pendingMenu": true,
                     |            "closed": false
                     |        }
                     |    }
                     |]
                   """.stripMargin

  private var restaurantsDB: Set[Restaurant] = jsonString.parseJson.convertTo[Set[Restaurant]]

  def healthCheck(): Unit = {
    println(jsonString.parseJson.convertTo[Seq[Restaurant]].find(_.data.enName == "3al Ahwa Cafe").map(r => (r.uuid, r.data.enName)))
  }

  def restaurantsInitialCount(): Int = restaurantsDB.size

  def getAllRestaurants: Seq[Restaurant] = restaurantsDB.toSeq

  def getRestaurant(uuid: UUID): Option[Restaurant] = restaurantsDB.find(_.uuid.compareTo(uuid) == 0)

  def queryRestaurants(closed: Boolean): Seq[Restaurant] = restaurantsDB.filter(_.data.closed == closed).toSeq

  def addRestaurant(newRestaurant: Restaurant): Restaurant = {
    val existingRestaurant = restaurantsDB.find(_.uuid.compareTo(newRestaurant.uuid) == 0)
    existingRestaurant match {
      case Some(_) => throw new IllegalStateException("There's a restaurant with the same UUID.")
      case _ =>
        restaurantsDB += newRestaurant
        restaurantsDB.find(_.uuid == newRestaurant.uuid).get
    }
  }

  def updateRestaurant(restaurant: Restaurant): Restaurant = {
    restaurantsDB.find(_.uuid == restaurant.uuid) foreach { res => restaurantsDB -= res }
    restaurantsDB += restaurant
    restaurantsDB.find(_.uuid == restaurant.uuid).get
  }

}
