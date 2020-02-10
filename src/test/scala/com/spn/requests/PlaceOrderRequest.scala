package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//"Get Menu" Request
object PlaceOrderRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAyMDUxMDAxMTAxNTE4NiIsInRva2VuIjoiQjA3SS0wVEc5LVZZNUotUDdrSS1Kajh4LXJDU0MtUGYiLCJleHBpcmF0aW9uTW9tZW50IjoiMjAyMS0wMi0wNFQxMDowMzoxMS40OThaIiwiaXNQcm9maWxlQ29tcGxldGUiOmZhbHNlLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0wNVQxMDowMzoxMS40OThaIiwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImxhc3ROYW1lIjoiIiwiZW1haWwiOiIiLCJpYXQiOjE1ODA4OTY5OTIsImV4cCI6MTU4MTQ5NjYzMn0.uYyy50kNF3nw5uTSJl1rmDHeM4Ec_I3YlPJRowOL3As",
    "x-via-device" -> "true")

  val placeOrder= exec(http("Place Order Request")
    .post(Config.app_url + Config.PLACE_ORDER_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
"serviceID": "1mn_99_india",
"priceCharged": 159.00,
"makeAutoPayment": false,
"paymentmethodInfo": {
"label": "Coupon",
"amount": 159.00
},
"serviceType": "PACKAGE",
"channelPartnerID": "MSMIND",
"startDate": "1560508606457"
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
