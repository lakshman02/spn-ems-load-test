package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.{CommonFeedFiles, Constants, Utils}
import com.spn.requests._
import io.gatling.core.Predef._

import scala.util.Random

object LoginWithEmailGroup {
  // All possible feeders - encapsulated inside this group - starts
  private def randomSerialNumber: String = {
    val r = Random
    Constants.SB_TEST_DEVICE_SERIAL_NUMBER_PREFIX + r.nextInt(2000000000)
  }

  private def randomModelNumber: String = {
    val r = Random
    "abc-" + r.nextInt(1000)
  }

  val feederDeviceDetails = Array(
    Map("serialNo" -> randomSerialNumber, "deviceName" -> "webClient", "deviceModelNumber" -> randomModelNumber, "deviceType" -> "webClient")
  ).circular

  val dateOfBirthFeeder = Iterator.continually(
    Map("dateOfBirth" -> ThreadLocalRandom.current().nextInt(1551081657, 1582617662))
  )

  val genderFeeder = Array(
    Map("gender" -> "Male"),
    Map("gender" -> "Female")
  ).random

  val pinCodeFeeder = Iterator.continually(
    Map("pincode" -> ThreadLocalRandom.current().nextInt(500072, 600000))
  )
  // All possible feeders - encapsulated inside this group - ends

  val invokeProfileApis= randomSwitch(
    2d -> exec(UpdateProfileRequest.updateProfile),
    2d -> exec(AccountSearchRequest.accountSearch)
  )
  val invokeTVRegistrationApis= randomSwitch(
    2d -> exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
  )

  // User Login Journey goes here - starts
  val doLoginWithEmail = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    //      exec(GetInitialConfigRequest.getInitialConfig) //as this is only needed for deep-link, no need of it here
    exec(LoginWithEmailRequest.LoginWithEmail)
      .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
        exec(GetProfileRequest.getProfile)
          .exec(invokeProfileApis)
          .doIf(session => Utils.checkIfTVPlatform(session)) {
            exec(invokeTVRegistrationApis)
            .doIf(session => session.contains(Constants.RESP_ACTIVATION_CODE)) {
                exec(session => {
                  //Switch over from TV platform to any mobile device
                  val randomPhonePlatform = Array("ANDROID_PHONE", "IPAD", "IPHONE", "ANDROID_TAB")
                  session
                    .set("oldChannel", session("channel").as[String]) // Storing the old channel here
                    .set("channel", randomPhonePlatform(Random.nextInt(randomPhonePlatform.size - 1)))
                })
                  .exec(RegisterDeviceRequest.registerDevice)
                  .randomSwitch(2d -> exec(DeviceManagementGroup.doDeviceManagementOperations))
                  .exec(session => {
                    //Switch over from TV platform to any mobile device and back to the original
                    session.set("channel", session("oldChannel").as[String]) // Restoring the old channel here
                  })
              }
          }
      }
  }

  val doJustLoginWithEmail = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    group("Doing the email login alone!") {
      feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
        .feed(CommonFeedFiles.dateTimeFeeder)
        .feed(dateOfBirthFeeder)
        .feed(feederDeviceDetails)
        .feed(genderFeeder)
        .feed(pinCodeFeeder)
        .exec(LoginWithEmailRequest.LoginWithEmail)
    }
  }
}