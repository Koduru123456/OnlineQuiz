package hemanth.S3083018.onlinequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase


class QuizLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizLoginScreen()
        }

    }
}


@Composable
fun QuizLoginScreen() {
    var userMailId by remember { mutableStateOf("") }
    var userAccountPin by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.primary_color),
            ),
    ) {

        Spacer(modifier = Modifier.height(54.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_quiz), // Replace with your actual SVG drawable
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = colorResource(id = R.color.primary_color2),
                )
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(horizontal = 12.dp),
                value = userMailId,
                onValueChange = { userMailId = it },
                label = { Text("Student Email") }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = userAccountPin,
                onValueChange = { userAccountPin = it },
                label = { Text("Student Password") }
            )

            Spacer(modifier = Modifier.height(36.dp))


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(
                        color = colorResource(id = R.color.primary_color),
                        )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.primary_color),
                    )
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .clickable {
                        when{


                            userMailId.isBlank() -> {
                                Toast.makeText(context, "Enter MailId to Login", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            userAccountPin.isBlank() -> {
                                Toast.makeText(context, "Enter Password to Register", Toast.LENGTH_SHORT)
                                    .show()

                            }
                            else -> {

                                val userData = UserData(
                                    "",
                                    userMailId,
                                    "",
                                    userAccountPin

                                )

                                loginOnlineQuiz(userData, context)

                            }
                        }
                    },
                text = "Login",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = colorResource(id = R.color.primary_color2),
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {

                Text(
                    modifier = Modifier,
                    text = "New Here?  ",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.primary_color),
                    )
                )


                Text(
                    modifier = Modifier
                        .clickable {
                            context.startActivity(Intent(context, QuizRegisterActivity::class.java))
                            context.finish()
                        },
                    text = "Create Account",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.primary_color),
                    )
                )

            }

            Spacer(modifier = Modifier.height(46.dp))


        }


    }
}

@Preview(showBackground = true)
@Composable
fun QuizLoginScreenPreview() {
    QuizLoginScreen()
}

fun loginOnlineQuiz(userData: UserData, context: Context) {

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("OnlineQuizData").child(userData.emailId.replace(".", ","))

    databaseReference.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val userDataLoc = task.result?.getValue(UserData::class.java)
            if (userDataLoc != null) {
                if (userDataLoc.password == userData.password) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    ParticipantData.persistLoginState(context, true)
                    ParticipantData.persistUserMail(context, userDataLoc.emailId)
                    ParticipantData.persistUserName(context, userDataLoc.userName)
                    Toast.makeText(context, "Login Sucessfully", Toast.LENGTH_SHORT).show()

                    context.startActivity(Intent(context, QuizHomeActivity::class.java))
                    (context as Activity).finish()
                } else {
                    Toast.makeText(context, "Seems Incorrect Credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Your account not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                context,
                "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}