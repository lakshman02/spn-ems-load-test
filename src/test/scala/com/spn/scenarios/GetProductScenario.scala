package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetProduct
import io.gatling.core.Predef.{scenario, _}

object GetProductScenario   {

  val dataSalesFeeder = csv("data/payment_details.csv").circular

  val getProductScenario = scenario("Get Product Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(dataSalesFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)

    .exec(GetProduct.GetProduct)
  //.exec (session => println(session) session)

}