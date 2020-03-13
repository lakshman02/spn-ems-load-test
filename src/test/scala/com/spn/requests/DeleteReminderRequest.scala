package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteReminderRequest {

  val deleteReminderRequest = exec(http("Delete Reminder Request")
    .post(Config.app_url + Config.DELETE_REMINDER_URL)
    .headers(Config.sentHeadersNew)
      .check(status is 200)
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
