package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EpgReminderDeleteRequest {

  val epgReminderDelete= exec(http("EPG Reminder Delete Request")
    .post(Config.app_url + Config.EPG_REMINDER_DELETE_URL)
    .headers(Config.sentHeadersNew)
    .body(StringBody ("""{
    "channelId": "${channelId}",
    "assetId": "${assetId}",
    "title": "${title}",
    "startDateTime": 1582680600530,
    "endDateTime": 1582680600840
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
