package com.spn.scenarios.journey
import java.time.LocalDateTime

import io.gatling.core.Predef._

import scala.concurrent.duration._
import com.spn.requests.{ActiveSubscription, AllSubscriptionsRequest, LoginRequest}



object UserJourneyLoginandSubscription {
private val dataFeederLoginData = csv("data/LoginID.csv").random
  private val Userlogin = csv("data/evergent_data.csv").random

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val scnLoginAndSubscription = scenario("User Journey with Login and Subscription details")
    .feed(dataFeederTenant).feed(dataFeederCluster).feed(dataFeederLocale).feed(dataFeederChannel).feed(dataFeederProperty)

    .feed(dataFeederLoginData)
    .feed(Userlogin)

    .exec(LoginRequest.LoginRequest)
    .pause(1,3 seconds)

    .exec(AllSubscriptionsRequest.getAllSubscriptions)
    .pause(1,3 seconds)

    .exec(ActiveSubscription.ActiveSubscription)


}
