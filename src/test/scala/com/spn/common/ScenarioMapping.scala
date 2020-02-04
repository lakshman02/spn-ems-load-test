package com.spn.common

import com.spn.scenarios.journey.GetProfileJourneyScenario
import com.spn.scenarios.{AccountSearchScenario, CreateOTPScenario, GetInitialConfigScenario, GetMenuScenario, GetPageIdScenario, GetProfileScenario, GetULDScenario, LoginScenario, LoginWithEmailScenario}

object ScenarioMapping {

  val scenarioNames = Map (
    "accountSearch" -> AccountSearchScenario.accountSearchScenario,
    "createOTP" -> CreateOTPScenario.createOTPScenario,
    "getInitialConfig" -> GetInitialConfigScenario.getInitialConfigScenario,
    "getMenu" -> GetMenuScenario.getMenuScenario,
    "getPage" -> GetPageIdScenario.PageId,
    "getProfile" -> GetProfileScenario.getProfileScenario,
    "getULD" -> GetULDScenario.getULDScenario,
    "login" -> LoginScenario.loginScenario,
    "loginWithEmail" -> LoginWithEmailScenario.LoginWithEmailScenario,
    "getProfileJourney" -> GetProfileJourneyScenario.getProfileJourneyScenario
  )
}