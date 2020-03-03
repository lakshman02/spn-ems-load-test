package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.DeleteSearchHistoryRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteSearchHistoryScenario {

 val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.circular

val scnDeleteSearchHistory = scenario (" Delete Search History")
  .feed(CommonFeedFiles.dataFeederChannel)
  .feed(CommonFeedFiles.dataFeederLocale)
  .feed(CommonFeedFiles.dataFeederCluster)
  .feed(CommonFeedFiles.dataFeederTenant)
  .feed(CommonFeedFiles.dataFeederProperty)
  .feed(userCredentials)
  .exec(DeleteSearchHistoryRequest.DeleteSearchHistory)

}
