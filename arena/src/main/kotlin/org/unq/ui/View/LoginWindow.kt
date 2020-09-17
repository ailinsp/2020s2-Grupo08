package org.unq.ui.View

import org.unq.ui.ViewModel.InstagramModel
import org.unq.ui.model.FieldsBlank
import org.unq.ui.model.InvalidUserOPassword
import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class LoginWindow(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Login to Instagram"
        setMinWidth(300)

        Label(mainPanel) withText "Email"
        TextBox(mainPanel) with {
            bindTo("email")
            width = 300
        }

        Label(mainPanel) withText "Password"
        PasswordField(mainPanel) with {
            bindTo("password")
        }


        Panel(mainPanel) with {

            asHorizontal()

            Button(it) with {
                caption = "Login"
                width = 150
                var model = thisWindow.modelObject
                onClick {
                    try {
                        model.login(model.email, model.password)
                        thisWindow.close()
                        UserWindow(owner, this@LoginWindow.modelObject).open()
                    } catch (ex: Exception) {
                        when (ex) {
                            is NotFound -> {
                                throw UserException("Wrong Username or Password")
                            }
                            is InvalidUserOPassword -> {
                                throw UserException("You must insert a valid email")
                            }
                            is FieldsBlank -> {
                                throw UserException("You must complete all fields")
                            }
                        }
                    }
                }
            }

            Button(it) with {
                caption = "Register"
                width = 150
                var model = thisWindow.modelObject
                onClick {
                    thisWindow.close()
                    model.cleanUserAttributes()
                    RegisterWindow(owner, this@LoginWindow.modelObject).open()
                }
            }
        }
    }
}