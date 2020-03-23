package com.spn.simulations

import com.spn.common.{InjectionStrategy, ScenarioMapping}
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.structure.PopulationBuilder
import org.json.{JSONArray, JSONObject}

import scala.collection.mutable.ArraySeq
import scala.io.{BufferedSource, Source}

class SPNAPISimulationWithCommonRampUp extends Simulation {

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

    val testList = new JSONObject(rawTestList)
    val commonRampUp = testList.getJSONArray("commonRampUp")
    val testCases = testList.getJSONArray("cases")
    val waitBetweenTests = testList.getInt("waitBetweenTests")
    var testStartAt = 0

    val injectStepList = new ArraySeq[OpenInjectionStep](commonRampUp.length())
    for(j <- 0 until commonRampUp.length()) {
      //For each injection step
      val step = commonRampUp.getJSONObject(j)
      val stepType = step.getString("type")
      val stepArgs = step.getJSONArray("args")
      injectStepList(j) = InjectionStrategy.getInjectionStep(stepType, stepArgs)
    }

    val scnList = new ArraySeq[PopulationBuilder](testCases.length())

    var injectStepListTemp = injectStepList
    for(i <- 0 until testCases.length()) {

      // For adding default wait time between tests - starts
      if(i != 0) {
        testStartAt = testStartAt + waitBetweenTests
      }
      val jsonArray = new JSONArray();
      jsonArray.put(testStartAt)
      injectStepListTemp = new ArraySeq[OpenInjectionStep](commonRampUp.length() + 1)
      injectStepListTemp(0) = InjectionStrategy.getInjectionStep("nothingFor",  jsonArray)
      injectStepList.copyToArray(injectStepListTemp.array.asInstanceOf[Array[Any]], 1)

      // For adding default wait time between tests - ends

      //For each scenario
      val testCase = testCases.getString(i)
      scnList(i) = ScenarioMapping.scenarioNames(testCase).inject(injectStepListTemp)
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
