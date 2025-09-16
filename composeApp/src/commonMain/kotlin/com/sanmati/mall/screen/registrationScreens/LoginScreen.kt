package com.sanmati.mall.screen.registrationScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MobileOff
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.connections.APIsCalling
import com.sanmati.mall.connections.ApiEvents
import com.sanmati.mall.connections.KtorClientFactory
import com.sanmati.mall.designUi.CommonDialog
import com.sanmati.mall.designUi.CustomButton
import com.sanmati.mall.designUi.FilledTextField
import com.sanmati.mall.model.LoginRequest
import com.sanmati.mall.theme.Background
import com.sanmati.mall.theme.OnSurface
import com.sanmati.mall.theme.OnSurfaceVariant
import com.sanmati.mall.theme.Primary
import com.sanmati.mall.theme.PrimaryDark
import com.sanmati.mall.theme.Surface
import com.sanmati.mall.utils.DeviceConfiguration
import com.sanmati.mall.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.painterResource
import sanmatimall.composeapp.generated.resources.Res
import sanmatimall.composeapp.generated.resources.sm_logo
import kotlin.time.Duration

@Composable
fun LoginScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    /* Scaffold(
         modifier = Modifier.fillMaxSize(),
         snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // ✅ Host snackbar here
         contentWindowInsets = WindowInsets.statusBars
     ) { innerPadding ->

         Box(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(innerPadding)
         ) {
             val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
             val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
             val (cardWidthFraction, cardHeightFraction) = when (deviceConfiguration) {
                 DeviceConfiguration.MOBILE_PORTRAIT -> 0.9f to 0.85f
                 DeviceConfiguration.MOBILE_LANDSCAPE -> 0.8f to 0.85f
                 DeviceConfiguration.TABLET_PORTRAIT -> 0.9f to 0.8f
                 DeviceConfiguration.TABLET_LANDSCAPE -> 0.8f to 0.85f
                 DeviceConfiguration.DESKTOP -> 0.7f to 0.8f
             }

             Box(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(innerPadding)
                     .padding(WindowInsets.safeDrawing.asPaddingValues()),
                 contentAlignment = Alignment.Center
             ) {
                 Card(
                     modifier = Modifier
                         .fillMaxWidth(cardWidthFraction)
                         .fillMaxHeight(cardHeightFraction),
                     shape = RoundedCornerShape(16.dp),
                     elevation = CardDefaults.cardElevation(12.dp),
                     colors = CardDefaults.cardColors(containerColor = Surface)
                 ) {
                     LoginUI(
                         navController = navController,
                         deviceConfiguration = deviceConfiguration,
                         snackbarHostState = snackbarHostState,
                         scope = scope
                     )

                 }
             }
         }
     }*/

    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginHeader()
            Spacer(modifier = Modifier.height(24.dp))
            LoginForm(
                navController,
                mobileNumber, { mobileNumber = it },
                password, { password = it },
                passwordVisible, { passwordVisible = !passwordVisible },
                isLoading, { isLoading = it },
                errorMessage,
                snackbarHostState,
                scope
            )
        }
    }
}

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.sm_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = OnSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginForm(
    navController: NavController,
    mobileNumber: String,
    onMobileChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onTogglePassword: () -> Unit,
    isLoading: Boolean,
    onLoadingChange: (Boolean) -> Unit,
    errorMessage: String?,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    viewModel: LoginViewModel = remember { LoginViewModel() },
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    // ✅ Collect API events only once
    LaunchedEffect(Unit) {
        viewModel.apiEvents.collect { event ->
            when (event) {
                is ApiEvents.Success -> {
                    snackbarHostState.showSnackbar(event.message)
                    onLoadingChange(false)
                    navController.navigate("admin")
                }
                is ApiEvents.Error -> {
                    snackbarHostState.showSnackbar(event.message)
                    onLoadingChange(false)
                }
            }
        }
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Mobile field
        FilledTextField(
            text = mobileNumber,
            onValidate = onMobileChange,
            labelText = "Mobile Number",
            placeholderText = "0000000000",
            singleLine = true,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            leadingIcon = { Icon(Icons.Rounded.Phone, contentDescription = null, tint = OnSurfaceVariant) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        FilledTextField(
            text = password,
            onValidate = onPasswordChange,
            labelText = "Password",
            placeholderText = "********",
            singleLine = true,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyBoardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null, tint = OnSurfaceVariant) },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                IconButton(onClick = onTogglePassword) {
                    Icon(imageVector = icon, contentDescription = null, tint = PrimaryDark)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ Button with loading state
        CustomButton(
            text = if (isLoading) "" else "Login",
            onClick = {
                if (mobileNumber.length != 10 || password.length < 3) {
                    scope.launch {
                        snackbarHostState.showSnackbar("Invalid Credentials")
                    }
                } else {
                    onLoadingChange(true)
                    viewModel.performLogin(mobileNumber, password) // trigger API
                }
            },
            isLoading = isLoading,
            enabled = !isLoading && mobileNumber.isNotBlank() && password.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
