package com.sanmati.mall.designUi

import androidx.compose.runtime.Composable
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication

@Composable
actual fun ForceLogoutDialog(onLogout: () -> Unit) {
    val alert = UIAlertController.alertControllerWithTitle(
        title = "Session Expired",
        message = "Your session has expired. Please login again.",
        preferredStyle = UIAlertControllerStyleAlert
    )

    alert.addAction(
        UIAlertAction.actionWithTitle(
            title = "Logout",
            style = UIAlertActionStyleDefault
        ) { _ -> onLogout() }
    )

    // 🚫 No cancel button → not dismissible

    val rootVC = UIApplication.sharedApplication.keyWindow?.rootViewController
    rootVC?.presentViewController(alert, animated = true, completion = null)

}