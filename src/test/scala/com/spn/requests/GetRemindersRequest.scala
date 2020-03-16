package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetRemindersRequest {

  val getRemindersRequest = exec(http("Get Reminders Request")
    .get(Config.app_url + Config.GET_REMINDER_URL)
    .headers(Config.sentHeadersNew)
      .check(status is 200)
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
