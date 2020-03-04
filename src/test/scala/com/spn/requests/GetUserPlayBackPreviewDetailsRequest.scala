package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetUserPlayBackPreviewDetailsRequest {

  val PreviewDetails= exec(http("Get User PlayBack Preview Details Request")
    .post(Config.app_url + Config.GET_USER_PLAYBACK_PREVIEW_DETAILS_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "data": [
          {
          "id":"${id}",
          "type": "${type}"
          }
          ],
          "deviceDetails": {
          "deviceId": "${deviceId}"
          }}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
