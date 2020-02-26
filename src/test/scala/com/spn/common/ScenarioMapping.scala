package com.spn.common

import com.spn.scenarios.journey.{GetProfileJourneyScenario, UserJourneyMobileLoginAndGetSubscription,UserJourneyLoggedInWithEmailScenario,_}
import com.spn.scenarios.{AccountSearchScenario, ActiveSubscriptionScenario, AllSubscriptionsScenario, ContentDetailBundleScenario, CreateOTPScenario, GetInitialConfigScenario, GetMenuScenario, GetPageIdScenario, GetProductScenario, GetProfileScenario, GetULDScenario, IsCustomerEligibleForFreeTrialScenario, LoginScenario, LoginWithEmailScenario, VODDetailsScenario,_}

object ScenarioMapping {

  val scenarioNames = Map (

    // All single request mapping goes here
    "accountSearch" -> AccountSearchScenario.accountSearchScenario,
    "createPaymentQr" -> CreatePaymentQrScenario.createPaymentQrScenario,
    "createOTP" -> CreateOTPScenario.createOTPScenario,
    "getInitialConfig" -> GetInitialConfigScenario.getInitialConfigScenario,
    "getMenu" -> GetMenuScenario.getMenuScenario,
    "getPage" -> GetPageIdScenario.PageId,
    "getProfile" -> GetProfileScenario.getProfileScenario,
    "getULD" -> GetULDScenario.getULDScenario,
    "login" -> LoginScenario.loginScenario,
    "loginWithEmail" -> LoginWithEmailScenario.LoginWithEmailScenario,

    "getProduct" -> GetProductScenario .getProductScenario,
    "activeSubscription" -> ActiveSubscriptionScenario .activeSubscriptionScenario,
    "contentDetailBundle" -> ContentDetailBundleScenario .contentDetail_BundleScenario,
    "postGenericCoupon" -> PostGenericCouponScenario.scnGeneric_Coupon,
    "postSubscriptionHistory" -> PostSubscriptionHistoryScenario.SubscriptionHistory,

    "getVODDetails" -> VODDetailsScenario.vodDetailsScenario,
    "allSubscriptions" -> AllSubscriptionsScenario.getAllSubscriptionsScenario,
    "groupOfBundles" -> GroupOfBundlesScenario.groupOfBundlesScenario,
    "updateProfile" -> UpdateProfileScenario.updateProfileScenario,
    "isSubscribed" -> IsSubscribedScenario.isSubscribedScenario,
    "placeOrder" -> PlaceOrderScenario.placeOrderScenario,
    "subscriptionPayment" -> Subscription_PaymentScenario.subscription_PaymentScenario,
    "isCustomerEligibleForFreeTrial" -> IsCustomerEligibleForFreeTrialScenario.checkCustomerEligibleForFreeTrial,
    "productsByCoupon" ->ProductsByCouponScenario.productsByCouponScenario,
    "upgradablePlans" -> UpgradablePlansScenario.upgradablePlansScenario,
    "applycoupon" -> PostApplyCouponScenario.scnApplyCoupon,
    "prorateAmount" -> ProrateAmountScenario.prorateAmountScenario,
    "paymentMode" -> PaymentModesScenario.scnPaymentMode,
    "subscriptionRemove" -> SubscriptionRemoveScenario.subscriptionRemoveScenario,
    "changeService" -> ChangeServiceScenario.changeServiceScenario,
    "postSyncState" -> PostSyncStateScenario.postSyncStateScenario,
    "getSyncState" -> GetSyncStateScenario.getSyncStateScenario,
    "videoUrl" -> VideoUrlScenario.videoUrlScenario,
    "storeDropOffReason" -> StoreDropOffReasonScenario.storeDropOffReasonScenario,
    "getLAUrl" -> GetLAUrlScenario.getLAUrlScenario,

    // All Journey mapping goes here
    "getProfileJourney" -> GetProfileJourneyScenario.getProfileJourneyScenario,
    "UserJourneyLoggedInWithEmail" -> UserJourneyLoggedInWithEmailScenario.userJourneyLoggedInUser,
    "UserJourneyLoggedInWithMobile" -> UserJourneyMobileLoginAndGetSubscription.scnLoginAndSubscription,
    "UserJourneyLoggedInWithMobileAndGetProfile" -> UserJourneyMobileLoginAndGetProfile.scnLoginAndGetProfile,
    "UserJourneyEmailLoginAndGetProfile" -> UserJourneyEmailLoginAndGetProfile.scnLoginAndGetProfile,


  )
}