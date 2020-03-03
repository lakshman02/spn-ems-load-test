package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteXdrRequest {
val sentHeaders=Map("Authorization" -> "${RESP_AUTH_TOKEN}",
  "x-via-device" -> "true")

  val deleteXdrRequest = exec(http("DeleteXdr Request")
    .post(Config.app_url + Config.DELETE_XDR_URL)
    .queryParam("assetId", "${contentId}")
      .headers(sentHeaders)
      .check(status is 200)
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
