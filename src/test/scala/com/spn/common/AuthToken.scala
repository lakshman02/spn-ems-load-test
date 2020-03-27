package com.spn.common

import com.spn.requests.LoginWithEmailRequest
import io.gatling.core.Predef._

object AuthToken {

  val getAuthToken = exec(
    doIfOrElse(session => (!session.contains(Constants.RESP_AUTH_TOKEN))){
        exec(LoginWithEmailRequest.LoginWithEmail)
    }{
      exec(session => {
        session.set(Constants.RESP_AUTH_TOKEN, Constants.RESP_SECURITY_TOKEN_DUMMY_VAL)
      })
    })
}
