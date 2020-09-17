package org.unq.ui.View

import org.unq.ui.ViewModel.InstagramModel
import org.unq.ui.model.InvalidUserOPassword
import org.unq.ui.model.UsedEmail
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class RegisterWindow (owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) { }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Register to Instagram"
        setMinWidth(300)

        Label(mainPanel) withText "Name"
        TextBox(mainPanel) with {
            bindTo("name")
            width = 300
        }

        Label(mainPanel) withText "Email"
        TextBox(mainPanel) with {
            bindTo("email")
        }

        Label(mainPanel) withText "Password"
        PasswordField(mainPanel) with {
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            bindTo("image")
        }

        Panel(mainPanel) with {
            asHorizontal()
            var model = thisWindow.modelObject

            Button(it) with {
                caption = "Register"
                width = 200
                onClick {
                    if (model.name.isNullOrEmpty() || model.email.isNullOrEmpty()
                        || model.password.isNullOrEmpty() || model.image.isNullOrEmpty()
                    ) {
                        showError("You must complete all the fields")
                    } else {
                        try {
                            model.register(
                                model.name,
                                model.email,
                                model.password,
                                model.image
                            )
                            thisWindow.close()
                            UserWindow(owner, this@RegisterWindow.modelObject).open()
                        } catch (ex: Exception) {
                            when (ex) {
                                is UsedEmail -> {
                                    throw UserException("The email is already used, please choose another one or Log In")
                                }
                                is InvalidUserOPassword -> {
                                    throw UserException("You must insert a valid email")
                                }
                            }
                        }
                    }
                }
            }

            Button(it) with {
                caption = "Cancel"
                var model = thisWindow.modelObject
                width = 200
                onClick {
                    model.cleanUserAttributes()
                    thisWindow.close()
                    LoginWindow(owner, this@RegisterWindow.modelObject).open()
                }
            }
        }
    }

}