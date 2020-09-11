package org.unq.ui.model

import org.omg.CORBA.UserException
import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.arena.Application
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Panel

import org.uqbar.arena.windows.*
import org.uqbar.commons.model.annotations.Observable


class LoginWindow(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
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
                        UserViewWindow(owner,UserViewModel()).open()

                    } catch (e: NotFound){
                        throw UserException(e.message)
                }

            }
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


@Observable
class UserViewModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    fun search(msg: String) {
        instagramSystem.searchByTag(msg)
    }

}



class UserViewWindow(owner: WindowOwner, model: UserViewModel): SimpleWindow<UserViewModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(p0: Panel?) {
        title = "Post de User"
        setMinWidth(300)
    }

}



fun main(){
    InstagramApplication().start()
}