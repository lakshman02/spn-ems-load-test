package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.Constants
import com.spn.requests._
import io.gatling.core.Predef._

import scala.util.Random

object LoginWithEmailGroup {
  // All possible feeders - encapsulated inside this group - starts
  private def randomSerialNumber: String = {
    val r = Random
    "d6acc46e-5a09-d432-1afb-" + r.nextInt(2000000000)
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
    5d -> exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
  )

  // User Login Journey goes here - starts
  val doLoginWithEmail = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    //      exec(GetInitialConfigRequest.getInitialConfig) //as this is only needed for deep-link, no need of it here
    exec(LoginWithEmailRequest.LoginWithEmail)
    .exec(invokeTVRegistrationApis)
      .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
        exec(GetProfileRequest.getProfile)
          .exec(invokeProfileApis)
          .doIf(session => session.contains("channel") && (// Do this only for TV platforms
            session("channel").as[String].equals("APPLE_TV")
              || session("channel").as[String].equals("FIRE_TV")
              || session("channel").as[String].equals("SONY_ANDROID_TV")
              || session("channel").as[String].equals("XIAOMI_ANDROID_TV")
              || session("channel").as[String].equals("JIO_ANDROID_TV")
              || session("channel").as[String].equals("SONY_HTML_TV")
              || session("channel").as[String].equals("SAMSUNG_HTML_TV")
            )) {
              doIf(session => session.contains(Constants.RESP_ACTIVATION_CODE)) {
                exec(session => {
                  //Switch over from TV platform to any mobile device
                  val randomPhonePlatform = Array("ANDROID_PHONE", "IPAD", "IPHONE", "ANDROID_TAB")
                  session
                    .set("oldChannel", session("channel").as[String]) // Storing the old channel here
                    .set("channel", randomPhonePlatform(Random.nextInt(randomPhonePlatform.size - 1)))
                })
                  .exec(RegisterDeviceRequest.registerDevice)
                  .exec(session => {
                    //Switch over from TV platform to any mobile device and back to the original
                    session.set("channel", session("oldChannel").as[String]) // Restoring the old channel here
                  })
              }
          }
      }
  }
}