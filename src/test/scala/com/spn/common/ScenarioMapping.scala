package com.spn.common

import com.spn.scenarios.journey.GetProfileJourneyScenario

import com.spn.scenarios.{AccountSearchScenario, CreateOTPScenario, GetInitialConfigScenario, GetMenuScenario,
  GetPageIdScenario, GetProfileScenario, GetULDScenario, LoginScenario, LoginWithEmailScenario, VODDetailsScenario,
  AllSubscriptionsScenario,GetProductScenario,ActiveSubscriptionScenario,ContentDetailBundleScenario,_}



object ScenarioMapping {

  val scenarioNames = Map (
    "accountSearch" -> AccountSearchScenario.accountSearchScenario,
    "createOTP" -> CreateOTPScenario.createOTPScenario,
    "getInitialConfig" -> GetInitialConfigScenario.getInitialConfigScenario,
    "getMenu" -> GetMenuScenario.getMenuScenario,
    "getPage" -> GetPageIdScenario.PageId,
    "getProfile" -> GetProfileScenario.getProfileScenario,
    "getULD" -> GetULDScenario.getULDScenario,
    "login" -> LoginScenario.loginScenario,
    "loginWithEmail" -> LoginWithEmailScenario.LoginWithEmailScenario,
    "getProfileJourney" -> GetProfileJourneyScenario.getProfileJourneyScenario,
    "getProduct" -> GetProductScenario .getProductScenario,
    "activeSubscription" -> ActiveSubscriptionScenario .activeSubscriptionScenario,
    "ContentDetailBundle" -> ContentDetailBundleScenario .contentDetail_BundleScenario,
    "PostGenericCoupon" -> PostGenericCouponScenario.scnGeneric_Coupon,
    "PostSubscriptionHistory" -> PostSubscriptionHistoryScenario.SubscriptionHistory,
    "getVODDetails" -> VODDetailsScenario.vodDetailsScenario,
    "allSubscriptions" -> AllSubscriptionsScenario.getAllSubscriptionsScenario,
    "groupOfBundles" -> GroupOfBundlesScenario.groupOfBundlesScenario,
    "updateProfile" -> UpdateProfileScenario.updateProfileScenario,
    "updateProfile" -> UpdateProfileScenario.updateProfileScenario,
    "isSubscribed" -> IsSubscribedScenario.isSubscribedScenario,
      "subscriptionpaymenturl"-> Subscription_PaymentScenario.subscription_PaymentScenario
  )
}