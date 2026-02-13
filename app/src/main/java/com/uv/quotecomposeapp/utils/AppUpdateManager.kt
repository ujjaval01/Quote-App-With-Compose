package com.uv.quotecomposeapp.utils

import android.app.Activity
import com.google.android.play.core.appupdate.*
import com.google.android.play.core.install.model.*

fun checkForAppUpdate(activity: Activity) {

    val appUpdateManager = AppUpdateManagerFactory.create(activity)

    val appUpdateInfoTask = appUpdateManager.appUpdateInfo

    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->

        if (appUpdateInfo.updateAvailability()
            == UpdateAvailability.UPDATE_AVAILABLE
            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
        ) {

            appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.IMMEDIATE,
                activity,
                100
            )
        }
    }
}
