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
    "processRazorPayOrder" -> ProcessRazorPayOrderScenario.processRazorPayOrderScenario,
    "addSettings" -> AddSettingsScenario.addSettingsScenario,
    "epgReminder" -> EPGReminderScenario.EPGReminderScenario,
    "epgReminderGetList"  -> EpgReminderGetListScenario.scnEpg_reminder_GetList,
    // All Journey mapping goes here
    "videoUrl" -> VideoUrlScenario.videoUrlScenario,
    "storeDropOffReason" -> StoreDropOffReasonScenario.storeDropOffReasonScenario,
    "showDetail" -> ShowDetailScenario.showDetailScenario,
    "getUserPlayBackPreviewDetails" -> GetUserPlayBackPreviewDetailsScenario.getUserPlayBackPreviewDetailsScenario,
    "deleteList" -> DeleteListScenario.deleteListScenario,
    "addSearchedItem" -> AddSearchedItemScenario.scnAddSearchedItem,
    "episodeIdDetails" -> EpisodeIdDetailScenario.scnEpisodeIdDetails,
    "bundleId" -> BundleIdScenario.scnbundle,
    "getList" -> GetListScenario.getListScenario,
    "movieDetail" -> MovieDetailScenario.movieDetailScenario,
    "addList" -> AddListScenario.addListScenario,
    "getToken" -> GetTokenScenario.getTokenScenario,
    "getLAUrl" -> GetLAUrlScenario.getLAUrlScenario,
    "getSearchHistory" -> GetSearchHistoryScenario.getSearchHistoryScenario,
    "traySearch" -> TraySearchScenario.traySearchScenario,
    "traySearchVOD" -> TraySearchVODScenario.traySearchVODScenario,
    "generateDeviceActivationCode" -> GenerateDeviceActivationCodeScenario.generateDeviceActivationCodeScenario,
    "searchDescription" -> SearchDescriptionScenario.searchDescriptionScenario,
    "deleteSearchHistory" -> DeleteSearchHistoryScenario.scnDeleteSearchHistory,
    "createRazorPayOrder" -> CreateRazorPayOrderScenario.createRazorPayOrderScenario,
    "addXDR" -> AddXdrScenario.addXdrScenario,
    "next" -> NextScenario.nextScenario,
    "deleteXdr" ->DeleteXdrScenario.deleteXdrScenario,
    "getXDR" -> GetXDRScenario.scn_Get_XDR,
    "nextAndPrevious" -> NextAndPreviousScenario.nextAndPreviousScenario,
    "getDRMDeviceId" -> GetDRMDeviceIdScenario.getDRMDeviceIdScenario,
    "getPrevious" -> PreviousScenario.scnPreviousContent,
    "getDevices" -> GetDevicesScenario.getDevicesScenario,
    "trayRecommendationCatchMedia" -> TrayRecommendationCatchMediaScenario.trayRecommendationCatchMediaScenario,
    "trayRecommendationRecosense" -> TrayRecommendationRecosenseScenario.trayRecommendationRecosenseScenario,
    "userRecommendationDetails" -> UserRecommendationDetailsScenario.userRecommendationDetailsScenario,


    // All Journey mapping goes here
    "getProfileJourney" -> GetProfileJourneyScenario.getProfileJourneyScenario,
    "UserJourneyLoggedInWithEmail" -> UserJourneyLoggedInWithEmailScenario.userJourneyLoggedInUser,
    "UserJourneyLoggedInWithMobile" -> UserJourneyMobileLoginAndGetSubscription.scnLoginAndSubscription,
    "UserJourneyLoggedInWithMobileAndGetProfile" -> UserJourneyMobileLoginAndGetProfile.scnLoginAndGetProfile,
    "UserJourneyEmailLoginAndGetProfile" -> UserJourneyEmailLoginAndGetProfile.scnLoginAndGetProfile

  )
}