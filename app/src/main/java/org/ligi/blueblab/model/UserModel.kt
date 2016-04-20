package org.ligi.blueblab.model

import com.chibatching.kotpref.KotprefModel
import org.ligi.blueblab.Mood

class UserModel : KotprefModel() {

    var name: String by stringPrefVar()
    var moodString: String by stringPrefVar(default = Mood.NEUTRAL.toString())
    var color: Int by intPrefVar()
    var id: String by stringPrefVar(default = "")

    fun isInitialized(): Boolean {
        return !id.isEmpty()
    }

    fun getMood(): Mood {
        return Mood.valueOf(moodString)
    }

    fun setMood(mood:Mood) {
        moodString = mood.toString()
    }

}