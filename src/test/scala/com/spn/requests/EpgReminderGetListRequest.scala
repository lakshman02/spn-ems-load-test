package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object EpgReminderGetListRequest {
  val EPG_GetList = exec(http("EPG Reminder GET LIST")
    .get(Config.app_url + Config.EPG_REMINDER_GET_LIST_URL)
    .headers(Config.sentHeadersNew)
    .queryParamMap(Map("channelId" -> "ALL",
      "offset" -> "500",
      "from" -> "0",
      "size" -> "20",
      "startDate" -> "2019-01-01"))
    .check(status.is(200))
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
