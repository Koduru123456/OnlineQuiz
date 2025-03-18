package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
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

class QuizHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizHomeActivityScreen()
        }
    }
}

@Composable
fun QuizHomeActivityScreen() {

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

        Text(
            text = "Select a Category",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val categories = listOf(
            QuizCategory("Science", R.drawable.ic_quiz),
            QuizCategory("History", R.drawable.ic_quiz),
            QuizCategory("Math", R.drawable.ic_quiz),
            QuizCategory("Geography", R.drawable.ic_quiz),
            QuizCategory("Sports", R.drawable.ic_quiz),
            QuizCategory("Technology", R.drawable.ic_quiz),
            QuizCategory("Movies", R.drawable.ic_quiz),
            QuizCategory("Music", R.drawable.ic_quiz)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(categories) { category ->
                CategoryItem(context,category)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuizHomeActivityScreenPreview() {
    QuizHomeActivityScreen()
}


@Composable
fun CategoryItem(context: Activity,category: QuizCategory) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                context.startActivity(Intent(context, TakeQuizActivity::class.java))
                context.finish()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

data class QuizCategory(val name: String, val imageRes: Int)
