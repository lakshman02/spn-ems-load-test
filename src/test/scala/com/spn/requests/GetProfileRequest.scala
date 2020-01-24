package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetProfileRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAxMDgwOTM2OTk1ODI5IiwidG9rZW4iOiJoTzluLUlIYU8tTmJraS1wcElsLXNPZXEtaXBYOS01YSIsImV4cGlyYXRpb25Nb21lbnQiOiIyMDIxLTAyLTI4VDAxOjA0OjUzLjYyOVoiLCJpc1Byb2ZpbGVDb21wbGV0ZSI6InRydWUiLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EIiwiZmlyc3ROYW1lIjoiU2FuZHkiLCJsYXN0TmFtZSI6IiIsInNlc3Npb25DcmVhdGlvblRpbWUiOiIyMDIwLTAxLTA4VDEwOjA0OjUzLjc0OFoiLCJtb2JpbGVOdW1iZXIiOiI5MDUyMjI3NjA3IiwiZGF0ZU9mQmlydGgiOiI2NTU0OTcwMDAwMDAiLCJnZW5kZXIiOiJNYWxlIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ImZhbHNlIiwiaXNNb2JpbGVWZXJpZmllZCI6InRydWUiLCJlbWFpbCI6InNhbmRlZXAucGFjaGlwdWx1c3UxOTkwQGdtYWlsLmNvbSIsImlhdCI6MTU3ODQ3Nzg5NCwiZXhwIjoxNjE0NDc0MjM0fQ.ZQLxvbfWsmpuYBs1IHaIIE4EYinD97p3YsCTAiVoXdw",
    "x-via-device" -> "true",
    "Content-Type" -> "application/json"
  )
  val getProfile= exec(http("Get Profile Request")
    .get(Config.app_url + Config.GET_PROFILE_URL)
    .headers(sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(bodyString.saveAs("BODY"))
  )
//    //session function
//    .exec{
//      session =>
//        println(session)
//        session
//    }
    .pause(1)
}
