package org.unq.ui.Login

import org.unq.ui.User.UserRegisterView
import org.unq.ui.model.InstagramModel
import org.unq.ui.User.UserView
import org.unq.ui.model.FieldsBlank
import org.unq.ui.model.InvalidUserOPassword
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
                    modelObject.login(modelObject.email, modelObject.password)
                    thisWindow.close()
                    UserView(owner, this@LoginView.modelObject).open()
                } catch(ex:Exception) {
                    when(ex) {
                        is NotFound -> {
                            throw UserException ("Wrong Username or Password")
                        }
                        is InvalidUserOPassword -> {
                            throw UserException ("You must insert a valid email")
                        }
                        is FieldsBlank ->{
                            throw UserException ("all fields are required")
                        }
                    }
                }

            }
        }

        Button(mainPanel) with {
            caption = "Register"
            onClick {
                thisWindow.close()
                UserRegisterView(owner, this@LoginView.modelObject).open()
            }
        }
    }
}