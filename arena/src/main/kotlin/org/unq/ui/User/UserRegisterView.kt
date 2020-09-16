package org.unq.ui.User

import org.unq.ui.Login.LoginView
import org.unq.ui.model.InstagramModel
import org.unq.ui.model.InvalidUserOPassword
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class UserRegisterView (owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
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
                    showError("Debe completar todos los campos")
                }
                else{
                    try {
                        modelObject.register(modelObject.name, modelObject.email, modelObject.password, modelObject.image)
                        thisWindow.close()
                        UserView(owner, this@UserRegisterView.modelObject).open()
                    }
                    catch(ex:Exception) {
                        when(ex) {
                            is UsedEmail -> {
                                throw UserException("Ya existe una cuenta asociada al email provisto")
                            }
                            is InvalidUserOPassword -> {
                                throw UserException ("Debe introducir un email válido")
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
                LoginView(owner, this@UserRegisterView.modelObject).open()
            }
        }
    }

}