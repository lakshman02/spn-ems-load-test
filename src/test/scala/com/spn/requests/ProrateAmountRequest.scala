package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ProrateAmountRequest {

  val prorateAmountRequest = exec(http("Prorate Amount Request")
    .post(Config.app_url + Config.PRORATE_AMOUNT_URL)
    .headers(Config.sentHeaders)
    .body(StringBody("""{
                       |"serviceID": "${service_id_old}",
                       |"channelPartnerID": "${single_channel_partner_id}",
                       |"timestamp": "${getDateTime}"
                       |}""".stripMargin)).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}
