package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object IsCustomerEligibleForFreeTrialRequest {

  val checkCustomerEligibleForFreeTrial = exec(http("Check if the user is eligible for free trial")
    .get(Config.app_url + Config.CHECK_FREE_TRIAL)
    .queryParam("timestamp", "${getDateTime}")
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
