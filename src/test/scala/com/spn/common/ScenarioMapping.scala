package com.spn.common

import com.spn.scenarios.journey.complete.SonyLivCompleteUserJourney
import com.spn.scenarios.journey.{GetProfileJourneyScenario, UserJourneyLoggedInWithEmailScenario, UserJourneyMobileLoginAndGetSubscription, _}
import com.spn.scenarios.{AccountSearchScenario, ActiveSubscriptionScenario, AllSubscriptionsScenario, ContentDetailBundleScenario, CreateOTPScenario, GetInitialConfigScenario, GetMenuScenario, GetPageIdScenario, GetProductScenario, GetProfileScenario, GetULDScenario, IsCustomerEligibleForFreeTrialScenario, LoginScenario, LoginWithEmailScenario, VODDetailsScenario, _}

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
    "genericCoupon" -> PostGenericCouponScenario.scnGeneric_Coupon,
    "SubscriptionHistory" -> PostSubscriptionHistoryScenario.SubscriptionHistory,
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
    "epgReminderDelete" -> EPGReminderDeleteScenario.EPGReminderDeleteScenario,
    "epgReminderGetList"  -> EpgReminderGetListScenario.scnEpg_reminder_GetList,
    "epgReminderDelete" -> EPGReminderDeleteScenario.EPGReminderDeleteScenario,
    "registerDevice" -> RegisterDeviceScenario.registerDeviceScenario,
    // All Journey mapping goes here
    "videoUrl" -> VideoUrlScenario.videoUrlScenario,
    "storeDropOffReason" -> StoreDropOffReasonScenario.storeDropOffReasonScenario,
    "showDetail" -> ShowDetailScenario.showDetailScenario,
    "getUserPlayBackPreviewDetails" -> GetUserPlayBackPreviewDetailsScenario.getUserPlayBackPreviewDetailsScenario,
    "deleteList" -> DeleteListScenario.deleteListScenario,
    "addSearchedItem" -> AddSearchedItemScenario.scnAddSearchedItem,
    "episodeDetails" -> EpisodeIdDetailScenario.scnEpisodeIdDetails,
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
    "previous" -> PreviousScenario.scnPreviousContent,
    "getSettings" -> GetSettingsScenario.getSettingsScenario,
    "addReminder" -> AddReminderScenario.addReminderScenario,
    "getReminders" -> GetRemindersScenario.getRemindersScenario,
    "getPrevious" -> PreviousScenario.scnPreviousContent,
    "deleteReminder" -> DeleteReminderScenario.deleteReminderScenario,
    "deleteSettings" -> DeleteSettingsScenario.scnDeleteSetting,
    "userPreferences" -> UserPerferencesScenario.scnUserPerferences,
    "getDevices" -> GetDevicesScenario.getDevicesScenario,
    "trayRecommendationCatchMedia" -> TrayRecommendationCatchMediaScenario.trayRecommendationCatchMediaScenario,
    "trayRecommendationRecosense" -> TrayRecommendationRecosenseScenario.trayRecommendationRecosenseScenario,
    "userRecommendationDetails" -> UserRecommendationDetailsScenario.userRecommendationDetailsScenario,
    "userRecommendationLanding" -> UserRecommendationLandingScenario.scnuserRecommendationLandingScenario,
    "removeDevices" -> RemoveDevicesScenario.removeDevicesScenario,
    "deleteAllSearchHistory" -> DeleteAllSearchHistoryScenario.scnDeleteAllSearchHistory,
    "registerDevice" -> RegisterDeviceScenario.registerDeviceScenario,
    "logout" -> LogoutScenario.logoutScenario,
    "subscriptionOrderStatusDate" -> SubscriptionOrderStatusDateScenario.subscriptionOrderStatusDateScenario,
    "previewAdd" -> PreviewAddScenario.previewAddScenario,
    "subscriptionOrderStatusUser" -> SubscriptionOrderStatusUserScenario.subscriptionOrderStatusUserScenario,


    // All Journey mapping goes here

    // Complete user journey
    "SonyLivCompleteUserJourney" -> SonyLivCompleteUserJourney.doSonyLivCompleteUserJourney,



    "getProfileJourney" -> GetProfileJourneyScenario.getProfileJourneyScenario,
    "UserJourneyLoggedInWithEmail" -> UserJourneyLoggedInWithEmailScenario.userJourneyLoggedInUser,
    "UserJourneyLoggedInWithMobile" -> UserJourneyMobileLoginAndGetSubscription.scnLoginAndSubscription,
    "UserJourneyLoggedInWithMobileAndGetProfile" -> UserJourneyMobileLoginAndGetProfile.scnLoginAndGetProfile,
    "UserJourneyEmailLoginAndGetProfile" -> UserJourneyEmailLoginAndGetProfile.scnLoginAndGetProfile,

    "loggedInUserAppLaunchScenario" -> LoggedInUserAppLaunchScenario.loggedInUserAppLaunchScenario,
    "GuestUserAppLaunchScenario" -> GuestUserAppLaunchScenario.guestUserAppLaunchScenario,
  "guestUserDetailScreenScenario" -> GuestUserDetailScreenScenario.guestUserDetailScreenScenario,
  "guestUserHomeScreenScenario" -> GuestUserHomeScreenScenario.guestUserHomeScreenScenario


  )
}