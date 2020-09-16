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
            width= 300
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

        Button(mainPanel) with {
            caption = "Register"
            onClick {
                if(modelObject.name.isNullOrEmpty() || modelObject.email.isNullOrEmpty()
                        || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("You must complete all the fields")
                }
                else{
                    try {
                        modelObject.register(modelObject.name, modelObject.email, modelObject.password, modelObject.image)
                        thisWindow.close()
                        UserWindow(owner, this@RegisterWindow.modelObject).open()
                    }
                    catch(ex:Exception) {
                        when(ex) {
                            is UsedEmail -> {
                                throw UserException("The email is already used, please choose another one or Log In")
                            }
                            is InvalidUserOPassword -> {
                                throw UserException ("You must insert a valid email")
                            }
                        }
                    }
                }
            }
        }

        Button(mainPanel) with{
            caption ="Cancel"
            onClick{
                thisWindow.close()
                LoginWindow(owner, this@RegisterWindow.modelObject).open()
            }
        }
    }

}