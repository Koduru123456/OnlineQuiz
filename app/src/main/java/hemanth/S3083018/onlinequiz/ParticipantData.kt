package hemanth.S3083018.onlinequiz

import android.content.Context

object ParticipantData {

    fun saveLoginState(context: Context, value: Boolean) {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putBoolean("PSTATUS", value).apply()
    }

    fun retrieveLoginState(context: Context): Boolean {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        return userLogin.getBoolean("PSTATUS", false)
    }

    fun saveUserName(context: Context, value: String) {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putString("PUSERNAME", value).apply()
    }

    fun retrieveUserName(context: Context): String {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        return userLogin.getString("PUSERNAME", "")!!
    }

    fun saveUserMail(context: Context, value: String) {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putString("PUSERMAIL", value).apply()
    }

    fun retrieveUserMail(context: Context): String {
        val userLogin = context.getSharedPreferences("ONLINE_QUIZ_DATA", Context.MODE_PRIVATE)
        return userLogin.getString("PUSERMAIL", "")!!
    }
}