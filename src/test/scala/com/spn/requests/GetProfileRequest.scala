package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetProfileRequest {
  val sentHeaders = Map(
//    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNzA2MTQyMDE1Mzc5Njg1NzQiLCJ0b2tlbiI6Ik1JeFItNDlwTy1OaFJBLU15V24tSDdpTy1BeWRtLTliIiwiZXhwaXJhdGlvbk1vbWVudCI6IjIwMjEtMDQtMDRUMDM6MzY6NDEuMDAwWiIsImlzUHJvZmlsZUNvbXBsZXRlIjp0cnVlLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EMSIsImZpcnN0TmFtZSI6IkxvYWQiLCJsYXN0TmFtZSI6IlRlc3QiLCJtYWlsIjoiam1ldGVyLXRlc3QtaW5kd2VzLXVzZXItMzY1ODM2QG1haWxpbmF0b3IuY29tIiwic2Vzc2lvbkNyZWF0aW9uVGltZSI6IjIwMjAtMDItMTdUMTE6MDA6MTAuODMxWiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImlhdCI6MTU4MTkzNzIxMCwiZXhwIjoxNjE3NTA3MzcwfQ.JovNKWW2N6So9Ef-tc4DMKlIzi4VD3Cj0ziyocG_3iE",
      "x-via-device" -> "true",
    "Content-Type" -> "application/json"
  )

  val getProfile= exec(http("Get Profile Request")
    .get(Config.app_url + Config.GET_PROFILE_URL)
    .headers(sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
