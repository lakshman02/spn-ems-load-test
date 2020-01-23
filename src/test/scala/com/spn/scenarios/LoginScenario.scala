package com.spn.scenarios
import com.spn.requests.LoginRequest
import io.gatling.core.Predef.{scenario, _}

  object LoginScenario {

    val dataFeeder=csv("platform.csv").random
    val loginData = csv("LoginID.csv").circular

    val loginScenario = scenario("LOGIN Scenario")
      .feed(dataFeeder)
      .feed(loginData)

      .exec(LoginRequest.LoginRequest)
      //.exec (session => println(session) session)

  }



