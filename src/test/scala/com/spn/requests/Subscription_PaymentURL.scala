package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Subscription_PaymentURL {

  val Subscription_Payment = exec(http("Subscription Payment URL")
    .post(Config.app_url + Config.SUBSCRIPTION_PAYMENTURL)
    .headers(Config.sentHeaders)
    .body(StringBody(
      """{
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
	"channelPartnerID": "${channelPartnerID}",
  "deviceSerialNo":"${deviceSerialNo}",
  "source":"${source}"
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}