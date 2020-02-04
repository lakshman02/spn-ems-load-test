package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//"Get Menu" Request
object UpdateProfileRequest {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAxMDgwOTM2OTk1ODI5IiwidG9rZW4iOiJoTzluLUlIYU8tTmJraS1wcElsLXNPZXEtaXBYOS01YSIsImV4cGlyYXRpb25Nb21lbnQiOiIyMDIxLTAyLTI4VDAxOjA0OjUzLjYyOVoiLCJpc1Byb2ZpbGVDb21wbGV0ZSI6InRydWUiLCJjaGFubmVsUGFydG5lcklEIjoiTVNNSU5EIiwiZmlyc3ROYW1lIjoiU2FuZHkiLCJsYXN0TmFtZSI6IiIsInNlc3Npb25DcmVhdGlvblRpbWUiOiIyMDIwLTAxLTA4VDEwOjA0OjUzLjc0OFoiLCJtb2JpbGVOdW1iZXIiOiI5MDUyMjI3NjA3IiwiZGF0ZU9mQmlydGgiOiI2NTU0OTcwMDAwMDAiLCJnZW5kZXIiOiJNYWxlIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ImZhbHNlIiwiaXNNb2JpbGVWZXJpZmllZCI6InRydWUiLCJlbWFpbCI6InNhbmRlZXAucGFjaGlwdWx1c3UxOTkwQGdtYWlsLmNvbSIsImlhdCI6MTU3ODQ3Nzg5NCwiZXhwIjoxNjE0NDc0MjM0fQ.ZQLxvbfWsmpuYBs1IHaIIE4EYinD97p3YsCTAiVoXdw",
    "x-via-device" -> "true")
  val updateProfile= exec(http("Group Of Bundles Request")
    .post(Config.app_url + Config.UPDATE_PROFILE_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
             	"customerUsername": "${email}",
    "firstName": "Rakesh",
    "lastName": "Tinku",
    "email": "sandeep.pachipulusu1990@gmail.com",
    "customerPassword": "Test@1234",
    "mobileNumber": "9052227607",
    "country": "IN",
    "dateOfBirth": 765743400000,
    "gender": "Male",
    "pincode": "500072",
    "channelPartnerID": "MSMIND",
    "dmaID": "IN",
    "timestamp": "2019-02-19T06:32:12.326Z"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
