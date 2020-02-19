package com.spn.scenarios
import com.spn.requests.LoginRequest
import com.spn.scenarios.IsCustomerEligibleForFreeTrialScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant, loginEmailData, userCredentials}
import io.gatling.core.Predef.{scenario, _}

  object LoginScenario {

    val dataFeederChannel = csv("data/channel.csv").circular
    val dataFeederCluster = csv("data/cluster.csv").circular
    val dataFeederLocale = csv("data/locale.csv").circular
    val dataFeederProperty = csv("data/property.csv").circular
    val dataFeederTenant = csv("data/tenant.csv").circular
    val loginEmailData = csv("data/LoginID.csv").circular
    val userCredentials = csv("data/evergent/evergent_data_for_mobile_login.csv.gz").unzip.shard

    val loginScenario = scenario("Login With Mobile Scenario")
      .feed(dataFeederTenant)
      .feed(dataFeederCluster)
      .feed(dataFeederLocale)
      .feed(dataFeederChannel)
      .feed(dataFeederProperty)
      .feed(CreateOTPScenario.dateTimeFeeder)
      .feed(userCredentials)
      .feed(loginEmailData)

      .exec(LoginRequest.LoginRequest)


  }



