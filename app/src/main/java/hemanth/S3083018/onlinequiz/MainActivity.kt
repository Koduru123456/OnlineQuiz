package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizStartingPoint()
        }
    }
}

@Composable
fun QuizStartingPoint()
{
    val context = LocalContext.current as Activity
    var showSplash by remember { mutableStateOf(true) }

    DisposableEffect(Unit) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            showSplash = false
        }
        onDispose { job.cancel() }
    }

    if (showSplash) {
        QuizMainActivity()

    } else {

        val loginStatus = UserDataSP.fetchLoginState(context)

        if(loginStatus)
        {
            context.startActivity(Intent(context, QuizHomeActivity::class.java))
            context.finish()
        }else{
            context.startActivity(Intent(context, QuizLoginActivity::class.java))
            context.finish()
        }

    }

}


@Composable
fun QuizMainActivity() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.primary_color),
            ),
    ) {


        Spacer(modifier = Modifier.weight(1f))


        Image(
            painter = painterResource(id = R.drawable.ic_quiz), // Replace with your actual SVG drawable
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Online Quiz App",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.primary_color2),
                )
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Developed By",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            )


            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Hemanth Koduru",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(24.dp))



        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuizMainActivityPreview() {
    QuizMainActivity()
}