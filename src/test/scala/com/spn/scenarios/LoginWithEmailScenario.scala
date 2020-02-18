package com.spn.scenarios
import com.spn.requests.LoginWithEmailRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef.{scenario, _}

object LoginWithEmailScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/LoginID.csv").circular
  //val userCredentials = csv("data/evergent_data.csv").random

  val LoginWithEmailScenario = scenario("Login With Email Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    //  .feed(userCredentials)
    .feed(loginEmailData)

    .exec(LoginWithEmailRequest.LoginWithEmail)
  //.exec (session => println(session) session)

}

