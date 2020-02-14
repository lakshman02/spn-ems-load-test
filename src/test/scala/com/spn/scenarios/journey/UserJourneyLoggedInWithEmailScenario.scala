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
  val evergentLoginData = csv("data/evergent_data.csv").circular

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
    .feed(evergentLoginData)
    .feed(dateTimeFeeder)

    .exec(LoginWithEmailRequest.LoginWithEmail)
    .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)){
      exec(session => {
        val authToken = session(Constants.RESP_AUTH_TOKEN).as[String]
        println(s"\nRESP_AUTH_TOKEN is: $authToken")
        session
      })
      .pause(1,3)
        .exec(ActiveSubscription.ActiveSubscription)
      .pause(1,3)
        .exec(AllSubscriptionsRequest.getAllSubscriptions)
    }
}
