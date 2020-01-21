package test.scala.com.spn.simulations

import com.spn.config.Config
import test.scala.com.spn.scenarios.GetMenuScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class GetMenuSimulation extends Simulation {
  private val getMenuExec = GetMenuScenario.getMenuScenario
    .inject(constantUsersPerSec(Config.users) during (Config.duration))

  setUp(getMenuExec).protocols(Config.httpProtocol)
    .assertions(
      global.responseTime.max.lt(Config.defaultResponseTime)
    )
}
