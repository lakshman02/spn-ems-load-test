package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ActiveSubscription  {

  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6Im5pdGluIiwibGFzdE5hbWUiOiJrdW1hciIsIm1haWwiOiIiLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0xOFQxMToxNzozMi43NzZaIiwibW9iaWxlTnVtYmVyIjoiIiwiZGF0ZU9mQmlydGgiOiIiLCJnZW5kZXIiOiIiLCJwcm9maWxlUGljIjoiIiwic29jaWFsUHJvZmlsZVBpYyI6IiIsInNvY2lhbExvZ2luSUQiOm51bGwsInNvY2lhbExvZ2luVHlwZSI6bnVsbCwiaXNFbWFpbFZlcmlmaWVkIjpmYWxzZSwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZSwiaWF0IjoxNTgyMDI0NjUyLCJleHAiOjE2MTc1MDczOTJ9.kw8H0N5iDLdoXhDWQHfumVaUhkLMc7DaWvqIPewG6m4",
    "x-via-device" -> "true",
    "Content-Type" -> "application/json"
  )
  val ActiveSubscription   = exec(http("Active Subscription Request")

    .post(Config.app_url + Config.ACTIVE_SUBSCRIPTIONS_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
  "channelPartnerID": "${channelPartnerID}",
  "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}