package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetTransactionStatusRequest {
//  val sentHeaders = Map(
//    "Authorization" -> "${RESP_AUTH_TOKEN}",
//    "x-via-device" -> "true",
//    "Content-Type" -> "application/json"
//  )

  val getTransactionStatus= exec(http("Get Transaction Status Request")
    .get(Config.app_url + Config.GET_TRANSACTION_STATUS_URL)
    .headers(Map("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAyMDYwNjM5MTAxNTczNiIsInRva2VuIjoiSFlYdC1tSXRyLTJOeVUtZ1VCQi1qYld0LUx4d1AtZU0iLCJleHBpcmF0aW9uTW9tZW50IjoiMjAyMS0wMi0wNVQwNjo0MTo0OS45MjVaIiwiaXNQcm9maWxlQ29tcGxldGUiOmZhbHNlLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0wNlQwNjo0MTo0OS45MjdaIiwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImxhc3ROYW1lIjoiIiwiZW1haWwiOiIiLCJpYXQiOjE1ODA5NzEzMTAsImV4cCI6MTU4MTU3MDk1MH0.dLrvkE3NJc-jlCrLSYZXh1Xi113QnZSYEBPnaY09GFk",
      "x-via-device" -> "true"))
    .body(StringBody("""{
                       "channelPartnerID": "MSMIND1",
                       "transactionId": "qr_EAB92TPTpAdUdd",
                       "paymentChannel":"BHARATQR",
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
