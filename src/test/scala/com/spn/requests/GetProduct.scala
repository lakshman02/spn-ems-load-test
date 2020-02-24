package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object GetProduct {

  val sentHeaders = Map("Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")
  val GetProduct  = exec(http("Get_Product Request")
    .post(Config.app_url + Config.GET_PRODUCTS_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
   "channelPartnerID": "${channelPartnerID}",
  "dmaID": "IN",
  "salesChannel": "web",
  "offerType": "Existing Customer",
  "languageCode": "en_US"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK")))
}