package com.example.composekit.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composekit.common.LabeledComponent
import com.example.composekit.common.SectionHeader
import com.example.composekit.components.textfields.AutoGrowingTextField
import com.example.composekit.components.textfields.CardNumberField
import com.example.composekit.components.textfields.DatePickerField
import com.example.composekit.components.textfields.ErrorTextField
import com.example.composekit.components.textfields.LeadingIconTextField
import com.example.composekit.components.textfields.OtpInputField
import com.example.composekit.components.textfields.PasswordStrengthField
import com.example.composekit.components.textfields.PasswordTextField
import com.example.composekit.components.textfields.PhoneNumberTextField
import com.example.composekit.components.textfields.SearchBarTextField
import com.example.composekit.components.textfields.ValidationTextField
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TextFieldsDemoScreen() {
    val state = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("19 July 2025") }

    var validatedText by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var otpValue by remember { mutableStateOf("") }

    var counterField by remember { mutableStateOf("") }
    var securePassword by remember { mutableStateOf("") }
    var bioText by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var customDate by remember { mutableStateOf<LocalDate?>(null) }

    val isEmailError = email.isNotEmpty() && !email.contains("@")
    val isValid = if (validatedText.isNotEmpty()) validatedText.length >= 5 else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state)
    ) {
        Text("TextField Components", style = MaterialTheme.typography.headlineSmall)

        SectionHeader("Standard TextFields")
        Spacer(modifier = Modifier.height(12.dp))

        LabeledComponent(label = "Basic Text Field") {
            BasicOutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = "Full Name"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LabeledComponent(label = "Password Field") {
            PasswordTextField(
                password = password,
                onPasswordChange = { password = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Password Strength Meter") {
            PasswordStrengthField(
                password = securePassword,
                onPasswordChange = { securePassword = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Text Field with Error") {
            ErrorTextField(
                value = email,
                onValueChange = { email = it },
                isError = isEmailError,
                errorMessage = "Invalid email format"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Text Field with Leading Icon") {
            LeadingIconTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader("Custom TextFields")

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "OTP Input Field") {
            OtpInputField(
                otpValue = otpValue,
                onOtpChange = { otpValue = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Validation TextField") {
            ValidationTextField(
                value = validatedText,
                onValueChange = { validatedText = it },
                label = "Must be 5+ characters",
                isValid = isValid
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Search Bar") {
            SearchBarTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Phone Number Field") {
            PhoneNumberTextField(
                number = phoneNumber,
                onNumberChange = { phoneNumber = it }
            )
        }


        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Auto Growing Text Area") {
            AutoGrowingTextField(
                value = bioText,
                onValueChange = { bioText = it },
                label = "Enter Bio"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Card Number Field") {
            CardNumberField(
                value = cardNumber,
                onValueChange = { cardNumber = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        LabeledComponent(label = "Date Picker Field") {
            DatePickerField(
                selectedDate = customDate,
                onDateSelected = { customDate = it }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun BasicOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}