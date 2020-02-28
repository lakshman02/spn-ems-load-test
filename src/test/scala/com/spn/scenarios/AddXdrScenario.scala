package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.AddXdrRequest
import io.gatling.core.Predef.{scenario, _}

object AddXdrScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val contentIdData = csv("data/contentID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val addXdrScenario = scenario("Add XDR Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dateTimeFeeder)
    .feed(userCredentials)
    .feed(contentIdData)
    .exec(AddXdrRequest.addXdr)
}