package com.spn.simulations
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.EpisodeIdDetailScenario
import com.spn.config.Config
class EpisodeIdDetailsSimulation extends Simulation {
private val EpisodeIdDetails = EpisodeIdDetailScenario.scnEpisodeIdDetails
    .inject(rampUsers(Config.rampUp)during(Config.duration))

 setUp(EpisodeIdDetails).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}
