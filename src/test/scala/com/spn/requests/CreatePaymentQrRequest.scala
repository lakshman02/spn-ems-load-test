package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreatePaymentQrRequest {

  val createPaymentQrRequest = exec(http("Create paymentQrRequest")
    .post(Config.app_url + Config.CREATE_PAYMENT_QR )
    .headers(Map("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAyMDYwNjM5MTAxNTczNiIsInRva2VuIjoiSFlYdC1tSXRyLTJOeVUtZ1VCQi1qYld0LUx4d1AtZU0iLCJleHBpcmF0aW9uTW9tZW50IjoiMjAyMS0wMi0wNVQwNjo0MTo0OS45MjVaIiwiaXNQcm9maWxlQ29tcGxldGUiOmZhbHNlLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0wNlQwNjo0MTo0OS45MjdaIiwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImxhc3ROYW1lIjoiIiwiZW1haWwiOiIiLCJpYXQiOjE1ODA5NzEzMTAsImV4cCI6MTU4MTU3MDk1MH0.dLrvkE3NJc-jlCrLSYZXh1Xi113QnZSYEBPnaY09GFk",
      "x-via-device" -> "true"))
    .body(StringBody("""{
                       "channelPartnerID": "${channelPartnerID}",
                       "serviceID": "${serviceID}",
                       "serviceType": "${serviceType}",
                       "paymentChannel":"${paymentChannel}"
                       "timestamp": "${getDateTime}",
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
