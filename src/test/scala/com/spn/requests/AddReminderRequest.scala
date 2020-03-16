package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddReminderRequest {

  val addReminder= exec(http("Add Reminder Request")
    .post(Config.app_url + Config.ADD_REMINDER_URL)
    .headers(Config.sentHeadersNew)
    .body(StringBody ("""{
      "assetId": "${contentId}",
      "matchId": "${matchId}",
      "startDateTime": 1582546604733
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
