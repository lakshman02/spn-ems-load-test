package com.spn.scenarios.journey

import java.time.LocalDateTime

import com.spn.requests.{ActiveSubscription, AllSubscriptionsRequest, LoginRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object UserJourneyMobileLoginAndGetSubscription {

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val dataFeederLoginData = csv("data/LoginID.csv").random
  val Userlogin = csv("data/evergent_data.csv").circular

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val scnLoginAndSubscription = scenario("User Journey with Mobile Login and Subscription details")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederLoginData)
    .feed(Userlogin)
    .feed(dateTimeFeeder)

    .exec(LoginRequest.LoginRequest)
    .pause(1, 3 seconds)

    .exec(AllSubscriptionsRequest.getAllSubscriptions)
    .pause(1, 3 seconds)

    .exec(ActiveSubscription.ActiveSubscription)
}
