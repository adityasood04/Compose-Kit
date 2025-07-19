package com.example.composekit.components.textfields

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

/**
 * DatePickerField
 *
 * A reusable composable text field that opens a native Android DatePickerDialog
 * when clicked. It shows the selected date in a formatted, non-editable text field.
 *
 * @param selectedDate The currently selected date to be displayed.
 * @param onDateSelected Callback invoked when the user selects a date.
 * @param label The label text for the text field. Defaults to "Select Date".
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    label: String = "Select Date"
) {
    val context = LocalContext.current
    val dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val calendar = Calendar.getInstance()

    val datePickerDialog = remember {
        DatePickerDialog(context).apply {
            setOnDateSetListener { _, year, month, day ->
                val picked = LocalDate.of(year, month + 1, day)
                onDateSelected(picked)
            }
        }
    }

    OutlinedTextField(
        value = selectedDate?.format(dateFormat) ?: "",
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() },
        enabled = true,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Pick date",
                modifier = Modifier.clickable { datePickerDialog.show() },

                )
        }
    )
}
