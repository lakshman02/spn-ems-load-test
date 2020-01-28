package com.spn.scenarios
import com.spn.requests.LoginWithEmailRequest
import io.gatling.core.Predef.{scenario, _}

object LoginWithEmailScenario {

  val dataFeeder=csv("data/platform.csv").random
  val loginEmailData = csv("data/LoginID.csv").circular

  val LoginWithEmailScenario = scenario("LOGIN With Email Scenario")
    .feed(dataFeeder)
    .feed(loginEmailData)

    .exec(LoginWithEmailRequest.LoginWithEmail)
  //.exec (session => println(session) session)

}

