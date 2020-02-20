package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionRemoveRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6Im5pdGluIiwibGFzdE5hbWUiOiJrdW1hciIsIm1haWwiOiIiLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0yMFQwNTo1MToyMS42MzFaIiwibW9iaWxlTnVtYmVyIjoiIiwiZGF0ZU9mQmlydGgiOiIiLCJnZW5kZXIiOiIiLCJwcm9maWxlUGljIjoiIiwic29jaWFsUHJvZmlsZVBpYyI6IiIsInNvY2lhbExvZ2luSUQiOm51bGwsInNvY2lhbExvZ2luVHlwZSI6bnVsbCwiaXNFbWFpbFZlcmlmaWVkIjpmYWxzZSwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZSwiaWF0IjoxNTgyMTc3ODgxLCJleHAiOjE2MTc1MDczODF9.vT-URQn92sGk5-ZXgGufY2U7Ncl6t7gGI6GMasHEebE",
    "x-via-device" -> "true")

  val subscriptionRemove= exec(http("SubscribedRemove Request")
    .post(Config.app_url + Config.SUBSCRIPTION_REMOVE_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
  "serviceID": "${skuORQuickCode}",
  "reason": "testing",
  "serviceType": "${serviceType}",
  "channelPartnerID": "${channelPartnerID}",
  "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
