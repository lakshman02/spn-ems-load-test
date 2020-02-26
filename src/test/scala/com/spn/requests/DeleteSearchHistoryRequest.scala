package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteSearchHistoryRequest {

  val DeleteSearchHistory = http("Post Delete Search History")
    .post (Config.app_url + Config.DELETE_SEARCH_HISTORY)
    .headers(Map ("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAxMDgwOTM2OTk1ODI5IiwidG9rZW4iOiJPRjhuLUZXRzItR3lUOS1GZkNWLUF0OVYtUXdmRi1qciIsImV4cGlyYXRpb25Nb21lbnQiOiIyMDIxLTAzLTI3VDEwOjE1OjE5LjkzMVoiLCJpc1Byb2ZpbGVDb21wbGV0ZSI6dHJ1ZSwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IkRlZXB1IiwibGFzdE5hbWUiOiJTYW5kZWVwIiwibWFpbCI6IiIsInNlc3Npb25DcmVhdGlvblRpbWUiOiIyMDIwLTAyLTA0VDE5OjE1OjIwLjA4NVoiLCJtb2JpbGVOdW1iZXIiOiIiLCJkYXRlT2ZCaXJ0aCI6IiIsImdlbmRlciI6IiIsInByb2ZpbGVQaWMiOiIiLCJzb2NpYWxQcm9maWxlUGljIjoiIiwic29jaWFsTG9naW5JRCI6bnVsbCwic29jaWFsTG9naW5UeXBlIjpudWxsLCJpc0VtYWlsVmVyaWZpZWQiOmZhbHNlLCJpc01vYmlsZVZlcmlmaWVkIjp0cnVlLCJpYXQiOjE1ODA4NDM3MjEsImV4cCI6MTYxNjg0MDA2MX0.V08VJ2aaSy5QIcdRQ19DZ1aJ2309ot7ZT3804PRcyFE",
      "x-via-device" -> "true"))

    .body(StringBody("""{
                       "delSearchParams": [
                        "king","football"
                       ]
                       }""".stripMargin)).asJson
    //Hardcoded body string
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
}
