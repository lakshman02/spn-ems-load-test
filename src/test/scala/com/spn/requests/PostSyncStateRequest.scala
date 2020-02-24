package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object PostSyncStateRequest {

  val postSyncStateRequest = exec(http("Post Sync State Request")
    .post(Config.app_url + Config.Post_Sync_State_URL)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))
    .body(StringBody("""{
                       |"packageId":"${packageId}",
                       |"packageName":"${packageName}",
                       |"state": "User Logged In",
                       |"isTransactionCompleted": false,
                       |"isPaymentSuccessful": false,
                       |"timestamp": "${getDateTime}"
                       |}""".stripMargin)).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
