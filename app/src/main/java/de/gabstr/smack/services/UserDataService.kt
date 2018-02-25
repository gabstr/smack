package de.gabstr.smack.services

import android.graphics.Color
import de.gabstr.smack.controller.App
import java.util.*

object UserDataService {

    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun logout () {
        id = ""
        avatarColor = ""
        avatarName = ""
        email = ""
        name = ""
        App.prefs.authToken = ""
        App.prefs.userEmail = ""
        App.prefs.isLoggedIn= false
        MessageService.clearMessages()
        MessageService.clearChannels()
    }

    fun returnAvatarColor(components: String) : Int {

        println(components)
        val strippedColor = components
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")

        println(strippedColor)

        var r = 0
        var g = 0
        var b = 0

        val scanner = Scanner(strippedColor)
                .useLocale(Locale.US) // because of '.' in double...
        if(scanner.hasNext()) {
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r, g, b)
    }
}