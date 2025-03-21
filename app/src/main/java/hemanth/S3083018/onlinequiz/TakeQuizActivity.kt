package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hemanth.S3083018.onlinequiz.quizData.QuizDatabaseHelper
import hemanth.S3083018.onlinequiz.quizData.QuizQuestion
import hemanth.S3083018.onlinequiz.quizData.QuizResults
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
//    val questions = listOf(
//        QuizQuestion("What is the capital of France?", listOf("Berlin", "Madrid", "Paris", "Rome"), 2),
//        QuizQuestion("Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Jupiter", "Saturn"), 1),
//        QuizQuestion("What is the largest mammal?", listOf("Elephant", "Blue Whale", "Giraffe", "Hippo"), 1),
//        QuizQuestion("Who wrote 'Hamlet'?", listOf("Shakespeare", "Hemingway", "Tolkien", "Austen"), 0)
//    )



    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var showScoreScreen by remember { mutableStateOf(false) }

    val context = LocalContext.current as Activity


    val dbHelper = QuizDatabaseHelper(context)
    val questions = dbHelper.getQuestionsByCategory(SelectedQuiz.quizCategory)

    if (showScoreScreen) {
        ScoreScreen(score, questions.size)
    } else {
        val question = questions[currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.primary_color)
                    ),
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
                    text = "Quiz",
                    color = Color.White,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Question ${currentQuestionIndex + 1}/${questions.size}",
                    fontSize = 20.sp,
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
    }
}

@Composable
fun ScoreScreen(score: Int, totalQuestions: Int) {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            modifier = Modifier
                .size(128.dp)
                ,
            painter = painterResource(id = R.drawable.quiz_completed),
            contentDescription = "Arrow Forward"
        )

        Text(
            text = "Quiz Completed!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Score: $score / $totalQuestions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = { context.QuizScreen() }) {
//            Text(text = "Restart Quiz")
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))

        val currentDate = getCurrentDate()

        val dbHelper = QuizResults(context)
        dbHelper.insertQuizResult(SelectedQuiz.quizName, SelectedQuiz.quizCategory, currentDate, score)


        Button(onClick = {
            context.startActivity(Intent(context, QuizHomeActivity::class.java))
            context.finish()
        }) {
            Text(text = "Go to Home")
        }
    }
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date())
}

//data class QuizQuestion(
//    val question: String,
//    val options: List<String>,
//    val correctAnswer: Int
//)

object SelectedQuiz{
    var quizCategory = ""
    var quizName = ""
}
