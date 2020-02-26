package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteListRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAxMDcxNTMwMTE3Njk5ODAxIiwidG9rZW4iOiJseHdILUxmUmUtdjBveC1TQzNILTl5MFQtZXdhSS1PZSIsImV4cGlyYXRpb25Nb21lbnQiOiIyMDIxLTA0LTE1VDIzOjE5OjU3LjAwMFoiLCJpc1Byb2ZpbGVDb21wbGV0ZSI6dHJ1ZSwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORDEiLCJmaXJzdE5hbWUiOiJSYWtlc2giLCJsYXN0TmFtZSI6IlRpbmt1IiwibWFpbCI6IiIsInNlc3Npb25DcmVhdGlvblRpbWUiOiIyMDIwLTAyLTI1VDExOjE3OjE0LjI1NFoiLCJtb2JpbGVOdW1iZXIiOiIiLCJkYXRlT2ZCaXJ0aCI6IiIsImdlbmRlciI6IiIsInByb2ZpbGVQaWMiOiIiLCJzb2NpYWxQcm9maWxlUGljIjoiIiwic29jaWFsTG9naW5JRCI6bnVsbCwic29jaWFsTG9naW5UeXBlIjpudWxsLCJpc0VtYWlsVmVyaWZpZWQiOmZhbHNlLCJpc01vYmlsZVZlcmlmaWVkIjp0cnVlLCJpYXQiOjE1ODI2Mjk0MzQsImV4cCI6MTYxODUyODc1NH0.fIgc_AewizQlGtR29lUGITCS-svwaZ4bbCscxs4gbCogit",
    "x-via-device" -> "true")

  val deleteList= exec(http("Delete list Request")
    .post(Config.app_url + Config.DELETE_LIST_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "assets": [
        "1000000189"
    ]
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
