package com.example.composekit.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.Nightlight
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.SentimentDissatisfied
import androidx.compose.material.icons.rounded.SentimentVerySatisfied
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Diary Component
 * Created by Aditya Sood
 * GitHub: https://github.com/adityasood04/
 *
 * Reuse allowed under MIT License with attribution.
 */


// data class for storing colors respective to a mood
data class Mood(
    val label: String,
    val icon: ImageVector,
    val primaryColor: Color,
    val secondaryColor: Color,
    val textColor: Color = Color.White
)

// objects of some moods with their respective colors
private val moodOptions = listOf(
    Mood(
        "Happy", Icons.Rounded.SentimentVerySatisfied,
        Color(0xFF81C784), Color(0xFF4CAF50)
    ),
    Mood(
        "Calm", Icons.Rounded.Spa,
        Color(0xFF80DEEA), Color(0xFF4DD0E1)
    ),
    Mood(
        "Focused", Icons.Rounded.Lightbulb,
        Color(0xFF90CAF9), Color(0xFF64B5F6)
    ),
    Mood(
        "Sad", Icons.Rounded.SentimentDissatisfied,
        Color(0xFFA1887F), Color(0xFF8D6E63)
    ),
    Mood(
        "Stressed", Icons.Rounded.FlashOn,
        Color(0xFFFFB74D), Color(0xFFFFA726)
    ),
    Mood(
        "Tired", Icons.Rounded.Nightlight,
        Color(0xFF9575CD), Color(0xFF7E57C2)
    ),
    Mood(
        "Creative", Icons.Rounded.Palette,
        Color(0xFFF48FB1), Color(0xFFF06292)
    )
)


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MoodDiaryScreen() {
    var selectedMood by remember { mutableStateOf(moodOptions[0]) }
    var diaryText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val textColor = remember(selectedMood) {
        val bgColor = selectedMood.primaryColor
        val brightness = (bgColor.red * 299 + bgColor.green * 587 + bgColor.blue * 114) / 1000
        if (brightness > 0.6) Color.Black else Color.White
    }

    val buttonBgColor = remember(selectedMood) {
        textColor.copy(alpha = 0.2f)
    }

    val buttonTextColor = remember(selectedMood) {
        textColor
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Diary Entry",
                        color = textColor,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = textColor
                ),
                actions = {
                    if (diaryText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Clear",
                            tint = textColor.copy(alpha = 0.7f),
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .clickable {
                                    diaryText = ""
                                    focusManager.clearFocus()
                                }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            selectedMood.primaryColor,
                            selectedMood.secondaryColor
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MoodSelector(
                    moods = moodOptions,
                    selectedMood = selectedMood,
                    onMoodSelected = { selectedMood = it },
                    textColor = textColor
                )

                Box(modifier = Modifier.weight(1f)) {
                    DiaryEntry(
                        text = diaryText,
                        onTextChange = { diaryText = it },
                        currentMood = selectedMood,
                        focusRequester = remember { FocusRequester() }
                    )
                }

                SaveButton(
                    onClick = { },
                    enabled = true,
                    backgroundColor = buttonBgColor,
                    textColor = buttonTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )

            }
        }
    }

}


//Mood selector - lazy row component
@Composable
fun MoodSelector(
    moods: List<Mood>,
    selectedMood: Mood,
    onMoodSelected: (Mood) -> Unit,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(selectedMood) {
        val index = moods.indexOf(selectedMood)
        if (index != -1) {
            lazyListState.animateScrollToItem(index)
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        itemsIndexed(moods) { _, mood ->
            val isSelected = mood == selectedMood
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) textColor.copy(alpha = 0.2f) else Color.Transparent,
                animationSpec = spring(stiffness = Spring.StiffnessLow),
                label = "MoodBackgroundAnimation"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(72.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onMoodSelected(mood) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(backgroundColor)
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = textColor.copy(alpha = if (isSelected) 0.5f else 0f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = mood.icon,
                        contentDescription = mood.label,
                        modifier = Modifier.size(28.dp),
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mood.label,
                    color = textColor,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                )
            }
        }
    }
}

// Writing area component
@Composable
fun DiaryEntry(
    text: String,
    onTextChange: (String) -> Unit,
    currentMood: Mood,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember {
        SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(Date())
    }
    val keyboard = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFECE7DE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = currentMood.primaryColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = currentMood.icon,
                    contentDescription = currentMood.label,
                    tint = currentMood.primaryColor,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = currentMood.label,
                    color = currentMood.primaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = formattedDate,
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(
                value = text,
                onValueChange = { onTextChange(it) },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic
                ),
                cursorBrush = SolidColor(Color.Black),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboard?.hide() }),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopStart
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = "How are you feeling today?",
                                color = Color.Black.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}


@Composable
fun SaveButton(
    onClick: () -> Unit,
    enabled: Boolean,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.1f),
            disabledContentColor = textColor.copy(alpha = 0.3f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        )
    ) {
        Text(
            text = "Save Entry",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

