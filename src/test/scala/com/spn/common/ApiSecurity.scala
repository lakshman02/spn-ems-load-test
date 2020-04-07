package com.spn.common

import com.spn.config.Config
import com.spn.requests.GetTokenRequest
import io.gatling.core.Predef._

object ApiSecurity {

  val getToken = exec(

    /*
      enableAPISecurity = 1 ; New security token for every virtual user
      enableAPISecurity = 2 ; Security token will be passed from FrontLine using "singleSecurityToken"
      enableAPISecurity = 0 ; Dummy security token will be applied
     */
    doIfOrElse(session => (Config.enableAPISecurity == 1 && !session.contains(Constants.RESP_SECURITY_TOKEN))){
      tryMax(2, "tokenRetry") {
        exec(GetTokenRequest.getToken)
      }.exitHereIfFailed
    }{
      doIfOrElse(session => (Config.enableAPISecurity == 2 && !session.contains(Constants.RESP_SECURITY_TOKEN))){
        exec(session => {
          session.set(Constants.RESP_SECURITY_TOKEN, Config.singleSecurityToken)
        })
      } {
        exec(session => {
          session.set(Constants.RESP_SECURITY_TOKEN, Constants.RESP_SECURITY_TOKEN_DUMMY_VAL)
        })
      }
    })
}
