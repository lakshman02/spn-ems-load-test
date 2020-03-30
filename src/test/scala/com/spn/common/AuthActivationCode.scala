package com.spn.common

import com.spn.requests.{LoginWithEmailRequest,GenerateDeviceActivationCodeRequest}
import io.gatling.core.Predef._

object AuthActivationCode {

  val getAuthToken = exec(
    doIfOrElse(session => (!session.contains(Constants.RESP_AUTH_TOKEN))){
        exec(LoginWithEmailRequest.LoginWithEmail)
    }{
      exec(session => {
        session.set(Constants.RESP_AUTH_TOKEN, Constants.RESP_SECURITY_TOKEN_DUMMY_VAL)
      })
    })
  val getActivationCode = exec(
    doIfOrElse(session => !session.contains(Constants.RESP_ACTIVATION_CODE)){
        exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
    }{
      exec(session => {
        session.set(Constants.RESP_ACTIVATION_CODE, Constants.RESP_ACTIVATION_CODE_DUMMY_VAL)
      })
    })
}
