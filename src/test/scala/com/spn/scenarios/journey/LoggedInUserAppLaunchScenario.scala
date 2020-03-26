package com.spn.scenarios.journey
import io.gatling.core.Predef.scenario
import com.spn.requests.GetProfileRequest

object LoggedInUserAppLaunchScenario {
 val loggedInUserAppLaunchScenario= scenario("Logged In User App Launch Scenario")
  .exec(GuestUserAppLaunchScenario.guestUserAppLaunchScenario)
//  .exec(GetProfileRequest.getProfile)
}
