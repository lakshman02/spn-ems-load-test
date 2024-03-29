package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetRemindersRequest
import io.gatling.core.Predef.{scenario, _}

object GetRemindersScenario {


  val getRemindersScenario = scenario("Get Reminders Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_GetReminder)
    .exec(ApiSecurity.getToken)
    .exec(GetRemindersRequest.getRemindersRequest)

}
