package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PaymentModesRequest
import io.gatling.core.Predef._
object PaymentModesScenario {

 val bodydatafeeder = csv("data/LoginID.csv")
 val usersWithAuthtokenDataFeeder = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

 val scnPaymentMode = scenario(" Payment Modes ")
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(bodydatafeeder)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(usersWithAuthtokenDataFeeder)
    .exec(PaymentModesRequest.Payment_mode)
}
