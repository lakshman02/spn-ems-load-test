package com.spn.scenarios

import com.spn.requests.MovieDetailRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object MovieDetailScenario {
  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val movieDetailData = csv("data/movieId.csv").circular

  val movieDetailScenario =scenario("Movie Detail Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(movieDetailData)
    .exec(MovieDetailRequest.movieDetail)
}
