package hemanth.S3083018.onlinequiz.quizData

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class QuizResults(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QuizApp.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_QUIZ_RESULTS = "QuizResults"
        private const val COLUMN_ID = "id"
        private const val COLUMN_QUIZ_NAME = "quiz_name"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DATE_ATTEMPTED = "date_attempted"
        private const val COLUMN_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_QUIZ_RESULTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUIZ_NAME TEXT NOT NULL,
                $COLUMN_CATEGORY TEXT NOT NULL,
                $COLUMN_DATE_ATTEMPTED TEXT NOT NULL,
                $COLUMN_SCORE INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUIZ_RESULTS")
        onCreate(db)
    }

    // Function to store a quiz result
    fun insertQuizResult(quizName: String, category: String, dateAttempted: String, score: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUIZ_NAME, quizName)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_DATE_ATTEMPTED, dateAttempted)
            put(COLUMN_SCORE, score)
        }
        val result = db.insert(TABLE_QUIZ_RESULTS, null, values)
        db.close()
        return result
    }

    // Function to get the list of quiz results
    fun getQuizResults(): List<QuizResult> {
        val quizResults = mutableListOf<QuizResult>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_QUIZ_RESULTS ORDER BY $COLUMN_DATE_ATTEMPTED DESC"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                val quizName = it.getString(it.getColumnIndexOrThrow(COLUMN_QUIZ_NAME))
                val category = it.getString(it.getColumnIndexOrThrow(COLUMN_CATEGORY))
                val dateAttempted = it.getString(it.getColumnIndexOrThrow(COLUMN_DATE_ATTEMPTED))
                val score = it.getInt(it.getColumnIndexOrThrow(COLUMN_SCORE))

                quizResults.add(QuizResult(id, quizName, category, dateAttempted, score))
            }
        }
        db.close()
        return quizResults
    }
}

// Data class to represent a quiz result
data class QuizResult(
    val id: Int,
    val quizName: String,
    val category: String,
    val dateAttempted: String,
    val score: Int
)
