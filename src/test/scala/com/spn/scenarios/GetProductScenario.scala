package com.spn.scenarios

import com.spn.requests.GetProduct

import io.gatling.core.Predef.{scenario, _}

object GetProductScenario   {

  val dataFeeder=csv("data/platform.csv").circular

  val getProductScenario = scenario("Get Product Scenario")
   .feed(dataFeeder)

    .exec(GetProduct .GetProduct)
  //.exec (session => println(session) session)

}