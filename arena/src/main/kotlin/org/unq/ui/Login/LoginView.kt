package org.unq.ui.Login

import org.unq.ui.model.InstagramModel
import org.unq.ui.User.UserView
import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class LoginView(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel?) {

        title = "Login to Instagram"
        setMinWidth(300)

        Label(mainPanel) withText "Email"
        TextBox(mainPanel) with {
            bindTo("email")
            width= 300
        }

        Label(mainPanel) withText "Password"
        PasswordField(mainPanel) with {
            bindTo("password")
        }


        Button(mainPanel) with {
            caption = "Login"
            onClick {
                try {
                   // var user =
                        modelObject.login(modelObject.email, modelObject.password)
                    //modelObject.user = user

                    thisWindow.close()
                    UserView(owner, InstagramModel()).open()

                } catch (e: NotFound){
                    throw UserException(e.message)
                }

            }
        }

    }
}