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
            val quizQuestions = dbHelper.getQuestionsByCategory("Science")
            if(quizQuestions.isEmpty()) {
                populateQuizData(dbHelper)
            }
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
        QuizQuestion(0, "Science Quiz", "Science", "What do plants need to grow?", listOf("Sunlight, Water, Air", "Sunlight, Water, Soil", "Water, Fire, Soil", "Air, Sand, Light"), "Sunlight, Water, Soil"),

        QuizQuestion(0, "Science Quiz", "Science", "Which part of the human body pumps blood?", listOf("Lungs", "Stomach", "Heart", "Brain"), "Heart"),

        QuizQuestion(0, "Science Quiz", "Science", "What gas do plants take in from the air?", listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"), "Carbon Dioxide"),

        QuizQuestion(0, "Science Quiz", "Science", "What is the closest planet to the Sun?", listOf("Earth", "Venus", "Mercury", "Mars"), "Mercury"),

        QuizQuestion(0, "Science Quiz", "Science", "What is the name of the process by which plants make their own food?", listOf("Respiration", "Photosynthesis", "Germination", "Digestion"), "Photosynthesis"),

        QuizQuestion(0, "Science Quiz", "Science", "What is the main source of energy for life on Earth?", listOf("Wind", "The Sun", "Electricity", "Water"), "The Sun"),

        QuizQuestion(0, "Science Quiz", "Science", "How many legs does a spider have?", listOf("4", "8", "6", "10"), "8"),

        QuizQuestion(0, "Science Quiz", "Science", "What is H₂O commonly known as?", listOf("Oxygen", "Hydrogen", "Water", "Carbon Dioxide"), "Water"),

        QuizQuestion(0, "Science Quiz", "Science", "Which material is attracted to magnets?", listOf("Wood", "Plastic", "Iron", "Glass"), "Iron"),

        QuizQuestion(0, "Science Quiz", "Science", "What is the largest organ in the human body?", listOf("Liver", "Skin", "Heart", "Brain"), "Skin"),

        QuizQuestion(0, "Science Quiz", "Science", "What do we call animals that eat only plants?", listOf("Carnivores", "Herbivores", "Omnivores", "Scavengers"), "Herbivores"),

        QuizQuestion(0, "Science Quiz", "Science", "Which planet is known as the ‘Red Planet’?", listOf("Venus", "Jupiter", "Mars", "Saturn"), "Mars"),

        // Add more questions...

        // History

        QuizQuestion(0, "History Quiz", "History", "Who was the first female Prime Minister of the UK?",
            listOf("Theresa May", "Margaret Thatcher", "Queen Elizabeth II", "Emmeline Pankhurst"), "Margaret Thatcher"),

        QuizQuestion(0, "History Quiz", "History", "What was the name of the ship that carried the Pilgrims to America in 1620?",
            listOf("Titanic", "Britannia", "Mayflower", "Queen Mary"), "Mayflower"),

        QuizQuestion(0, "History Quiz", "History", "Which famous king signed the Magna Carta?",
            listOf("King Richard III", "King Henry VIII", "King John", "King Edward I"), "King John"),

        QuizQuestion(0, "History Quiz", "History", "What was the Industrial Revolution?",
            listOf("A military conflict", "A period of technological and industrial change", "A famous battle", "A political treaty"), "A period of technological and industrial change"),

        QuizQuestion(0, "History Quiz", "History", "Who was the Queen of England during the Spanish Armada in 1588?",
            listOf("Mary I", "Elizabeth I", "Anne Boleyn", "Victoria"), "Elizabeth I"),

        QuizQuestion(0, "History Quiz", "History", "What was the name of the prehistoric period when people used stone tools?",
            listOf("The Stone Age", "The Bronze Age", "The Iron Age", "The Middle Ages"), "The Stone Age"),

        QuizQuestion(0, "History Quiz", "History", "Who was the British monarch during World War I?",
            listOf("Queen Victoria", "King George V", "King Edward VII", "King George VI"), "King George V"),

        QuizQuestion(0, "History Quiz", "History", "What year did the Great Fire of London occur?",
            listOf("1666", "1566", "1766", "1866"), "1666"),

        QuizQuestion(0, "History Quiz", "History", "What structure was built to protect England from Scottish invaders?",
            listOf("The Tower of London", "Offa's Dyke", "Hadrian’s Wall", "Windsor Castle"), "Hadrian’s Wall"),

        QuizQuestion(0, "History Quiz", "History", "Who was the first Roman Emperor to invade Britain in 55 BC?",
            listOf("Julius Caesar", "Augustus", "Nero", "Claudius"), "Julius Caesar"),

        QuizQuestion(0, "History Quiz", "History", "Which famous British scientist developed the theory of gravity?",
            listOf("Albert Einstein", "Isaac Newton", "Stephen Hawking", "Charles Darwin"), "Isaac Newton"),

        QuizQuestion(0, "History Quiz", "History", "In what year did Queen Elizabeth II become queen?",
            listOf("1952", "1945", "1960", "1977"), "1952"),


        // Math

        QuizQuestion(0, "Math Quiz", "Math", "What is 8 + 9?",
            listOf("15", "17", "18", "20"), "17"),

        QuizQuestion(0, "Math Quiz", "Math", "What is 5 × 7?",
            listOf("30", "35", "40", "45"), "35"),

        QuizQuestion(0, "Math Quiz", "Math", "How many corners does a rectangle have?",
            listOf("3", "4", "5", "6"), "4"),

        QuizQuestion(0, "Math Quiz", "Math", "What is 20 ÷ 4?",
            listOf("10", "8", "5", "6"), "5"),

        QuizQuestion(0, "Math Quiz", "Math", "If a book costs £5 and you buy 3 books, how much will it cost in total?",
            listOf("£10", "£15", "£20", "£25"), "£15"),

        QuizQuestion(0, "Math Quiz", "Math", "What is the next number in the sequence: 2, 4, 6, 8, ?",
            listOf("9", "10", "12", "14"), "12"),

        QuizQuestion(0, "Math Quiz", "Math", "What is 50% of 200?",
            listOf("75", "100", "150", "200"), "100"),

        QuizQuestion(0, "Math Quiz", "Math", "What is the place value of the digit 7 in the number 378?",
            listOf("Ones", "Tens", "Hundreds", "Thousands"), "Tens"),

        QuizQuestion(0, "Math Quiz", "Math", "How many grams are in 1 kilogram?",
            listOf("10 grams", "100 grams", "1000 grams", "10,000 grams"), "1000 grams"),

        QuizQuestion(0, "Math Quiz", "Math", "Which of these fractions is equivalent to ½?",
            listOf("2/3", "4/8", "3/5", "5/10"), "4/8"),

        QuizQuestion(0, "Math Quiz", "Math", "What is 144 ÷ 12?",
            listOf("10", "12", "12", "16"), "12"),

        QuizQuestion(0, "Math Quiz", "Math", "A triangle has angles measuring 40° and 60°. What is the third angle?",
            listOf("70°", "80°", "80°", "90°"), "80°"),



        // Add more questions...

        // Geography
        QuizQuestion(0, "Geography Quiz", "Geography", "What is the capital city of Scotland?",
            listOf("Glasgow", "Edinburgh", "Aberdeen", "Inverness"), "Edinburgh"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which is the longest river in the UK?",
            listOf("River Thames", "River Trent", "River Severn", "River Clyde"), "River Severn"),

        QuizQuestion(0, "Geography Quiz", "Geography", "What is the name of the highest mountain in the UK?",
            listOf("Ben Nevis", "Snowdon", "Scafell Pike", "Slieve Donard"), "Ben Nevis"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which sea is located to the east of England?",
            listOf("North Sea", "Irish Sea", "Celtic Sea", "Atlantic Ocean"), "North Sea"),

        QuizQuestion(0, "Geography Quiz", "Geography", "What is the capital city of Wales?",
            listOf("Cardiff", "Swansea", "Newport", "Bangor"), "Cardiff"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which famous chalk cliffs are found along the south coast of England?",
            listOf("Stonehenge", "The Pennines", "White Cliffs of Dover", "Giant’s Causeway"), "White Cliffs of Dover"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which country in the UK is known as the Land of the Lakes?",
            listOf("Scotland", "Northern Ireland", "England (Lake District)", "Wales"), "England (Lake District)"),

        QuizQuestion(0, "Geography Quiz", "Geography", "What is the name of the large bay located between Wales and England?",
            listOf("Irish Bay", "Dover Bay", "Bristol Channel", "English Channel"), "Bristol Channel"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which national park is famous for its granite tors in southwest England?",
            listOf("Peak District", "Dartmoor", "Yorkshire Dales", "Snowdonia"), "Dartmoor"),

        QuizQuestion(0, "Geography Quiz", "Geography", "What is the official currency of the United Kingdom?",
            listOf("Euro", "Pound Sterling (£)", "Dollar", "Franc"), "Pound Sterling (£)"),

        QuizQuestion(0, "Geography Quiz", "Geography", "What is the name of the UK’s largest island besides Great Britain?",
            listOf("Isle of Skye", "Isle of Wight", "Isle of Man", "Anglesey"), "Isle of Man"),

        QuizQuestion(0, "Geography Quiz", "Geography", "Which river flows through London?",
            listOf("River Clyde", "River Thames", "River Avon", "River Tyne"), "River Thames"),


        // Add more questions...

        // Sports

        QuizQuestion(0, "Sports Quiz", "Sports", "What is the national sport of England?",
            listOf("Rugby", "Cricket", "Football", "Tennis"), "Cricket"),

        QuizQuestion(0, "Sports Quiz", "Sports", "How many players are there in a football (soccer) team on the field?",
            listOf("9", "10", "11", "12"), "11"),

        QuizQuestion(0, "Sports Quiz", "Sports", "Where is the famous tennis tournament Wimbledon played?",
            listOf("Manchester", "London", "Birmingham", "Liverpool"), "London"),

        QuizQuestion(0, "Sports Quiz", "Sports", "What sport is played at the Ashes tournament?",
            listOf("Cricket", "Rugby", "Tennis", "Golf"), "Cricket"),

        QuizQuestion(0, "Sports Quiz", "Sports", "In which year did London host the Summer Olympics?",
            listOf("2008", "2012", "2016", "2020"), "2012"),

        QuizQuestion(0, "Sports Quiz", "Sports", "Which country invented rugby?",
            listOf("England", "Scotland", "Wales", "Ireland"), "England"),

        QuizQuestion(0, "Sports Quiz", "Sports", "What is the name of the top football league in England?",
            listOf("La Liga", "Serie A", "Premier League", "Bundesliga"), "Premier League"),

        QuizQuestion(0, "Sports Quiz", "Sports", "How many points is a try worth in rugby union?",
            listOf("3", "5", "7", "10"), "5"),

        QuizQuestion(0, "Sports Quiz", "Sports", "Which sport uses a shuttlecock?",
            listOf("Badminton", "Tennis", "Squash", "Volleyball"), "Badminton"),

        QuizQuestion(0, "Sports Quiz", "Sports", "What is the name of the famous horse racing event held annually in England?",
            listOf("The Grand National", "The Kentucky Derby", "The Melbourne Cup", "The Irish Derby"), "The Grand National"),

        QuizQuestion(0, "Sports Quiz", "Sports", "Who is a famous British Formula 1 driver with multiple world championships?",
            listOf("Michael Schumacher", "Lewis Hamilton", "Sebastian Vettel", "Max Verstappen"), "Lewis Hamilton"),

        QuizQuestion(0, "Sports Quiz", "Sports", "What is the maximum score you can achieve in one throw in darts?",
            listOf("20", "40", "60 (Triple 20)", "80"), "60 (Triple 20)"),


        // Add more questions...

        // Technology

        QuizQuestion(0, "Technology Quiz", "Technology", "What does “WWW” stand for in a website address?",
            listOf("World Wide Web", "World Wide Web", "Web World Wide", "Wide World Web"), "World Wide Web"),

        QuizQuestion(0, "Technology Quiz", "Technology", "Which device is used to print documents?",
            listOf("Scanner", "Monitor", "Printer", "Keyboard"), "Printer"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What is the brain of a computer called?",
            listOf("RAM", "CPU (Central Processing Unit)", "Monitor", "Hard Drive"), "CPU (Central Processing Unit)"),

        QuizQuestion(0, "Technology Quiz", "Technology", "Which of the following is an input device?",
            listOf("Monitor", "Keyboard", "Printer", "Speaker"), "Keyboard"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What does USB stand for?",
            listOf("Universal System Bus", "Unified Serial Bus", "Universal Serial Bus", "Unique Storage Bus"), "Universal Serial Bus"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What type of software is Microsoft Word?",
            listOf("Web Browser", "Operating System", "Word Processor", "Database"), "Word Processor"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What is the main function of a firewall in computer systems?",
            listOf("To protect against cyberattacks", "To speed up internet connection", "To store data", "To print documents"), "To protect against cyberattacks"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What is the smallest unit of data in a computer?",
            listOf("Byte", "Kilobyte", "Bit", "Megabyte"), "Bit"),

        QuizQuestion(0, "Technology Quiz", "Technology", "Which part of a computer is used to store data permanently?",
            listOf("RAM", "CPU", "Hard Drive", "Monitor"), "Hard Drive"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What does AI stand for in technology?",
            listOf("Automated Information", "Advanced Internet", "Artificial Intelligence", "Automated Integration"), "Artificial Intelligence"),

        QuizQuestion(0, "Technology Quiz", "Technology", "Which company makes the iPhone?",
            listOf("Samsung", "Microsoft", "Apple", "Google"), "Apple"),

        QuizQuestion(0, "Technology Quiz", "Technology", "What is a commonly used programming language for creating websites?",
            listOf("Python", "HTML", "Java", "C++"), "HTML"),


        // Add more questions...

        // Movies

        QuizQuestion(0, "Movies Quiz", "Movies", "Which British boy wizard is the main character in a famous movie series?",
            listOf("Frodo Baggins", "Percy Jackson", "Harry Potter", "Sherlock Holmes"), "Harry Potter"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Who played the role of James Bond in the movie Skyfall?",
            listOf("Sean Connery", "Pierce Brosnan", "Daniel Craig", "Roger Moore"), "Daniel Craig"),

        QuizQuestion(0, "Movies Quiz", "Movies", "In which animated movie does a princess named Elsa sing \"Let It Go\"?",
            listOf("Moana", "Tangled", "Frozen", "Cinderella"), "Frozen"),

        QuizQuestion(0, "Movies Quiz", "Movies", "What is the name of the British stop-motion animation featuring a man and his dog, Gromit?",
            listOf("Wallace and Gromit", "Shaun the Sheep", "Paddington Bear", "Postman Pat"), "Wallace and Gromit"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Who directed the movie Titanic?",
            listOf("Steven Spielberg", "James Cameron", "Christopher Nolan", "Ridley Scott"), "James Cameron"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Which famous British actor played Mr. Bean?",
            listOf("Stephen Fry", "Rowan Atkinson", "Hugh Laurie", "Ricky Gervais"), "Rowan Atkinson"),

        QuizQuestion(0, "Movies Quiz", "Movies", "What is the name of the lion in The Lion King?",
            listOf("Scar", "Mufasa", "Simba", "Nala"), "Simba"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Which magical object helps Mary Poppins fly?",
            listOf("Magic Wand", "Umbrella", "Broomstick", "Hat"), "Umbrella"),

        QuizQuestion(0, "Movies Quiz", "Movies", "What kind of creature is Shrek in the animated movie Shrek?",
            listOf("Troll", "Ogre", "Giant", "Goblin"), "Ogre"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Which British actress played Hermione Granger in the Harry Potter series?",
            listOf("Emma Roberts", "Emma Watson", "Emily Blunt", "Keira Knightley"), "Emma Watson"),

        QuizQuestion(0, "Movies Quiz", "Movies", "In which animated film does a clownfish named Marlin search for his son Nemo?",
            listOf("Shark Tale", "The Little Mermaid", "Finding Nemo", "Madagascar"), "Finding Nemo"),

        QuizQuestion(0, "Movies Quiz", "Movies", "Which British spy film series features characters like Q and M?",
            listOf("Mission: Impossible", "James Bond", "Kingsman", "The Bourne Series"), "James Bond"),


        // Add more questions...

        // Music

        QuizQuestion(0, "Music Quiz", "Music", "Which British band is known for songs like \"Hey Jude\" and \"Let It Be\"?",
            listOf("The Rolling Stones", "Queen", "The Beatles", "Oasis"), "The Beatles"),

        QuizQuestion(0, "Music Quiz", "Music", "Who is the lead singer of the British rock band Queen?",
            listOf("Mick Jagger", "Freddie Mercury", "Paul McCartney", "David Bowie"), "Freddie Mercury"),

        QuizQuestion(0, "Music Quiz", "Music", "Which famous UK music festival is held in Somerset?",
            listOf("Isle of Wight Festival", "Reading Festival", "Glastonbury Festival", "Leeds Festival"), "Glastonbury Festival"),

        QuizQuestion(0, "Music Quiz", "Music", "Who is known as the \"Queen of Pop\" and has had numerous UK chart hits?",
            listOf("Kylie Minogue", "Madonna", "Adele", "Beyoncé"), "Madonna"),

        QuizQuestion(0, "Music Quiz", "Music", "Which musical instrument has black and white keys?",
            listOf("Guitar", "Piano", "Violin", "Trumpet"), "Piano"),

        QuizQuestion(0, "Music Quiz", "Music", "Which British singer released the hit song \"Someone Like You\"?",
            listOf("Dua Lipa", "Adele", "Amy Winehouse", "Ellie Goulding"), "Adele"),

        QuizQuestion(0, "Music Quiz", "Music", "What is the name of the UK chart that ranks the most popular songs each week?",
            listOf("Global Top 50", "Billboard 100", "Official UK Singles Chart", "Spotify Chart"), "Official UK Singles Chart"),

        QuizQuestion(0, "Music Quiz", "Music", "What is the name of the British boy band that Harry Styles was a part of?",
            listOf("Westlife", "Take That", "One Direction", "The Vamps"), "One Direction"),

        QuizQuestion(0, "Music Quiz", "Music", "Who composed the famous classical piece \"The Planets\"?",
            listOf("Beethoven", "Mozart", "Gustav Holst", "Tchaikovsky"), "Gustav Holst"),

        QuizQuestion(0, "Music Quiz", "Music", "What is the name of the traditional Scottish musical instrument often played at ceremonies?",
            listOf("Bagpipes", "Harp", "Accordion", "Flute"), "Bagpipes"),

        QuizQuestion(0, "Music Quiz", "Music", "Who sang the theme song \"Skyfall\" for the James Bond movie Skyfall?",
            listOf("Sam Smith", "Ellie Goulding", "Adele", "Florence Welch"), "Adele"),

        QuizQuestion(0, "Music Quiz", "Music", "What is the term for a musical performance with an orchestra, typically including multiple movements?",
            listOf("Opera", "Symphony", "Sonata", "Concerto"), "Symphony"),

        // Add more questions...

        QuizQuestion(0, "PMath Quiz", "PMath", "What is 8 + 6?",
            listOf("12", "13", "14", "15"), "14"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is half of 20?",
            listOf("5", "8", "10", "12"), "10"),

        QuizQuestion(0, "PMath Quiz", "PMath", "Which of the following is a multiple of 3?",
            listOf("14", "21", "27", "35"), "27"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is 7 × 5?",
            listOf("35", "30", "40", "45"), "35"),

        QuizQuestion(0, "PMath Quiz", "PMath", "A triangle has how many sides?",
            listOf("2", "3", "4", "5"), "3"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is 45 ÷ 9?",
            listOf("5", "6", "7", "8"), "5"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is the place value of 3 in the number 4321?",
            listOf("Ones", "Tens", "Hundreds", "Thousands"), "Hundreds"),

        QuizQuestion(0, "PMath Quiz", "PMath", "How many minutes are in one hour?",
            listOf("30 minutes", "45 minutes", "60 minutes", "90 minutes"), "60 minutes"),

        QuizQuestion(0, "PMath Quiz", "PMath", "Which shape has no corners?",
            listOf("Square", "Triangle", "Rectangle", "Circle"), "Circle"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is 100 - 37?",
            listOf("57", "63", "67", "73"), "63"),

        QuizQuestion(0, "PMath Quiz", "PMath", "What is the next number in the sequence: 2, 4, 6, 8, ?",
            listOf("9", "10", "11", "12"), "10"),

        QuizQuestion(0, "PMath Quiz", "PMath", "If a chocolate bar costs £2 and you buy 3 bars, how much will you pay?",
            listOf("£4", "£5", "£6", "£8"), "£6"),



        QuizQuestion(0, "PScience Quiz", "PScience", "What do plants need to make their food?",
            listOf("Water and Soil", "Sunlight and Water", "Air and Rocks", "Light and Fire"), "Sunlight and Water"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What gas do humans breathe in to stay alive?",
            listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"), "Oxygen"),

        QuizQuestion(0, "PScience Quiz", "PScience", "Which part of the plant takes in water from the soil?",
            listOf("Roots", "Stem", "Leaves", "Flowers"), "Roots"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What is the largest planet in our solar system?",
            listOf("Earth", "Mars", "Jupiter", "Venus"), "Jupiter"),

        QuizQuestion(0, "PScience Quiz", "PScience", "How many legs does a spider have?",
            listOf("6", "8", "10", "12"), "8"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What is the boiling point of water?",
            listOf("50°C", "75°C", "100°C", "150°C"), "100°C"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What is the process by which plants release oxygen?",
            listOf("Respiration", "Photosynthesis", "Digestion", "Evaporation"), "Photosynthesis"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What do we call an animal that eats only plants?",
            listOf("Carnivore", "Herbivore", "Omnivore", "Insectivore"), "Herbivore"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What force keeps us on the ground?",
            listOf("Gravity", "Magnetism", "Electricity", "Friction"), "Gravity"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What is the hardest natural substance on Earth?",
            listOf("Gold", "Iron", "Diamond", "Quartz"), "Diamond"),

        QuizQuestion(0, "PScience Quiz", "PScience", "Which part of the human body pumps blood?",
            listOf("Heart", "Lungs", "Liver", "Stomach"), "Heart"),

        QuizQuestion(0, "PScience Quiz", "PScience", "What is the name of the galaxy we live in?",
            listOf("Andromeda", "Black Hole", "Milky Way", "Orion"), "Milky Way"),


        QuizQuestion(0, "PHistory Quiz", "PHistory", "Who was the first king of England?",
            listOf("Richard I", "Athelstan", "Alfred the Great", "William the Conqueror"), "Athelstan"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "What is the name of the famous prehistoric monument in England?",
            listOf("Hadrian’s Wall", "The Tower of London", "Stonehenge", "Big Ben"), "Stonehenge"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Who was the Queen of England during the Victorian era?",
            listOf("Elizabeth I", "Anne", "Victoria", "Mary I"), "Victoria"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "In which year did the Battle of Hastings take place?",
            listOf("965", "1066", "1215", "1415"), "1066"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Who was the Prime Minister of Britain during World War II?",
            listOf("Neville Chamberlain", "Clement Attlee", "Winston Churchill", "Margaret Thatcher"), "Winston Churchill"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "What was the name of the famous ship that sank on its maiden voyage in 1912?",
            listOf("Titanic", "Britannia", "Mayflower", "Endeavour"), "Titanic"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Who built Hadrian’s Wall?",
            listOf("Romans", "Vikings", "Anglo-Saxons", "Normans"), "Romans"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Which famous document, signed in 1215, limited the power of the king?",
            listOf("The Domesday Book", "Magna Carta", "The Bill of Rights", "The Act of Union"), "Magna Carta"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Who was the first woman Prime Minister of the UK?",
            listOf("Theresa May", "Margaret Thatcher", "Elizabeth II", "Queen Victoria"), "Margaret Thatcher"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "Which country did the UK fight against in the Falklands War in 1982?",
            listOf("France", "Spain", "Argentina", "Germany"), "Argentina"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "What was the name of Henry VIII’s second wife?",
            listOf("Catherine of Aragon", "Anne Boleyn", "Jane Seymour", "Anne of Cleves"), "Anne Boleyn"),

        QuizQuestion(0, "PHistory Quiz", "PHistory", "What was the name of the period when machines and factories changed the way people worked?",
            listOf("The Renaissance", "The Middle Ages", "The Industrial Revolution", "The Victorian Era"), "The Industrial Revolution"),




        QuizQuestion(0, "PGeography Quiz", "PGeography", "What is the capital city of England?",
            listOf("Manchester", "Birmingham", "London", "Liverpool"), "London"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which is the longest river in the UK?",
            listOf("Thames", "Severn", "Trent", "Avon"), "Severn"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "What is the highest mountain in the UK?",
            listOf("Ben Nevis", "Snowdon", "Scafell Pike", "Helvellyn"), "Ben Nevis"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which ocean is to the west of the UK?",
            listOf("Indian Ocean", "Atlantic Ocean", "Arctic Ocean", "Pacific Ocean"), "Atlantic Ocean"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "What is the name of the large body of water between England and France?",
            listOf("The North Sea", "The Baltic Sea", "The English Channel", "The Irish Sea"), "The English Channel"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "What is the capital city of Scotland?",
            listOf("Glasgow", "Edinburgh", "Aberdeen", "Inverness"), "Edinburgh"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which country is not part of the United Kingdom?",
            listOf("Republic of Ireland", "Scotland", "Wales", "Northern Ireland"), "Republic of Ireland"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "What type of landform is the Isle of Wight?",
            listOf("Mountain", "Peninsula", "Island", "Valley"), "Island"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which famous river flows through London?",
            listOf("Severn", "Avon", "Thames", "Mersey"), "Thames"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "What is the name of the group of islands to the north of Scotland?",
            listOf("Shetland Islands", "Orkney Islands", "Isle of Man", "Channel Islands"), "Shetland Islands"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which national park is located in the Lake District?",
            listOf("Snowdonia", "Lake District National Park", "Peak District", "Dartmoor"), "Lake District National Park"),

        QuizQuestion(0, "PGeography Quiz", "PGeography", "Which continent is the UK located in?",
            listOf("Asia", "Africa", "Europe", "Australia"), "Europe"),


        


    )

    questions.forEach {
        dbHelper.insertQuizQuestion(it.quizName, it.category, it.question, it.options, it.correctAnswer)
    }
}


