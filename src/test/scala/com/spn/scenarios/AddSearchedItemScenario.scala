package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.AddSearchedItemRequest
import io.gatling.core.Predef._

object AddSearchedItemScenario {

  val dataFeederSearchvalue =csv ("data/traySearchQueries.csv").circular

  val scnAddSearchedItem = scenario("Add Searched item")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(dataFeederSearchvalue)
    .exec(AddSearchedItemRequest.AddSearchedItem)
}
