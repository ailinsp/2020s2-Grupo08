package org.unq.ui.View


import org.unq.ui.Exceptions.FieldsBlank
import org.unq.ui.Exceptions.InvalidUserOPassword
import org.unq.ui.ViewModel.InstagramModel
import org.unq.ui.ViewModel.LoginModel
import org.unq.ui.ViewModel.RegisterModel
import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class LoginWindow(owner: WindowOwner, model: LoginModel): Dialog<LoginModel>(owner,model){


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
                        MainWindow(owner, InstagramModel(this@LoginWindow.modelObject.managementModel)).open()
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
                    RegisterWindow(owner, RegisterModel(this@LoginWindow.modelObject.managementModel)).open()
                }
            }
        }
    }

}