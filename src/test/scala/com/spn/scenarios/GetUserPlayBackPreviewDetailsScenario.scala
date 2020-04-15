package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.GetUserPlayBackPreviewDetailsRequest
import io.gatling.core.Predef.{scenario, _}

import scala.util.Random

object GetUserPlayBackPreviewDetailsScenario{

//  val previewDetailsDataFeeder=csv("data/previewDetails.csv").circular

  private def randomDeviceIdNumber: String = {
    val r = Random
    Constants.SB_TEST_DEVICE_ID_PREFIX + r.nextInt(20000)
  }

  val getUserPlayBackPreviewDetailsScenario =scenario("Get User PlayBack Preview Details Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.previewDetailsDataFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(session => session.set("id", "1000000055").set("type", "live").set("deviceId", randomDeviceIdNumber))
    .exec(ApiSecurity.getToken)
    .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
}
