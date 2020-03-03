package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.SubscriptionRemoveRequest
import io.gatling.core.Predef._

object SubscriptionRemoveScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular
  val dataFeederServiceDetails = csv("data/service_details.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val subscriptionRemoveScenario= scenario("Subscription Remove Scenario")

    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dataFeederServiceDetails)
    .feed(CommonFeedFiles.dateTimeFeeder)


    .exec(SubscriptionRemoveRequest.subscriptionRemove)
  //.exec (session => println(session) session)
}
