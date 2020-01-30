package com.spn.simulations

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.structure.PopulationBuilder
import io.gatling.http.Predef._
import org.json.JSONArray

import scala.collection.mutable.ArraySeq
import scala.io.BufferedSource
import scala.io.Source.fromFile;

class CustomSimulation extends Simulation {
  val baseURL = "http://www.example.com"
  val httpConf = http.baseUrl(baseURL)
//  val rawTestList = System.getProperty("testCases")
//  val rawTestList = "[\n  {\n    \"inject\": [\n      {\n        \"type\": \"rampUsers\",\n        \"args\": [\n          1,\n          10\n        ]\n      }\n    ],\n    \"case\": \"Login\"\n  },\n  {\n    \"inject\": [\n      {\n        \"type\": \"atOnceUsers\",\n        \"args\": [\n          5\n        ]\n      }\n    ],\n    \"case\": \"Register\"\n  }\n]"

//  private val source: BufferedSource = fromFile(".\\src\\test\\resources\\test_scenario_injection_strategy.json")
  private val source: BufferedSource = fromFile("./src/test/resources/test_scenario_injection_strategy.json")
  val rawTestList = source.mkString
//  val rawTestList = ("test_scenario_injection_strategy.json")

  val loginScn = scenario("Login").exec(session => {
    println("Logging in...")
    session
  })
  val registerScn = scenario("Register").exec(session => {
    println("Registering...")
    session
  })

  val scenarioNames = Map (
    "Login" -> loginScn,
    "Register" -> registerScn
  )

  def getInjectionStep(stepType:String, stepArgs:JSONArray) : OpenInjectionStep = {
    stepType match {
      case "rampUsers" => rampUsers(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "heavisideUsers" => heavisideUsers(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "atOnceUsers" => atOnceUsers(stepArgs.getInt(0))
      case "constantUsersPerSec" => constantUsersPerSec(stepArgs.getInt(0)) during(stepArgs.getInt(1))
      case "rampUsersPerSec" => rampUsersPerSec(stepArgs.getInt(0)) to(stepArgs.getInt(1)) during(stepArgs.getInt(2))
      case "nothingFor" => nothingFor(stepArgs.getInt(0))
    }
  }

  def scnList() : Seq[PopulationBuilder] = {
    var testList = new JSONArray(rawTestList)
    var scnList = new ArraySeq[PopulationBuilder](testList.length())
    for(i <- 0 until testList.length()) {
      //For each scenario
      var testCase = testList.getJSONObject(i)
      val injectionSteps = testCase.getJSONArray("inject")
      var injectStepList = new ArraySeq[OpenInjectionStep](injectionSteps.length())
      for(j <- 0 until injectionSteps.length()) {
        //For each injection step
        var step = injectionSteps.getJSONObject(j)
        var stepType = step.getString("type")
        var stepArgs = step.getJSONArray("args")
        injectStepList(j) = getInjectionStep(stepType, stepArgs)
      }
      scnList(i) = scenarioNames(testCase.getString("case")).inject(injectStepList)
    }
    scnList
  }

  setUp(scnList:_*).protocols(httpConf)
}
