package org.unq.ui.Login

import org.unq.ui.User.UserModel
import org.unq.ui.User.UserView
import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class LoginView(owner: WindowOwner, model: LoginModel): SimpleWindow<LoginModel>(owner, model){
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
        TextBox(mainPanel) with {
            bindTo("password")
        }




        Button(mainPanel) with {
            caption = "Login"

            onClick {
                //val model = UserViewModel()
                //val view = UserViewWindow(this@LoginWindow,model)
                // view.onAccept{
                try {
                    modelObject.login(modelObject.email, modelObject.password)
                    thisWindow.close()
                    UserView(owner, UserModel()).open()

                } catch (e: NotFound){
                    throw UserException(e.message)
                }

            }
        }

    }
}