package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EpgReminderRequest {

  val epgReminder= exec(http("EPG Reminder Request")
    .post(Config.app_url + Config.EPG_REMINDER_URL)
    .headers(Config.sentHeadersNew)
    .body(StringBody ("""{
    "channelId": "${channelId}",
    "assetId": "${assetId}",
    "title": "${title}",
    "startDateTime": ${startDateTime},
    "endDateTime": ${endDateTime}
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
