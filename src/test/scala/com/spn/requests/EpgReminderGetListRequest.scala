package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

import com.spn.config.Config

object EpgReminderGetListRequest {
  val EPG_GetList = exec(http("EPG LIST")
    .get(Config.app_url + Config.EPG_REMINDER_GET_LIST_URL)
    .headers(Config.headerWithoutAuth)
    .queryParamMap(Map("channelId" -> "${channelId}",
      "offset" -> "${offset}",
      "from" -> "${from}",
      "size" -> "${size}",
      "startDate" -> "${getDateTime}"))
    .check(status.is(200))
    .check(jsonPath("$.resultCode").is("OK"))
      .check(jsonPath("$.resultObj").saveAs(Constants.RESP_EPG_RESPONSE))
  )
}
