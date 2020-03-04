package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ChangeServiceRequest {

  val changeServiceRequest = exec(http("Change Service Request")
    .post(Config.app_url + Config.CHANGE_SERVICE_URL)
    .headers(Config.sentHeaders)
      .body(StringBody(
        """{
          |"oldServiceID": "${service_id_old}",
          |"newServiceID" : "${service_id_new}",
          |"channelPartnerID" : "${single_channel_partner_id}",
          |"timestamp" : "${getDateTime}"
          |}""".stripMargin)).asJson
      .check(status is 200)
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
