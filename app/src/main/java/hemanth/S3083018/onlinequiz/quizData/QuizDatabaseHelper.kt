package hemanth.S3083018.onlinequiz.quizData

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QuizDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QuizDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_QUIZ = "QuizQuestions"

        private const val COLUMN_ID = "id"
        private const val COLUMN_QUIZ_NAME = "quiz_name"
        private const val COLUMN_CATEGORY = "quiz_category"
        private const val COLUMN_QUESTION = "question"
        private const val COLUMN_OPTIONS = "options"  // Store options as a comma-separated string
        private const val COLUMN_CORRECT_ANSWER = "correct_answer"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_QUIZ (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUIZ_NAME TEXT NOT NULL,
                $COLUMN_CATEGORY TEXT NOT NULL,
                $COLUMN_QUESTION TEXT NOT NULL,
                $COLUMN_OPTIONS TEXT NOT NULL,  -- Store options as CSV
                $COLUMN_CORRECT_ANSWER TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUIZ")
        onCreate(db)
    }

    // Function to Insert Quiz Question
    fun insertQuizQuestion(
        quizName: String, category: String, question: String, options: List<String>, correctAnswer: String
    ): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUIZ_NAME, quizName)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_QUESTION, question)
            put(COLUMN_OPTIONS, options.joinToString(","))  // Convert list to CSV string
            put(COLUMN_CORRECT_ANSWER, correctAnswer)
        }
        val result = db.insert(TABLE_QUIZ, null, values)
        db.close()
        return result
    }

    // Function to Get Questions by Category
    fun getQuestionsByCategory(category: String): List<QuizQuestion> {
        val questionList = mutableListOf<QuizQuestion>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_QUIZ WHERE $COLUMN_CATEGORY = ?",
            arrayOf(category)
        )

        if (cursor.moveToFirst()) {
            do {
                val question = QuizQuestion(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    quizName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUIZ_NAME)),
                    category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                    question = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION)),
                    options = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTIONS)).split(","),  // Convert CSV to list
                    correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORRECT_ANSWER))
                )
                questionList.add(question)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return questionList
    }
}


// Data Class for Quiz Question
data class QuizQuestion(
    val id: Int,
    val quizName: String,
    val category: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)
