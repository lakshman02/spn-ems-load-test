package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DeleteSearchHistoryRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteSearchHistoryScenario {


val scnDeleteSearchHistory = scenario ("Delete Search History")
  .feed(CommonFeedFiles.dataFeederChannel)
  .feed(CommonFeedFiles.dataFeederLocale)
  .feed(CommonFeedFiles.dataFeederCluster)
  .feed(CommonFeedFiles.dataFeederTenant)
  .feed(CommonFeedFiles.dataFeederProperty)
  .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
  .feed(CommonFeedFiles.contentFeeder)
  .exec(ApiSecurity.getToken)
  .exec(DeleteSearchHistoryRequest.DeleteSearchHistory)

}
