package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.Constants
import com.spn.requests._
import io.gatling.core.Predef._

import scala.util.Random

object SettingsAndPreferenceGroup {
  // All possible feeders - encapsulated inside this group - starts

  val addSettingsFeeder = Iterator.continually(
    Map("videoStreamingQuality" -> Random.nextInt(15))
  )
  // All possible feeders - encapsulated inside this group - ends

  val invokeProfileApis = randomSwitch(
    2d -> exec(UpdateProfileRequest.updateProfile)
  )
  val invokeSettingAndPreferenceApis = randomSwitch(
    20d -> exec(UserPreferencesRequest.userPreferences),
    2d -> exec(DeleteSettingsRequest.Delete_Settings)
  )

  // User Login Journey goes here - starts
  val doSettingsAndPreferenceOperations = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
    exec(AddSettingsRequest.addSettings)
      .exec(GetSettingsRequest.getSettings)
      .exec(invokeProfileApis)
      .exec(invokeSettingAndPreferenceApis)
  }
}