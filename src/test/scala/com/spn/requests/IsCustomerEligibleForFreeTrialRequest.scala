package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object IsCustomerEligibleForFreeTrialRequest {

  val checkCustomerEligibleForFreeTrial = exec(http("Check if the user is eligible for free trial")
    .get(Config.app_url + Config.CHECK_FREE_TRIAL)
      .queryParam("timestamp", "${getDateTime}")
    .headers(Map("Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAyMDUxMTQ2MTAxNTIzNCIsInRva2VuIjoiV3gyVS1lYWhKLTJaMHItTVh1bC1XNlNwLTRqQXgtSXciLCJleHBpcmF0aW9uTW9tZW50IjoiMjAyMS0wMi0wNFQxMTo0NjowMy45MzdaIiwiaXNQcm9maWxlQ29tcGxldGUiOmZhbHNlLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0wNVQxMTo0NjowMy45MzhaIiwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImxhc3ROYW1lIjoiIiwiZW1haWwiOiIiLCJpYXQiOjE1ODA5MDMxNjQsImV4cCI6MTU4MTUwMjgwNH0.joiCJ14Wru1JpFTBl85kZk-z5TSW-txOzgIYYo9dyhM",
      "x-via-device" -> "true"))
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
