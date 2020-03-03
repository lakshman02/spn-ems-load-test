package com.spn.scenarios

import com.spn.common.Constants
import com.spn.requests.GetMenuRequest
import com.spn.common.CommonFeedFiles
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//"Get Menu" scenario
object GetMenuScenario{

  val getMenuScenario =scenario("Get Menu Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .exec(GetMenuRequest.getMenu)
//    .exec(session => {
//       val channel = session("channel").as[String]
//       val response = session(Constants.RESP_MENU).as[String]
//       println(s"\nMENU Response from Session for channel : $channel ; body is : $response")
//      session
//    })
}
