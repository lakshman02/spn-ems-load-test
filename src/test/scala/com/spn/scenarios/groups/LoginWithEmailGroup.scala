package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.Constants
import com.spn.requests.{GetInitialConfigRequest,GetProfileRequest,LoginWithEmailRequest,UpdateProfileRequest,AccountSearchRequest,GenerateDeviceActivationCodeRequest,RegisterDeviceRequest}
import io.gatling.core.Predef._

object LoginWithEmailGroup {
  val invokeProfileApis= randomSwitch(
    50d -> exec(UpdateProfileRequest.updateProfile),
    50d -> exec(AccountSearchRequest.accountSearch)
  )
  val invokeTVRegistrationApis= randomSwitch(
    100d -> exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
  )
  // User Login Journey goes here - starts
  val doLoginWithEmail = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
      exec(GetInitialConfigRequest.getInitialConfig)
        .exec(LoginWithEmailRequest.LoginWithEmail)
        .exec(GetProfileRequest.getProfile)
        .exec(invokeProfileApis)
        .doIf(session => session.contains("channel") && (session("channel").equals("APPLE_TV"))){//We need to check for tv platforms here
          exec(invokeTVRegistrationApis)
        }
        .doIf(session => session.contains(Constants.RESP_ACTIVATION_CODE)){
        exec(RegisterDeviceRequest.registerDevice)
      }
    }
}
//APPLE_TV
//FIRE_TV
//SONY_ANDROID_TV
//XIAOMI_ANDROID_TV
//JIO_ANDROID_TV
//SONY_HTML_TV
//SAMSUNG_HTML_TV