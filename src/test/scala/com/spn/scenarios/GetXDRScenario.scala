package com.spn.scenarios

import com.spn.requests.GetXDRRequest
import io.gatling.core.Predef.{scenario, _}

object GetXDRScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederContentId = csv("data/contentID.csv").circular
  val dataFeederUserAuth = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.random

  val scn_Get_XDR = scenario("Get XDR scenario")

    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederContentId)
    .feed(dataFeederUserAuth)
    .exec(GetXDRRequest.getXDR)
}
