package com.spn.testScripts

import com.spn.common.CommonFeedFiles
import com.spn.config.Config
import io.gatling.core.Predef.{rampUsers, _}
import io.gatling.core.scenario.Simulation

class TestingBatchSimulation extends Simulation{

  val testingBatchScenario = scenario("Testing Batch Scenario")
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
      .exec(session => {

        val evg_email = session("evg_email").as[String]

        println(s"Testing users processing time : $evg_email")
        session
      })


  private val tryExec = testingBatchScenario
    .inject(
      rampUsers(50000) during(300)
    )

  setUp(tryExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


