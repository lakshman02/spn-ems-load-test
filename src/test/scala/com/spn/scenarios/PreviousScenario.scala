package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PreviousRequest
import io.gatling.core.Predef.{scenario, _}

object PreviousScenario {

  val dataFeederContentid = csv("data/contentID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val scnPreviousContent = scenario("Get Previous Content Details")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentid)
    .feed(userCredentials)
    .exec(PreviousRequest.Previous)
}
