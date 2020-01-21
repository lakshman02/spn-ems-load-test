package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetInitialConfigScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}
import io.gatling.http.Predef.http
import scala.concurrent.duration._

class GetInitialConfigSimulation extends Simulation {

  val httpProtocol = http
    .acceptHeader("")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  private val getInitialConfigExec = GetInitialConfigScenario.getInitialConfigScenario
    .inject(rampUsers(Config.users) during (Config.rampUp seconds))

    setUp(getInitialConfigExec).protocols(httpProtocol)
}