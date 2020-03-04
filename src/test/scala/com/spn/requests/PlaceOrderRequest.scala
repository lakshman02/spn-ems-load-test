package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PlaceOrderRequest {

  val placeOrder= exec(http("Place Order Request")
    .post(Config.app_url + Config.PLACE_ORDER_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
"serviceID": "${skuORQuickCode}",
"priceCharged": 159.00,
"makeAutoPayment": false,
"paymentmethodInfo": {
"label": "Coupon",
"amount": 159.00
},
"serviceType": "${serviceType}",
"channelPartnerID": "${channelPartnerID}",
"startDate": "1560508606457"
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
