package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//Account Search Request
object UpgradablePlansRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAxMDcxNTMwMTE3Njk5ODAxIiwidG9rZW4iOiI5eVZoLTFCN0UtQURnVy1OMldpLTdscnUtdnQ5NS0zQiIsImV4cGlyYXRpb25Nb21lbnQiOiIyMDIxLTA0LTA0VDAwOjQ2OjQ3LjAwMFoiLCJpc1Byb2ZpbGVDb21wbGV0ZSI6dHJ1ZSwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORDEiLCJmaXJzdE5hbWUiOiJSYWtlc2giLCJsYXN0TmFtZSI6IlRpbmt1IiwibWFpbCI6InNhbmRlZXAucGFjaGlwdWx1c3UxOTkwQGdtYWlsLmNvbSIsInNlc3Npb25DcmVhdGlvblRpbWUiOiIyMDIwLTAyLTE4VDE2OjA3OjAzLjYxNloiLCJtb2JpbGVOdW1iZXIiOiIiLCJkYXRlT2ZCaXJ0aCI6IiIsImdlbmRlciI6IiIsInByb2ZpbGVQaWMiOiIiLCJzb2NpYWxQcm9maWxlUGljIjoiIiwic29jaWFsTG9naW5JRCI6bnVsbCwic29jaWFsTG9naW5UeXBlIjpudWxsLCJpc0VtYWlsVmVyaWZpZWQiOmZhbHNlLCJpc01vYmlsZVZlcmlmaWVkIjp0cnVlLCJpYXQiOjE1ODIwNDIwMjMsImV4cCI6MTYxNzQ5NzE2M30.V1Jib3sSOxVitqOv-kLStgE-QQs1dAp105n8-pbkuBw",
    "x-via-device" -> "true")

  val upgradablePlans= exec(http("Upgradable Plans Request")
    .post(Config.app_url + Config.UPGRADABLE_PLANS_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "skuORQuickCode": "${skuORQuickCode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
