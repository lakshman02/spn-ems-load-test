package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object PostSubscriptionHistoryRequest {

  val subscriptionHistory = exec(http("Post Subscription History")
    .post(Config.app_url + Config.Post_Subscription_History)
      .headers(Map("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6Im5pdGluIiwibGFzdE5hbWUiOiJrdW1hciIsIm1haWwiOiIiLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0xOFQxMToxNzozMi43NzZaIiwibW9iaWxlTnVtYmVyIjoiIiwiZGF0ZU9mQmlydGgiOiIiLCJnZW5kZXIiOiIiLCJwcm9maWxlUGljIjoiIiwic29jaWFsUHJvZmlsZVBpYyI6IiIsInNvY2lhbExvZ2luSUQiOm51bGwsInNvY2lhbExvZ2luVHlwZSI6bnVsbCwiaXNFbWFpbFZlcmlmaWVkIjpmYWxzZSwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZSwiaWF0IjoxNTgyMDI0NjUyLCJleHAiOjE2MTc1MDczOTJ9.kw8H0N5iDLdoXhDWQHfumVaUhkLMc7DaWvqIPewG6m4",
        "x-via-device" -> "true" ))
  .body(StringBody("""{
                       "channelPartnerID": "${channel}",
                       "timestamp": "${getDateTime}"
                     } """)).asJson
      .check(status.is(200))
      .check(jsonPath("$.resultCode").is("OK"))
  )
}
