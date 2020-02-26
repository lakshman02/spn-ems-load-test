package com.spn.common

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import org.json.JSONArray

object InjectionStrategy {

  def getInjectionStep(stepType:String, stepArgs:JSONArray) : OpenInjectionStep = {
    stepType match {
      case "rampUsers" => rampUsers(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "heavisideUsers" => heavisideUsers(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "atOnceUsers" => atOnceUsers(stepArgs.getInt(0))
      case "constantUsersPerSec" => constantUsersPerSec(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "rampUsersPerSec" => rampUsersPerSec(stepArgs.getInt(0)) to(stepArgs.getInt(1)) during(stepArgs.getInt(2))
      case "nothingFor" => nothingFor(stepArgs.getInt(0))
      case "incrementUsersInSteps" => incrementUsersPerSec(stepArgs.getInt(1))//Watch out for index
        .times(stepArgs.getInt(2))
        .eachLevelLasting(stepArgs.getInt(3))
        .separatedByRampsLasting(stepArgs.getInt(4))
        .startingFrom(stepArgs.getInt(0))
    }
  }
}
