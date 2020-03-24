package com.spn.common

import com.spn.config.Config
import com.spn.requests.GetTokenRequest
import io.gatling.core.Predef._

object ApiSecurity {

  val getToken = exec(
    doIfOrElse(session => (Config.enableAPISecurity == 1 && !session.contains(Constants.RESP_SECURITY_TOKEN))){
      exec(GetTokenRequest.getToken)
    }{
      exec(session => {
        session.set(Constants.RESP_SECURITY_TOKEN, Constants.RESP_SECURITY_TOKEN_DUMMY_VAL)
      })
    })
}
