package com.spn.scenarios.journey

import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.{ActiveSubscription, AllSubscriptionsRequest, LoginWithEmailRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object UserJourneyLoggedInWithEmailScenario {

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val dataLoginCredentials = csv("data/LoginID.csv").random


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val userJourneyLoggedInUser = scenario("User Journey - Logged in user and fetching subscription details")



    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(dataLoginCredentials)
    .feed(dateTimeFeeder)

    .exec(LoginWithEmailRequest.LoginWithEmail)
    .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)){
      exec(session => {
        val authToken = session(Constants.RESP_AUTH_TOKEN).as[String]
        println(s"\nRESP_AUTH_TOKEN is: $authToken")
        session
      })
        .pause(1,4)
        .exec(AllSubscriptionsRequest.getAllSubscriptions)
        .pause(1,5)
        .exec(ActiveSubscription.ActiveSubscription)
    }

}
