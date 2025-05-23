package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hemanth.S3083018.onlinequiz.quizData.QuizDatabaseHelper
import hemanth.S3083018.onlinequiz.quizData.QuizQuestion
import hemanth.S3083018.onlinequiz.quizData.QuizResults
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TakeQuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizScreen()
        }
    }
}

@Composable
fun QuizScreen() {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var showScoreScreen by remember { mutableStateOf(false) }
    var showAnswerScreen by remember { mutableStateOf(false) }
    var remainingTime by remember { mutableIntStateOf(30) }

    val context = LocalContext.current as Activity
    val dbHelper = QuizDatabaseHelper(context)
    val questions = dbHelper.getQuestionsByCategory(SelectedQuiz.quizCategory)
    val userAnswers = remember { mutableStateListOf<Int>() }

    // Start timer when question changes
    LaunchedEffect(currentQuestionIndex) {
        remainingTime = 30
        while (remainingTime > 0) {
            delay(1000L) // 1 second delay
            remainingTime--
        }
        // Auto-move to next question if time runs out
        if (selectedOption == -1) {
            userAnswers.add(-1) // No answer selected
        }
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            selectedOption = -1
        } else {
            showScoreScreen = true
        }
    }

    when {
        showAnswerScreen -> AnswerScreen(questions, userAnswers) { showAnswerScreen = false }
        showScoreScreen -> ScoreScreen(score, questions.size) { showAnswerScreen = true }
        else -> {
            if (questions.isNotEmpty()) {

                val question = questions[currentQuestionIndex]

                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.primary_color)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { context.finish() },
                            painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                            contentDescription = "Arrow Back"
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            text = SelectedQuiz.quizName,
                            color = Color.White,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Question ${currentQuestionIndex + 1}/${questions.size}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Show Timer
                        Text(
                            text = "Time Left: $remainingTime sec",
                            color = if (remainingTime <= 5) Color.Red else Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = question.question,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        question.options.forEachIndexed { index, option ->
                            Button(
                                onClick = { selectedOption = index },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedOption == index) Color.Green else Color.LightGray
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Text(text = option, color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                userAnswers.add(selectedOption)
                                if (question.options[selectedOption] == question.correctAnswer) {
                                    score++
                                }
                                if (currentQuestionIndex < questions.size - 1) {
                                    currentQuestionIndex++
                                    selectedOption = -1
                                } else {
                                    showScoreScreen = true
                                }
                            },
                            enabled = selectedOption != -1,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = if (currentQuestionIndex == questions.size - 1) "Finish" else "Next")
                        }
                    }
                }
            } else {
                Log.e("Test", "No Questions")
            }
        }
    }
}


@Composable
fun ScoreScreen(score: Int, totalQuestions: Int, onSeeAnswersClick: () -> Unit) {
    val context = LocalContext.current as Activity
    val currentDate = getCurrentDate()
    val dbHelper = QuizResults(context)
    dbHelper.insertQuizResult(SelectedQuiz.quizName, SelectedQuiz.quizCategory, currentDate, score)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = R.drawable.quiz_completed),
            contentDescription = "Quiz Completed"
        )

        Text(text = "Quiz Completed!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Score: $score / $totalQuestions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSeeAnswersClick() }) {
            Text(text = "See Answers")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            context.startActivity(Intent(context, QuizHomeActivity::class.java))
            context.finish()
        }) {
            Text(text = "Go to Home")
        }
    }
}

@Composable
fun AnswerScreen(questions: List<QuizQuestion>, userAnswers: List<Int>, onBackClick: () -> Unit) {

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.primary_color)
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        context.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Arrow Back"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = "Answers",
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(questions.size) { index ->
                    val question = questions[index]
                    val selectedAnswerIndex = userAnswers.getOrNull(index) ?: -1
                    val correctAnswerIndex = question.options.indexOf(question.correctAnswer)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "${index + 1}. ${question.question}",
                                fontWeight = FontWeight.Bold
                            )

                            question.options.forEachIndexed { optIndex, option ->
                                val isCorrect = optIndex == correctAnswerIndex
                                val isSelected = optIndex == selectedAnswerIndex

                                Text(
                                    text = option,
                                    color = when {
                                        isSelected && isCorrect -> Color.Green
                                        isSelected && !isCorrect -> Color.Red
                                        isCorrect -> Color.Green
                                        else -> Color.Black
                                    },
                                    fontWeight = if (isSelected || isCorrect) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(onClick = { onBackClick() }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Back to Results")
        }
    }
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date())
}


object SelectedQuiz {
    var quizCategory = ""
    var quizName = ""
}
