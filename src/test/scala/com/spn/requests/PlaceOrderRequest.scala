package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//"Get Menu" Request
object PlaceOrderRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6IkxvYWQiLCJsYXN0TmFtZSI6IlRlc3QiLCJtYWlsIjoiam1ldGVyLXRlc3QtaW5kd2VzLXVzZXItMzY1ODM2QG1haWxpbmF0b3IuY29tIiwic2Vzc2lvbkNyZWF0aW9uVGltZSI6IjIwMjAtMDItMTdUMTE6MDA6MTAuODMxWiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImlhdCI6MTU4MTkzNzIxMCwiZXhwIjoxNjE3NTA3MzcwfQ.JovNKWW2N6So9Ef-tc4DMKlIzi4VD3Cj0ziyocG_3iE",
    "x-via-device" -> "true")

  val placeOrder= exec(http("Place Order Request")
    .post(Config.app_url + Config.PLACE_ORDER_URL)
    .headers(sentHeaders)
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
