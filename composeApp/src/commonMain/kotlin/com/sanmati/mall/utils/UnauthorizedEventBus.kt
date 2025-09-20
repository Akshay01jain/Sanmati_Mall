package com.sanmati.mall.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.designUi.ForceLogoutDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object UnauthorizedEventBus {
    private val _unauthorizedEvent = MutableSharedFlow<Unit>()
    val unauthorizedEvent = _unauthorizedEvent.asSharedFlow()

    suspend fun emitUnauthorized() {
        _unauthorizedEvent.emit(Unit)
    }
}


fun handleUnauthorized() {
    // Clear token
    SharedPreference.clear()
    // Emit unauthorized event
    CoroutineScope(Dispatchers.Main).launch {
        UnauthorizedEventBus.emitUnauthorized()
    }
}

@Composable
fun AppRoot(navController: NavController) {
    val scope = rememberCoroutineScope()
    val unauthorizedEvent = UnauthorizedEventBus.unauthorizedEvent.collectAsState(initial = null)
    unauthorizedEvent.value?.let {
        ForceLogoutDialog {
            navController.navigate("login") {
                popUpTo(0)
            }
        }
    }
}

