package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hemanth.S3083018.onlinequiz.quizData.QuizDatabaseHelper
import hemanth.S3083018.onlinequiz.quizData.QuizQuestion

class QuizHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizHomeActivityScreen()
            val dbHelper = QuizDatabaseHelper(this)
            populateQuizData(dbHelper)
        }

    }
}

@Composable
fun QuizHomeActivityScreen()
{
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.primary_color2),
            ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.primary_color)
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = "Quiz Home",
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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .background(
                            color = colorResource(id = R.color.primary_color),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 12.dp)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    QuizCategoriesActivity::class.java
                                )
                            )

                        }


                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.quiz_categories),
                        contentDescription = "Quiz\nCategories"
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Quiz Categories",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .background(
                            color = colorResource(id = R.color.primary_color),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 12.dp)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    PopularQuizActivity::class.java
                                )
                            )

                        }

                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.popular_quiz),
                        contentDescription = "Popular\nQuiz"
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "Popular\nQuiz",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }
            
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .background(
                            color = colorResource(id = R.color.primary_color),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 12.dp)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    QuizResultActivity::class.java
                                )
                            )

                        }


                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.quiz_results),
                        contentDescription = "My\nResults"
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "My\nResults",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .background(
                            color = colorResource(id = R.color.primary_color),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 12.dp)

                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.user_profile),
                        contentDescription = "My\nProfile"
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "My\nProfile",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }
        }
    }
}

fun populateQuizData(dbHelper: QuizDatabaseHelper) {
    val questions = listOf(
        // Science
        QuizQuestion(0, "Science Quiz", "Science", "What is the chemical symbol for water?",
            listOf("H2O", "O2", "CO2", "NaCl"), "H2O"),
        QuizQuestion(0, "Science Quiz", "Science", "What planet is known as the Red Planet?",
            listOf("Earth", "Mars", "Jupiter", "Venus"), "Mars"),
        // Add more questions...

        // History
        QuizQuestion(0, "History Quiz", "History", "Who was the first President of the United States?",
            listOf("George Washington", "Abraham Lincoln", "John Adams", "Thomas Jefferson"), "George Washington"),
        QuizQuestion(0, "History Quiz", "History", "Which war ended with the Treaty of Versailles?",
            listOf("WWI", "WWII", "Cold War", "Civil War"), "WWI"),
        // Add more questions...

        // Math
        QuizQuestion(0, "Math Quiz", "Math", "What is 8 × 7?",
            listOf("49", "56", "63", "64"), "56"),
        QuizQuestion(0, "Math Quiz", "Math", "What is the square root of 144?",
            listOf("10", "12", "14", "16"), "12"),
        // Add more questions...

        // Geography
        QuizQuestion(0, "Geography Quiz", "Geography", "What is the capital of France?",
            listOf("London", "Berlin", "Madrid", "Paris"), "Paris"),
        QuizQuestion(0, "Geography Quiz", "Geography", "Which continent is the Sahara Desert located in?",
            listOf("Asia", "South America", "Africa", "Australia"), "Africa"),
        // Add more questions...

        // Sports
        QuizQuestion(0, "Sports Quiz", "Sports", "How many players are on a soccer team?",
            listOf("9", "10", "11", "12"), "11"),
        QuizQuestion(0, "Sports Quiz", "Sports", "Which country won the FIFA World Cup in 2018?",
            listOf("Brazil", "France", "Germany", "Argentina"), "France"),
        // Add more questions...

        // Technology
        QuizQuestion(0, "Technology Quiz", "Technology", "Who founded Microsoft?",
            listOf("Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Elon Musk"), "Bill Gates"),
        QuizQuestion(0, "Technology Quiz", "Technology", "What does CPU stand for?",
            listOf("Central Processing Unit", "Computer Processing Unit", "Core Processor Unit", "Control Processing Unit"),
            "Central Processing Unit"),
        // Add more questions...

        // Movies
        QuizQuestion(0, "Movies Quiz", "Movies", "Who directed 'Titanic'?",
            listOf("Steven Spielberg", "James Cameron", "Christopher Nolan", "Quentin Tarantino"), "James Cameron"),
        QuizQuestion(0, "Movies Quiz", "Movies", "Which movie won the Best Picture Oscar in 2020?",
            listOf("Parasite", "1917", "Joker", "Once Upon a Time in Hollywood"), "Parasite"),
        // Add more questions...

        // Music
        QuizQuestion(0, "Music Quiz", "Music", "Who is known as the 'King of Pop'?",
            listOf("Elvis Presley", "Michael Jackson", "Freddie Mercury", "Prince"), "Michael Jackson"),
        QuizQuestion(0, "Music Quiz", "Music", "Which band sang 'Bohemian Rhapsody'?",
            listOf("The Beatles", "Queen", "Led Zeppelin", "Pink Floyd"), "Queen")
        // Add more questions...
    )

    questions.forEach {
        dbHelper.insertQuizQuestion(it.quizName, it.category, it.question, it.options, it.correctAnswer)
    }
}


