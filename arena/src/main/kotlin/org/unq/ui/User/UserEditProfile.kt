package org.unq.ui.User

import org.unq.ui.model.InstagramModel
import org.unq.ui.model.UserDataModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class UserEditProfile(owner: WindowOwner, model: InstagramModel): Dialog<InstagramModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"

        Label(mainPanel) withText "Nombre"
        TextBox(mainPanel) with {

            bindTo("name")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            bindTo("image")
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick{

                if(modelObject.name.isNullOrEmpty() || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("Debe completar todos los campos")
                }
                else{
                    var user = UserDataModel(modelObject.name,modelObject.password,modelObject.image)
                   modelObject.editProfile(user)
                    thisWindow.close()
                   UserView(owner,this@UserEditProfile.modelObject).open()
                }
            }
        }

        Button(mainPanel) with{
            caption = "Cancel"
            onClick{
                cancel()
            }
        }
    }
}
