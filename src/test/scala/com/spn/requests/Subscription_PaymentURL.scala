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
	"retControlUrl":"${retControlUrl}",
	"itemId":"${itemId}",
	"itemName":"${itemName}",
	"currencyCode":"${currencyCode}",
	"pmtChannel":"${pmtChannel}",
	"country": "${country}",
	"subscriptionMode":"${subscriptionMode}",
	"pkgInd":"${pkgInd}",
	"renewable":"${renewable}",
	"channel_id":"${channel_id}",
	"cpId":"${cpId}",
	"channelPartnerID": "${channelPartnerID}"
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}