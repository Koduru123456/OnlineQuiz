package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hemanth.S3083018.onlinequiz.quizData.QuizResult
import hemanth.S3083018.onlinequiz.quizData.QuizResults


class QuizResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizResultsScreen()
        }
    }
}


data class QuizResult(
    val quizName: String,
    val category: String,
    val dateAttempted: String,
    val score: Int
)

@Composable
fun QuizResultsScreen() {

//    quizResults: List<QuizResult>

    val context = LocalContext.current as Activity

    val dbHelper = QuizResults(context)
    val quizResults = dbHelper.getQuizResults()

    Log.e("Test", "Results Size : ${quizResults.size}")
    for (result in quizResults) {
        Log.e(
            "QuizResult",
            "Quiz: ${result.quizName}, Score: ${result.score}, Date: ${result.dateAttempted}"
        )
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.primary_color2)
            )
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
                text = "Quiz Results",
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {

            LazyColumn {
                items(quizResults) { result ->
                    QuizResultItem(result)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

    }

//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Text(text = "Quiz Results", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyColumn {
//            items(quizResults) { result ->
//                QuizResultItem(result)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }
//    }
}

@Composable
fun QuizResultItem(result: QuizResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Quiz Name : ${result.quizName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Category : ${result.category}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Date Attempted : ${result.dateAttempted}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Score : ${result.score}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
