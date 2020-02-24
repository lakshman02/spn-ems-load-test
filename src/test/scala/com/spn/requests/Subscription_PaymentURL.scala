package com.spn.requests
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Subscription_PaymentURL {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
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
	"country": "IN",
	"subscriptionMode":"TVOD",
	"pkgInd":"T",
	"renewable":"F",
	"channel_id":"WEB",
	"cpId":"20012007291003285",
	"channelPartnerID": "MSMIND1"
}""")).asJson
    .check(jsonPath("$.resultCode").is("OK")))
}