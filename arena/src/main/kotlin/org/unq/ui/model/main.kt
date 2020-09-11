package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.arena.Application
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable


class LoginWindow(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Login"

        Label(mainPanel) withText "Email"
        TextBox(mainPanel) with {
            bindTo("email")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            bindTo("password")
        }

        Button(mainPanel) with {
            caption = "Login"
            onClick { modelObject.login(modelObject.email, modelObject.password) }
        }

    }
}

@Observable
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()){
    var email = ""
    var password = ""

    fun login(email: String, password: String){
        instagramSystem.login(email, password)
    }

}

class InstagramApplication: Application(){
    override fun createMainWindow(): Window<*> {
        return LoginWindow(this, InstagramModel())
    }
}

fun main(){
    InstagramApplication().start()
}