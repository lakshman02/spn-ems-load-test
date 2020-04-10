package com.spn.scenarios.groups

import com.jayway.jsonpath.JsonPath
import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PageDetailScreen.setTheUrlIdToSession
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object DeviceManagementGroup {

  def setSerialNoSession(session: Session): Session = {

    val getDeviceResponse = session(Constants.RESP_GET_DEVICE_COMPLETE_RESPONSE).as[String]
    println(s"\ngetDeviceResponse : $getDeviceResponse")

    var expression = ""

    expression = "$.[?(@.serialNo =~ /"+Constants.SB_TEST_DEVICE_SERIAL_NUMBER_PREFIX+".*/)].serialNo"
    println(s"\nExpression : $expression")

    val context = JsonPath.parse(getDeviceResponse)
    val serialNum = context.read[JSONArray](expression)

    // Cherry picking a serial number for further use
    var finalSerialNumNavigateTo = ""
    if (serialNum != null && serialNum.size() == 1) {
      finalSerialNumNavigateTo = serialNum.get(0).toString
    }
    else if (serialNum != null && serialNum.size() > 1) {
      val size = serialNum.size()
      finalSerialNumNavigateTo = serialNum.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nFinal serialNum to Navigate To for  is : $finalSerialNumNavigateTo")

    if (finalSerialNumNavigateTo != null && !finalSerialNumNavigateTo.isEmpty) {
      session.set(Constants.RESP_DEVICE_SERIAL_NUMBER, finalSerialNumNavigateTo)
    }
    else {
      print("Did not find serialNum")
      session
    }
  }

  val doRemoveDevice = exec(session =>{
    setSerialNoSession(session)
  }).doIf(session => session.contains(Constants.RESP_DEVICE_SERIAL_NUMBER)) {
    exec(RemoveDevicesRequest.removeDevicesRequest)
  }

  val doDeviceManagementOperations = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Device Management - Logged-In User - Channel - ${channel}"){
        exec(GetDevicesRequest.getDevicesRequest)
        .exec(doRemoveDevice)
    }
  }
}