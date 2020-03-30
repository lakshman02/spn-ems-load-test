package com.spn.scenarios.groups

import com.spn.common.Constants
import com.spn.requests.{AccountSearchRequest, GenerateDeviceActivationCodeRequest, GetInitialConfigRequest, GetProfileRequest, LoginWithEmailRequest, RegisterDeviceRequest, UpdateProfileRequest}
import io.gatling.core.Predef._

import scala.util.Random

object LoginWithEmailGroup {
  val invokeProfileApis= randomSwitch(
    2d -> exec(UpdateProfileRequest.updateProfile),
    2d -> exec(AccountSearchRequest.accountSearch)
  )
  val invokeTVRegistrationApis= randomSwitch(
    2d -> exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
  )

  // User Login Journey goes here - starts
  val doLoginWithEmail = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
      exec(GetInitialConfigRequest.getInitialConfig)
        .exec(LoginWithEmailRequest.LoginWithEmail)
        .exec(GetProfileRequest.getProfile)
        .exec(invokeProfileApis)
        .doIf(session => session.contains("channel") && ( // Do this only for TV platforms
          session("channel").as[String].equals("APPLE_TV")
          || session("channel").as[String].equals("FIRE_TV")
          || session("channel").as[String].equals("SONY_ANDROID_TV")
          || session("channel").as[String].equals("XIAOMI_ANDROID_TV")
          || session("channel").as[String].equals("JIO_ANDROID_TV")
          || session("channel").as[String].equals("SONY_HTML_TV")
          || session("channel").as[String].equals("SAMSUNG_HTML_TV")
          )){
          exec(invokeTVRegistrationApis)
            .doIf(session => session.contains(Constants.RESP_ACTIVATION_CODE)){
              exec(session => {
                val randomPhonePlatform = Array("ANDROID_PHONE","IPAD","IPHONE","ANDROID_TAB") //Switch over from TV platform to any mobile device
                session.set("channel", randomPhonePlatform(Random.nextInt(randomPhonePlatform.size - 1)))
              })
              .exec(RegisterDeviceRequest.registerDevice)
            }
        }
    }
}