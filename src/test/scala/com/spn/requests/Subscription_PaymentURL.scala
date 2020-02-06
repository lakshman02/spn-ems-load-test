package com.spn.requests
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Subscription_PaymentURL {
  val sentHeaders = Map(
    "Authorization" -> "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAyMDUxMDAxMTAxNTE4NiIsInRva2VuIjoiQjA3SS0wVEc5LVZZNUotUDdrSS1Kajh4LXJDU0MtUGYiLCJleHBpcmF0aW9uTW9tZW50IjoiMjAyMS0wMi0wNFQxMDowMzoxMS40OThaIiwiaXNQcm9maWxlQ29tcGxldGUiOmZhbHNlLCJzZXNzaW9uQ3JlYXRpb25UaW1lIjoiMjAyMC0wMi0wNVQxMDowMzoxMS40OThaIiwiY2hhbm5lbFBhcnRuZXJJRCI6Ik1TTUlORCIsImZpcnN0TmFtZSI6IiIsIm1vYmlsZU51bWJlciI6IiIsImRhdGVPZkJpcnRoIjoiIiwiZ2VuZGVyIjoiIiwicHJvZmlsZVBpYyI6IiIsInNvY2lhbFByb2ZpbGVQaWMiOiIiLCJzb2NpYWxMb2dpbklEIjpudWxsLCJzb2NpYWxMb2dpblR5cGUiOm51bGwsImlzRW1haWxWZXJpZmllZCI6ZmFsc2UsImlzTW9iaWxlVmVyaWZpZWQiOnRydWUsImxhc3ROYW1lIjoiIiwiZW1haWwiOiIiLCJpYXQiOjE1ODA4OTY5OTIsImV4cCI6MTU4MTQ5NjYzMn0.uYyy50kNF3nw5uTSJl1rmDHeM4Ec_I3YlPJRowOL3As",
    "x-via-device" -> "true",
    "Content-Type" -> "application/json"
  )
  val Subscription_Payment = exec(http("Subscription Payment URL")
    .post(Config.app_url + Config.SUBSCRIPTION_PAYMENTURL)
    .headers(sentHeaders)
    .body(StringBody ("""{
	"retControlUrl":"https://www.sonyliv.com/orderComplete/TVOD",
	"itemId":"rabnebanadijodi_all_tvod_ind_android",
	"itemName":"Rab_Ne_Bana_Di_Jodi",
	"currencyCode":"INR",
	"pmtChannel":"FORTUMO",
	"country": "{{country}}",
	"subscriptionMode":"TVOD",
	"pkgInd":"T",
	"renewable":"F",
	"channel_id":"WEB",
	"cpId":"20012007291003285",
	"channelPartnerID": "MSMIND"
}""")).asJson
    .check(jsonPath("$.resultCode").is("OK")))
}