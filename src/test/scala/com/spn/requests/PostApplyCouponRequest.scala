package com.spn.requests

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import com.spn.config.Config
object PostApplyCouponRequest {

  val ApplyCoupon = http("Post Apply Coupon")
    .post (Config.app_url + Config.Post_Apply_Coupon_URL)
    .headers(Map ("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6IkxvYWQiLCJsYXN0TmFtZSI6IlRlc3QiLCJtYWlsIjoiIiwic2Vzc2lvbkNyZWF0aW9uVGltZSI6IjIwMjAtMDItMTdUMTM6MzQ6MzguMTU4WiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImlhdCI6MTU4MTk0NjQ3OCwiZXhwIjoxNjE3NTA3Mzk4fQ.eEG4I1Up1RF6NVnMuQam-DqEbfT6XNuifJtOudFKaew",
      "x-via-device" -> "true"))

    .body(StringBody(""" {
                       "couponCode": "GEN_FREE",
                        "price": 1.99,
                        "productID": "daily_india",
                        "channelPartnerID": "${channelPartnerID}",
                        "timestamp": "2020-02-17T03:06:45.872Z"
                       }""")).asJson
    //Hardcoded body string
    .check(status .is(200))
    .check(jsonPath("$.resultCode").is("OK"))
}
