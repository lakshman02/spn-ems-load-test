package com.spn.scenarios
import com.spn.requests.LoginRequest
import io.gatling.core.Predef.{scenario, _}

  object LoginScenario {

    val dataFeeder=csv("data/platform.csv").random
    val loginData = csv("data/LoginID.csv").circular

    val loginScenario = scenario("Login With Mobile Scenario")
      .feed(dataFeeder)
      .feed(loginData)

      .exec(LoginRequest.LoginRequest)
      //.exec (session => println(session) session)

  }



