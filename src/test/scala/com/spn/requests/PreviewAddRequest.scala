package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PreviewAddRequest {

  val previewAddRequest = exec(http("Preview Add Request")
    .post(Config.app_url + Config.PREVIEW_ADD_URL)
    .headers(Config.sentHeaders)
    .body(StringBody(""" {
                           "deviceId": "${deviceId}",
                           "assetId": "${assetID}",
                           "assetType": "${type}",
                           "previewConsumed": "${previewConsumed}",
                           "created_at": "${created_at}",
                           "previewDuration": "${previewDuration}"
                         }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("success"))
  )

}

