package org.unq.ui.User

import org.unq.ui.model.UserDataModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class UserEditProfile(owner: WindowOwner, model: UserDataModel): Dialog<UserDataModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"
        setMinWidth(300)

        Label(mainPanel) withText "Nombre"
        TextBox(mainPanel) with {
            width = 150
            bindTo("name")
        }

        Label(mainPanel) withText "Password"
        TextBox(mainPanel) with {
            width = 150
            bindTo("password")
        }

        Label(mainPanel) withText "Image"
        TextBox(mainPanel) with {
            width = 150
            bindTo("image")
        }

        Button(mainPanel) with{
            caption = "Accept"
            onClick{
                if(modelObject.name.isNullOrEmpty() || modelObject.password.isNullOrEmpty() || modelObject.image.isNullOrEmpty() ){
                    showError("Debe completar todos los campos")
                }
                else{
                    accept()
                }
            }
        }

        Button(mainPanel) with{
            caption ="Cancel"
            onClick{
                cancel()
            }
        }
    }
}
