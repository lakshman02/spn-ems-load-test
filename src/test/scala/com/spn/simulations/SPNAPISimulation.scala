package com.spn.simulations

import com.spn.common.{InjectionStrategy, ScenarioMapping}
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.structure.PopulationBuilder
import org.json.JSONArray

import scala.collection.mutable.ArraySeq
import scala.io.{BufferedSource, Source}

class SPNAPISimulation extends Simulation {

  val fileSource = System.getProperty("fileSource")
  val filePath = System.getProperty("filePath")

  def scnList() : Seq[PopulationBuilder] = {

    var source: BufferedSource = null;

    if(fileSource!=null && fileSource.equals("s3")) {
      source = Source.fromURL(filePath)
    } else {
      source = Source.fromFile(filePath)
    }

    val rawTestList = source.mkString

    val testList = new JSONArray(rawTestList)
    val scnList = new ArraySeq[PopulationBuilder](testList.length())
    for(i <- 0 until testList.length()) {
      //For each scenario
      val testCase = testList.getJSONObject(i)
      val injectionSteps = testCase.getJSONArray("inject")
      val injectStepList = new ArraySeq[OpenInjectionStep](injectionSteps.length())
      for(j <- 0 until injectionSteps.length()) {
        //For each injection step
        val step = injectionSteps.getJSONObject(j)
        val stepType = step.getString("type")
        val stepArgs = step.getJSONArray("args")
        injectStepList(j) = InjectionStrategy.getInjectionStep(stepType, stepArgs)
      }
      scnList(i) = ScenarioMapping.scenarioNames(testCase.getString("case")).inject(injectStepList)
    }
    scnList
  }

//  setUp(scnList:_*).protocols(Config.httpProtocol)
  setUp(scnList:_*)
    .protocols(Config.httpProtocol)
    .assertions(
      global.responseTime.mean.lte(100),
      global.responseTime.percentile(99.9).lte(100),
      global.successfulRequests.percent.gte(99)
    )
}
